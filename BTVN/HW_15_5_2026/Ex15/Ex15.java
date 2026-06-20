// enhance a game to click on the fire -> +1 points

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Ex15 extends JFrame {

    private BufferedImage[] frames;
    private List<FireTarget> fires;
    private int score;
    private int timeLeft;
    private boolean isPlaying;

    private JLabel lblScore;
    private JLabel lblTime;
    private JButton btnStart;
    private GameCanvas canvas;

    private Timer animTimer;
    private Timer spawnTimer;
    private Timer gameTimer;

    private int frameWidth;
    private int frameHeight;
    private final Random rand = new Random();

    private class FireTarget {
        int x;
        int y;
        int currentFrame;

        public FireTarget(int x, int y, int currentFrame) {
            this.x = x;
            this.y = y;
            this.currentFrame = currentFrame;
        }
    }

    public Ex15() {
        setTitle("Fire Extinguisher Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        fires = new ArrayList<>();
        loadSprite();

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        topPanel.setBackground(Color.DARK_GRAY);

        lblScore = new JLabel("Score: 0");
        lblScore.setForeground(Color.WHITE);
        lblScore.setFont(new Font("Arial", Font.BOLD, 20));

        lblTime = new JLabel("Time: 30s");
        lblTime.setForeground(Color.WHITE);
        lblTime.setFont(new Font("Arial", Font.BOLD, 20));

        btnStart = new JButton("Start Game");
        btnStart.setFont(new Font("Arial", Font.BOLD, 16));
        btnStart.setFocusable(false);

        topPanel.add(lblScore);
        topPanel.add(lblTime);
        topPanel.add(btnStart);

        canvas = new GameCanvas();
        add(topPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);

        setupTimers();

        btnStart.addActionListener(e -> startGame());
    }

    private void loadSprite() {
        try {
            BufferedImage spriteSheet = ImageIO.read(new File("Sprite.png"));
            int rows = 5;
            int cols = 5;
            frameWidth = spriteSheet.getWidth() / cols;
            frameHeight = spriteSheet.getHeight() / rows;
            frames = new BufferedImage[rows * cols];

            int count = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    frames[count++] = spriteSheet.getSubimage(
                        j * frameWidth,
                        i * frameHeight,
                        frameWidth,
                        frameHeight
                    );
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                this,
                "Missing 'Sprite.png' in the root directory.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            frames = new BufferedImage[0];
        }
    }

    private void setupTimers() {
        animTimer = new Timer(40, e -> {
            if (frames != null && frames.length > 0) {
                for (FireTarget f : fires) {
                    f.currentFrame = (f.currentFrame + 1) % frames.length;
                }
                canvas.repaint();
            }
        });
        animTimer.start();

        spawnTimer = new Timer(600, e -> {
            if (isPlaying && frames != null && frames.length > 0) {
                spawnFire();
            }
        });

        gameTimer = new Timer(1000, e -> {
            if (isPlaying) {
                timeLeft--;
                lblTime.setText("Time: " + timeLeft + "s");
                if (timeLeft <= 0) {
                    endGame();
                }
            }
        });
    }

    private void startGame() {
        if (frames == null || frames.length == 0) return;
        
        isPlaying = true;
        score = 0;
        timeLeft = 30;
        fires.clear();
        
        lblScore.setText("Score: " + score);
        lblTime.setText("Time: " + timeLeft + "s");
        btnStart.setEnabled(false);

        spawnTimer.start();
        gameTimer.start();
    }

    private void endGame() {
        isPlaying = false;
        spawnTimer.stop();
        gameTimer.stop();
        btnStart.setEnabled(true);
        fires.clear();
        canvas.repaint();

        JOptionPane.showMessageDialog(
            this,
            "Game Over!\nYour final score is: " + score,
            "Time's Up",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void spawnFire() {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        if (width == 0 || height == 0) return;

        int x = rand.nextInt(Math.max(1, width - frameWidth));
        int y = rand.nextInt(Math.max(1, height - frameHeight));
        int startFrame = rand.nextInt(frames.length);

        fires.add(new FireTarget(x, y, startFrame));
    }

    private class GameCanvas extends JPanel {
        public GameCanvas() {
            setBackground(Color.BLACK);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (!isPlaying) return;

                    boolean hit = false;
                    for (int i = fires.size() - 1; i >= 0; i--) {
                        FireTarget f = fires.get(i);
                        Rectangle bounds = new Rectangle(f.x, f.y, frameWidth, frameHeight);
                        if (bounds.contains(e.getPoint())) {
                            fires.remove(i);
                            score++;
                            lblScore.setText("Score: " + score);
                            hit = true;
                            break;
                        }
                    }
                    if (hit) {
                        repaint();
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (frames == null || frames.length == 0) return;
            
            Graphics2D g2d = (Graphics2D) g;
            for (FireTarget f : fires) {
                g2d.drawImage(frames[f.currentFrame], f.x, f.y, null);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Ex15().setVisible(true);
        });
    }
}