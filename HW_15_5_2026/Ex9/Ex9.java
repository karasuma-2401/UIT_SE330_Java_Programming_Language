import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Ex9 extends JFrame {

    private JTextField textField;

    public Ex9() {
        setTitle("Font Style Changer");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        textField = new JTextField("Type your text here...");
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setHorizontalAlignment(JTextField.CENTER);

        JPanel radioPanel = new JPanel();

        JRadioButton rbNormal = new JRadioButton("Normal", true);
        JRadioButton rbBold = new JRadioButton("Bold");
        JRadioButton rbItalic = new JRadioButton("Italic");
        JRadioButton rbBoldItalic = new JRadioButton("Bold and Italic");

        ButtonGroup styleGroup = new ButtonGroup();
        styleGroup.add(rbNormal);
        styleGroup.add(rbBold);
        styleGroup.add(rbItalic);
        styleGroup.add(rbBoldItalic);

        radioPanel.add(rbNormal);
        radioPanel.add(rbBold);
        radioPanel.add(rbItalic);
        radioPanel.add(rbBoldItalic);

        rbNormal.addActionListener(e -> changeStyle(Font.PLAIN));
        rbBold.addActionListener(e -> changeStyle(Font.BOLD));
        rbItalic.addActionListener(e -> changeStyle(Font.ITALIC));
        rbBoldItalic.addActionListener(e -> changeStyle(Font.BOLD | Font.ITALIC));

        add(textField, BorderLayout.CENTER);
        add(radioPanel, BorderLayout.SOUTH);
    }

    private void changeStyle(int fontStyle) {
        Font currentFont = textField.getFont();
        textField.setFont(new Font(currentFont.getName(), fontStyle, currentFont.getSize()));
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Ex9().setVisible(true);
        });
    }
}