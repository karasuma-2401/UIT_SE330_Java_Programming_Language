import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Ex11 extends JFrame {

    public Ex11() {
        setTitle("Random Colored Stars Ring");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(new StarPanel(), BorderLayout.CENTER);
    }

    private class StarPanel extends JPanel {
        private final Random random = new Random();

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int centerX = width / 2;
            int centerY = height / 2;

            int numStars = 20;
            int ringRadius = 200; 
            int starInnerRadius = 50;
            int starOuterRadius = 120;

            g2d.translate(centerX, centerY);

            for (int i = 0; i < numStars; i++) {
                float r = random.nextFloat();
                float g_val = random.nextFloat();
                float b = random.nextFloat();
                g2d.setColor(new Color(r, g_val, b));

                AffineTransform oldTransform = g2d.getTransform();

                double angle = i * (2 * Math.PI / numStars);
                g2d.rotate(angle);
                g2d.translate(ringRadius, 0);

                g2d.fill(createStarShape(0, 0, starInnerRadius, starOuterRadius));

                g2d.setTransform(oldTransform);
            }
        }

        private Shape createStarShape(double centerX, double centerY, double innerRadius, double outerRadius) {
            Path2D.Double path = new Path2D.Double();
            double deltaAngle = Math.PI / 5;
            double currentAngle = -Math.PI / 2;

            for (int i = 0; i < 10; i++) {
                double radius = (i % 2 == 0) ? outerRadius : innerRadius;
                double x = centerX + Math.cos(currentAngle) * radius;
                double y = centerY + Math.sin(currentAngle) * radius;

                if (i == 0) {
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }
                currentAngle += deltaAngle;
            }
            path.closePath();
            return path;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Ex11().setVisible(true);
        });
    }
}