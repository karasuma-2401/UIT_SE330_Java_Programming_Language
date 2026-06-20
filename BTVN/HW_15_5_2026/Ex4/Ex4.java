import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ex4 extends JFrame {
    private JPanel btnPanel;
    private FlowLayout flowLayout;

    public Ex4() {
        setTitle("Align Button Program");
        setSize(450, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        flowLayout = new FlowLayout(FlowLayout.CENTER, 15, 0);
        btnPanel = new JPanel(flowLayout);

        JButton btnLeft = new JButton("Left");
        JButton btnRight = new JButton("Right");
        JButton btnCenter = new JButton("Center");

        btnLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flowLayout.setAlignment(FlowLayout.LEFT);
                refreshUI();
            }
        });
        btnCenter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flowLayout.setAlignment(FlowLayout.CENTER);
                refreshUI();
            }
        });
        btnRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flowLayout.setAlignment(FlowLayout.RIGHT);
                refreshUI();
            }
        });

        btnPanel.add(btnLeft);
        btnPanel.add(btnCenter);
        btnPanel.add(btnRight);

        btnPanel.setPreferredSize(new java.awt.Dimension(400, 50));
        add(btnPanel);
    }
    private void refreshUI() {
        btnPanel.revalidate();
        btnPanel.repaint();
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ex4().setVisible(true);
            }
        });
    }
}
