import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; 

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Ex2 extends JFrame implements KeyListener { 
    private JLabel display;
    
    public Ex2 () {
        setTitle("Key Detector programming"); 
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display = new JLabel("Press any key...", SwingConstants.CENTER);
        display.setFont(new Font("Arial", Font.BOLD, 24));

        add(display);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        String keyName = KeyEvent.getKeyText(keyCode);
        display.setText("You just pressed: " + keyName);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        // Do nothing
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Ex2 app = new Ex2();
                app.setVisible(true);
            }
        });
    }
}