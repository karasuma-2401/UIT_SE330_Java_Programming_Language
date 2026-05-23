import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class Ex3 extends JFrame {
        private JTextArea textArea1;
        private JTextArea textArea2;
        private JButton  btnCopy;

        public Ex3() {
            setTitle("Copy Programming");
            setSize(550, 350);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null); // -> It used to center the window on ther screen

            textArea1 = new JTextArea("Enter text here...");
            textArea2 = new JTextArea();

            JScrollPane scrollPane1 = new JScrollPane(textArea1);
            JScrollPane scrollPane2 = new JScrollPane(textArea2);

            JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));
            panel.add(scrollPane1);
            panel.add(scrollPane2);

            btnCopy = new JButton("Copy selected text");

            btnCopy.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedText = textArea1.getSelectedText();
                    if (selectedText != null && !selectedText.isEmpty()) {
                        textArea2.setText(selectedText);
                    } else {
                        JOptionPane.showMessageDialog(Ex3.this, 
                            "Please select some text to copy.", 
                            "No Text Selected", 
                            JOptionPane.WARNING_MESSAGE
                        );
                    }
                }
            });
            add(panel, BorderLayout.CENTER);
            add(btnCopy, BorderLayout.SOUTH);
        }
        public static void main(String[] args) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Ex3 app = new Ex3();
                    app.setVisible(true);
                }
            });
        }
    }
