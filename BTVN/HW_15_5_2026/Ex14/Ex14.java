import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Ex14 extends JFrame {

    public Ex14() {
        setTitle("Bezier Curve Animation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel instructionLabel = new JLabel("Click 4 points on the canvas to draw a Bezier curve.", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(instructionLabel, BorderLayout.NORTH);

        BezierCanvas canvas = new BezierCanvas();
        add(canvas, BorderLayout.CENTER);

        JButton btnClear = new JButton("Clear Canvas");
        btnClear.addActionListener(e -> canvas.clear());
        add(btnClear, BorderLayout.SOUTH);
    }

    private class BezierCanvas extends JPanel {
        private List<Point2D.Double> points;
        private Timer timer;
        private double t = 0.0;
        private String textToAnimate = "THANG";

        public BezierCanvas() {
            setBackground(Color.WHITE);
            points = new ArrayList<>();

            timer = new Timer(16, e -> {
                t += 0.005;
                if (t > 1.0) {
                    t = 0.0;
                }
                repaint();
            });

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (points.size() < 4) {
                        points.add(new Point2D.Double(e.getX(), e.getY()));
                        repaint();
                        if (points.size() == 4) {
                            t = 0.0;
                            timer.start();
                        }
                    }
                }
            });
        }

        public void clear() {
            points.clear();
            timer.stop();
            t = 0.0;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(Color.RED);
            for (Point2D.Double p : points) {
                g2d.fillOval((int) p.x - 4, (int) p.y - 4, 8, 8);
            }

            if (points.size() >= 2) {
                g2d.setColor(Color.LIGHT_GRAY);
                for (int i = 0; i < points.size() - 1; i++) {
                    g2d.drawLine((int) points.get(i).x, (int) points.get(i).y, 
                                 (int) points.get(i + 1).x, (int) points.get(i + 1).y);
                }
            }

            if (points.size() == 4) {
                Point2D.Double p0 = points.get(0);
                Point2D.Double p1 = points.get(1);
                Point2D.Double p2 = points.get(2);
                Point2D.Double p3 = points.get(3);

                g2d.setColor(Color.BLUE);
                CubicCurve2D.Double curve = new CubicCurve2D.Double(
                        p0.x, p0.y, p1.x, p1.y, p2.x, p2.y, p3.x, p3.y);
                g2d.draw(curve);

                double mt = 1.0 - t;
                double currentX = (mt * mt * mt * p0.x) 
                                + (3 * mt * mt * t * p1.x) 
                                + (3 * mt * t * t * p2.x) 
                                + (t * t * t * p3.x);
                
                double currentY = (mt * mt * mt * p0.y) 
                                + (3 * mt * mt * t * p1.y) 
                                + (3 * mt * t * t * p2.y) 
                                + (t * t * t * p3.y);

                double dx = 3 * mt * mt * (p1.x - p0.x) 
                          + 6 * mt * t * (p2.x - p1.x) 
                          + 3 * t * t * (p3.x - p2.x);
                
                double dy = 3 * mt * mt * (p1.y - p0.y) 
                          + 6 * mt * t * (p2.y - p1.y) 
                          + 3 * t * t * (p3.y - p2.y);

                double angle = Math.atan2(dy, dx);

                g2d.setColor(Color.BLUE);
                g2d.setFont(new Font("Arial", Font.BOLD, 24));
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(textToAnimate);
                int textHeight = fm.getAscent();

                AffineTransform oldTransform = g2d.getTransform();
                g2d.translate(currentX, currentY);
                g2d.rotate(angle);
                g2d.drawString(textToAnimate, -textWidth / 2, textHeight / 2);
                g2d.setTransform(oldTransform);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Ex14().setVisible(true);
        });
    }
}