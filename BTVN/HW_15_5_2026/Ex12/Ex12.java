import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Ex12 extends JFrame {

    private CirclePanel circlePanel;
    private JSlider sizeSlider;

    public Ex12() {
        setTitle("Circle Size Controller");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        circlePanel = new CirclePanel(150);

        sizeSlider = new JSlider(SwingConstants.HORIZONTAL, 10, 500, 150);
        sizeSlider.setMajorTickSpacing(100);
        sizeSlider.setMinorTickSpacing(25);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);

        sizeSlider.addChangeListener(e -> {
            int newSize = sizeSlider.getValue();
            circlePanel.setDiameter(newSize);
        });

        add(circlePanel, BorderLayout.CENTER);
        add(sizeSlider, BorderLayout.SOUTH);
    }

    private class CirclePanel extends JPanel {
        private int diameter;

        public CirclePanel(int diameter) {
            this.diameter = diameter;
            setBackground(Color.WHITE);
        }

        public void setDiameter(int diameter) {
            this.diameter = diameter;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(Color.BLUE);
            int x = (getWidth() - diameter) / 2;
            int y = (getHeight() - diameter) / 2;
            g2d.fillOval(x, y, diameter, diameter);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Ex12().setVisible(true);
        });
    }
}