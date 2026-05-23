import java.awt.BorderLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ex8 extends JFrame {

    private JLabel imageLabel;

    public Ex8() {
        setTitle("Image Viewer");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        imageLabel = new JLabel("No image selected", SwingConstants.CENTER);
        JScrollPane scrollPane = new JScrollPane(imageLabel);

        JButton btnOpen = new JButton("Choose Image");
        
        btnOpen.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg", "gif");
            fileChooser.setFileFilter(filter);

            int result = fileChooser.showOpenDialog(Ex8.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
                imageLabel.setIcon(icon);
                imageLabel.setText("");
            }
        });

        add(btnOpen, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Ex8().setVisible(true);
        });
    }
}