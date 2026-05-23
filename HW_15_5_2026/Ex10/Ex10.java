import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;

public class Ex10 extends JFrame {

    private JLabel textDisplay;
    private String currentFontFamily = "Arial";
    private JCheckBoxMenuItem cbBold;
    private JCheckBoxMenuItem cbItalic;

    public Ex10() {
        setTitle("Text Editor Menu");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        textDisplay = new JLabel("Le Minh Thang", SwingConstants.CENTER);
        textDisplay.setFont(new Font(currentFontFamily, Font.PLAIN, 36));
        add(textDisplay, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        JMenuItem itemAbout = new JMenuItem("About");
        JMenuItem itemExit = new JMenuItem("Exit");

        menuFile.add(itemAbout);
        menuFile.addSeparator();
        menuFile.add(itemExit);

        JMenu menuFormat = new JMenu("Format");
        JMenu menuColor = new JMenu("Color");
        JMenu menuFont = new JMenu("Font");

        JMenuItem itemRed = new JMenuItem("Red");
        JMenuItem itemGreen = new JMenuItem("Green");
        JMenuItem itemBlue = new JMenuItem("Blue");

        menuColor.add(itemRed);
        menuColor.add(itemGreen);
        menuColor.add(itemBlue);

        JRadioButtonMenuItem rbArial = new JRadioButtonMenuItem("Arial", true);
        JRadioButtonMenuItem rbTimes = new JRadioButtonMenuItem("Times New Roman");
        JRadioButtonMenuItem rbCourier = new JRadioButtonMenuItem("Courier New");

        ButtonGroup fontGroup = new ButtonGroup();
        fontGroup.add(rbArial);
        fontGroup.add(rbTimes);
        fontGroup.add(rbCourier);

        cbBold = new JCheckBoxMenuItem("Bold");
        cbItalic = new JCheckBoxMenuItem("Italic");

        menuFont.add(rbArial);
        menuFont.add(rbTimes);
        menuFont.add(rbCourier);
        menuFont.addSeparator();
        menuFont.add(cbBold);
        menuFont.add(cbItalic);

        menuFormat.add(menuColor);
        menuFormat.add(menuFont);

        menuBar.add(menuFile);
        menuBar.add(menuFormat);
        setJMenuBar(menuBar);

        itemExit.addActionListener(e -> System.exit(0));

        itemAbout.addActionListener(e -> JOptionPane.showMessageDialog(
            Ex10.this, 
            "This is a homework 10", 
            "About", 
            JOptionPane.INFORMATION_MESSAGE
        ));

        itemRed.addActionListener(e -> textDisplay.setForeground(Color.RED));
        itemGreen.addActionListener(e -> textDisplay.setForeground(Color.GREEN));
        itemBlue.addActionListener(e -> textDisplay.setForeground(Color.BLUE));

        rbArial.addActionListener(e -> {
            currentFontFamily = "Arial";
            updateTextFont();
        });

        rbTimes.addActionListener(e -> {
            currentFontFamily = "Times New Roman";
            updateTextFont();
        });

        rbCourier.addActionListener(e -> {
            currentFontFamily = "Courier New";
            updateTextFont();
        });

        cbBold.addActionListener(e -> updateTextFont());
        cbItalic.addActionListener(e -> updateTextFont());
    }

    private void updateTextFont() {
        int style = Font.PLAIN;
        if (cbBold.isSelected()) {
            style |= Font.BOLD;
        }
        if (cbItalic.isSelected()) {
            style |= Font.ITALIC;
        }
        textDisplay.setFont(new Font(currentFontFamily, style, 36));
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Ex10().setVisible(true);
        });
    }
}