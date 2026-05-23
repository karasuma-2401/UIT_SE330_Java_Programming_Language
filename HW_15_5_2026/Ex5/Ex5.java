import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Ex5 extends JFrame {
    private JButton[] buttons;
    public Ex5() {
        setTitle("Hidden/show button (Border layout");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        JButton btnAbove = new JButton("Above");
        JButton btnBelow = new JButton("Below");
        JButton btnLeft = new JButton("Left");
        JButton btnRight = new JButton("Right");
        JButton btnCenter = new JButton("Center");

        buttons = new JButton[] { btnAbove, btnBelow, btnLeft, btnRight, btnCenter };
        Font font = new Font("Arial", Font.BOLD, 20);

        ActionListener btnListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                for (JButton btn : buttons) {
                    btn.setVisible(true);
                }
                clickedButton.setVisible(false);
            }
        };
        for (JButton btn: buttons) {
            btn.setFont(font);
            btn.addActionListener(btnListener);
        }

        add(btnAbove, BorderLayout.NORTH);
        add(btnBelow, BorderLayout.SOUTH);
        add(btnLeft, BorderLayout.WEST);    
        add(btnRight, BorderLayout.EAST);
        add(btnCenter, BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ex5().setVisible(true);
            }
        });
    }
}
