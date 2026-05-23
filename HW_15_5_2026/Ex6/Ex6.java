import java.awt.BorderLayout;
import java.awt.event.ActionEvent;     
import java.awt.event.ActionListener;   
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Ex6 extends JFrame{
    private JList<String> categoryList;
    private JButton btnSubmit;
    
    public Ex6() {
        setTitle("Demo JList");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] categories = {
            "Văn học", "Khoa học", "Lịch sử", 
            "Công nghệ", "Thể thao", "Văn hóa"
        };
        categoryList = new JList<>(categories);
        categoryList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane scrollPane = new JScrollPane(categoryList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Press and hold Ctrl to pick more options"));

        btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> selectedItems = categoryList.getSelectedValuesList();
                if (selectedItems.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        Ex6.this,
                        "No categories selected.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                    );
                } else {
                    String resultText = String.join(", ", selectedItems);
                    JOptionPane.showMessageDialog(
                        Ex6.this,
                        "Selected categories:\n" + resultText,
                        "Selection Result",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });
        add(scrollPane, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnSubmit);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Ex6().setVisible(true);
        });
    }
}