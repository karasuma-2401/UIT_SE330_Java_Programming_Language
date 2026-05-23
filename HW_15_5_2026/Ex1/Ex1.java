import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Ex1 {
    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog(
            null, 
            "Please enter your name:", 
            "Enter Name", 
            JOptionPane.QUESTION_MESSAGE
        );
        if (name == null || name.trim().isEmpty()) {
            name = "Anonymous";
        }
        JFrame frame = new JFrame("Greeting App");
        frame.setSize(400, 200); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setLocationRelativeTo(null); 

        JLabel greetingLabel = new JLabel("Hello, " + name + "!", SwingConstants.CENTER);
        greetingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(greetingLabel);
        frame.setVisible(true);
    }
}