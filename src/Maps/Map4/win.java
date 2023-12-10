package Maps.Map4;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class win extends JFrame {
int win = 0;
    public win() {
        setTitle("Win Window");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel(""+ win);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        getContentPane().add(label);

        Timer timer = new Timer(2000, new ActionListener() { // Timer set to 2000 milliseconds (2 seconds)
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window after the timer expires
            }
        });

        timer.setRepeats(false); // Set to false to make it only fire once
        timer.start(); // Start the timer
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            win winWindow = new win();
            winWindow.setVisible(true);
        });
    }
}
