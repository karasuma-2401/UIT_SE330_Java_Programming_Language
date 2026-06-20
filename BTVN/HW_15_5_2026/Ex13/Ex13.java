import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

public class Ex13 extends JFrame {

    private enum DrawTool {
        FREEHAND_PATH, LINE, CURVE, RECTANGLE, SQUARE, ELLIPSE, CIRCLE, POLYGON
    }

    private class ShapeRecord {
        Shape shape;
        Color strokeColor;
        Color fillColor;
        float strokeWidth;
        boolean isFilled;

        public ShapeRecord(Shape shape, Color strokeColor, Color fillColor, float strokeWidth, boolean isFilled) {
            this.shape = shape;
            this.strokeColor = strokeColor;
            this.fillColor = fillColor;
            this.strokeWidth = strokeWidth;
            this.isFilled = isFilled;
        }
    }

    private class CanvasPanel extends JPanel {
        private List<ShapeRecord> shapes = new ArrayList<>();
        private Shape previewShape = null;
        private Path2D.Double currentPath = null;
        private Polygon currentPolygon = null;

        private DrawTool currentTool = DrawTool.FREEHAND_PATH;
        private Color strokeColor = Color.BLACK;
        private Color fillColor = Color.WHITE;
        private float strokeWidth = 2.0f;
        private boolean fillShape = false;

        private int startX, startY;

        public CanvasPanel() {
            setBackground(Color.WHITE);
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

            MouseAdapter mouseHandler = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (currentTool == DrawTool.POLYGON) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            if (currentPolygon != null && currentPolygon.npoints > 1) {
                                shapes.add(new ShapeRecord(currentPolygon, strokeColor, fillColor, strokeWidth, fillShape));
                            }
                            currentPolygon = null;
                            repaint();
                            return;
                        }
                        if (currentPolygon == null) {
                            currentPolygon = new Polygon();
                        }
                        currentPolygon.addPoint(e.getX(), e.getY());
                        repaint();
                        return;
                    }

                    if (SwingUtilities.isLeftMouseButton(e)) {
                        startX = e.getX();
                        startY = e.getY();
                        if (currentTool == DrawTool.FREEHAND_PATH) {
                            currentPath = new Path2D.Double();
                            currentPath.moveTo(startX, startY);
                        }
                    }
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    if (currentTool == DrawTool.POLYGON || !SwingUtilities.isLeftMouseButton(e)) {
                        return;
                    }

                    int x = e.getX();
                    int y = e.getY();
                    int minX = Math.min(startX, x);
                    int minY = Math.min(startY, y);
                    int width = Math.abs(x - startX);
                    int height = Math.abs(y - startY);

                    switch (currentTool) {
                        case FREEHAND_PATH:
                            currentPath.lineTo(x, y);
                            previewShape = currentPath;
                            break;
                        case LINE:
                            previewShape = new Line2D.Double(startX, startY, x, y);
                            break;
                        case CURVE:
                            previewShape = new QuadCurve2D.Double(startX, startY, startX, y, x, y);
                            break;
                        case RECTANGLE:
                            previewShape = new Rectangle2D.Double(minX, minY, width, height);
                            break;
                        case SQUARE:
                            int size = Math.max(width, height);
                            int sqX = startX < x ? startX : startX - size;
                            int sqY = startY < y ? startY : startY - size;
                            previewShape = new Rectangle2D.Double(sqX, sqY, size, size);
                            break;
                        case ELLIPSE:
                            previewShape = new Ellipse2D.Double(minX, minY, width, height);
                            break;
                        case CIRCLE:
                            int d = Math.max(width, height);
                            int cx = startX < x ? startX : startX - d;
                            int cy = startY < y ? startY : startY - d;
                            previewShape = new Ellipse2D.Double(cx, cy, d, d);
                            break;
                        default:
                            break;
                    }
                    repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (currentTool == DrawTool.POLYGON || !SwingUtilities.isLeftMouseButton(e)) {
                        return;
                    }
                    if (previewShape != null) {
                        shapes.add(new ShapeRecord(previewShape, strokeColor, fillColor, strokeWidth, fillShape));
                        previewShape = null;
                        currentPath = null;
                        repaint();
                    }
                }
            };

            addMouseListener(mouseHandler);
            addMouseMotionListener(mouseHandler);
        }

        public void setTool(DrawTool tool) {
            this.currentTool = tool;
            this.currentPolygon = null;
            this.previewShape = null;
            repaint();
        }

        public void setStrokeColor(Color c) {
            this.strokeColor = c;
        }

        public void setFillColor(Color c) {
            this.fillColor = c;
        }

        public void setStrokeWidth(float w) {
            this.strokeWidth = w;
        }

        public void setFillShape(boolean fill) {
            this.fillShape = fill;
        }

        public void clearCanvas() {
            shapes.clear();
            currentPolygon = null;
            previewShape = null;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            for (ShapeRecord record : shapes) {
                drawShapeRecord(g2d, record.shape, record.strokeColor, record.fillColor, record.strokeWidth, record.isFilled);
            }

            if (previewShape != null) {
                drawShapeRecord(g2d, previewShape, strokeColor, fillColor, strokeWidth, fillShape);
            }

            if (currentPolygon != null && currentPolygon.npoints > 0) {
                g2d.setColor(strokeColor);
                g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                for (int i = 0; i < currentPolygon.npoints - 1; i++) {
                    g2d.drawLine(currentPolygon.xpoints[i], currentPolygon.ypoints[i], currentPolygon.xpoints[i + 1], currentPolygon.ypoints[i + 1]);
                }
            }
        }

        private void drawShapeRecord(Graphics2D g2d, Shape shape, Color sColor, Color fColor, float sWidth, boolean fill) {
            if (fill && isFillable(shape)) {
                g2d.setColor(fColor);
                g2d.fill(shape);
            }
            g2d.setColor(sColor);
            g2d.setStroke(new BasicStroke(sWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.draw(shape);
        }

        private boolean isFillable(Shape shape) {
            return shape instanceof Rectangle2D || shape instanceof Ellipse2D || shape instanceof Polygon;
        }
    }

    public Ex13() {
        setTitle("Paint Application");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        CanvasPanel canvas = new CanvasPanel();
        add(canvas, BorderLayout.CENTER);

        JToolBar topToolBar = new JToolBar();
        topToolBar.setFloatable(false);

        JComboBox<DrawTool> toolComboBox = new JComboBox<>(DrawTool.values());
        toolComboBox.addActionListener(e -> canvas.setTool((DrawTool) toolComboBox.getSelectedItem()));
        topToolBar.add(new JLabel(" Tool: "));
        topToolBar.add(toolComboBox);

        topToolBar.addSeparator(new Dimension(20, 0));

        JButton btnStrokeColor = new JButton("Pen Color");
        btnStrokeColor.setForeground(Color.BLACK);
        btnStrokeColor.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Select Pen Color", Color.BLACK);
            if (c != null) {
                canvas.setStrokeColor(c);
                btnStrokeColor.setForeground(c);
            }
        });
        topToolBar.add(btnStrokeColor);

        JButton btnFillColor = new JButton("Brush Color");
        btnFillColor.setForeground(Color.WHITE);
        btnFillColor.setBackground(Color.DARK_GRAY);
        btnFillColor.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Select Brush Color", Color.WHITE);
            if (c != null) {
                canvas.setFillColor(c);
                btnFillColor.setForeground(c);
            }
        });
        topToolBar.add(btnFillColor);

        topToolBar.addSeparator(new Dimension(20, 0));

        JCheckBox chkFill = new JCheckBox("Fill Shape");
        chkFill.addActionListener(e -> canvas.setFillShape(chkFill.isSelected()));
        topToolBar.add(chkFill);

        topToolBar.addSeparator(new Dimension(20, 0));

        JSpinner spinWidth = new JSpinner(new SpinnerNumberModel(2.0, 1.0, 50.0, 1.0));
        spinWidth.addChangeListener(e -> {
            double val = (Double) spinWidth.getValue();
            canvas.setStrokeWidth((float) val);
        });
        topToolBar.add(new JLabel(" Pen Size: "));
        topToolBar.add(spinWidth);

        topToolBar.addSeparator(new Dimension(20, 0));

        JButton btnClear = new JButton("Clear Canvas");
        btnClear.addActionListener(e -> canvas.clearCanvas());
        topToolBar.add(btnClear);
        
        topToolBar.addSeparator(new Dimension(20, 0));

        add(topToolBar, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Ex13().setVisible(true);
        });
    }
}