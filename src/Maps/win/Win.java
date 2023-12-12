package Maps.win;

import Pages.Home.HomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Win extends JFrame {

    public static void main(String[] args) {
        new WinListener();
    }

    public static class WinListener implements ActionListener {

        JFrame frame = new JFrame();
        JButton backButton = new JButton("Back to Maze");
        JButton nextButton = new JButton("Next Level");
        JLabel myLabel = new JLabel("Win");
        JLabel gifLabel = new JLabel();
        JLabel imageLabel = new JLabel();

        WinListener() {
            myLabel.setBounds(100, 80, 200, 40); // Adjust the position for centering
            myLabel.setHorizontalAlignment(JLabel.CENTER);

            // Load the GIF from the resources
            //ImageIcon gifIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("/Assets/giphy.gif")));
            //gifLabel.setIcon(gifIcon);
            //gifLabel.setBounds(50, 150, 300, 200);  // Adjust the size and position as needed

            // Load the image for the right top

            backButton.setBounds(10, 330, 150, 40);
            backButton.setFocusable(false);
            backButton.addActionListener(this);

            nextButton.setBounds(260, 330, 150, 40);
            nextButton.setFocusable(false);
            nextButton.addActionListener(this);

            frame.add(myLabel);
            frame.add(gifLabel);
            frame.add(imageLabel);
            frame.add(backButton);
            frame.add(nextButton);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(420, 420);

            // Center the window on the screen
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (screenSize.width - frame.getWidth()) / 2;
            int y = (screenSize.height - frame.getHeight()) / 2;
            frame.setLocation(x, y);

            frame.setLayout(null);
            frame.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton) {
                // Handle back to maze button action
                frame.dispose(); // Close the current window
                HomePage myHomePage = new HomePage(); // Instantiate the HomePage class
            } else if (e.getSource() == nextButton) {
                // Handle next level button action
                // You can add your code here
            }
        }
    }
}
