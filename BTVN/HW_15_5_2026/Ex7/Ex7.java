import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Ex7 extends JFrame {

    public Ex7() {
        setTitle("Color Changer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();

        JRadioButton rbRed = new JRadioButton("Red");
        JRadioButton rbGreen = new JRadioButton("Green");
        JRadioButton rbBlue = new JRadioButton("Blue");
        JRadioButton rbYellow = new JRadioButton("Yellow");

        ButtonGroup colorGroup = new ButtonGroup();
        colorGroup.add(rbRed);
        colorGroup.add(rbGreen);
        colorGroup.add(rbBlue);
        colorGroup.add(rbYellow);

        controlPanel.add(rbRed);
        controlPanel.add(rbGreen);
        controlPanel.add(rbBlue);
        controlPanel.add(rbYellow);

        rbRed.addActionListener(e -> mainContainer.setBackground(Color.RED));
        rbGreen.addActionListener(e -> mainContainer.setBackground(Color.GREEN));
        rbBlue.addActionListener(e -> mainContainer.setBackground(Color.BLUE));
        rbYellow.addActionListener(e -> mainContainer.setBackground(Color.YELLOW));

        mainContainer.add(controlPanel, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Ex7().setVisible(true);
        });
    }
}