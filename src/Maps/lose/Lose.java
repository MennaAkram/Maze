package Maps.lose;



import Pages.Home.HomePage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Lose extends JFrame {

    public static void main(String[] args) {
        new LoseListener();}


    public static class LoseListener implements ActionListener {

        JFrame frame = new JFrame();
        JButton myButton = new JButton("Win");
        JLabel myLabel = new JLabel("you Lose");
        JLabel gifLabel = new JLabel();

        LoseListener() {

//            myButton.setBounds(100, 160, 200, 40);
//            myButton.setFocusable(false);
//            myButton.addActionListener(this);
            myLabel.setBounds(100, 80, 200, 40); // Adjust the position for centering
            myLabel.setHorizontalAlignment(JLabel.CENTER);
            ImageIcon gifIcon = new ImageIcon(this.getClass().getResource("/Assets/giphy.gif"));
            gifLabel.setIcon(gifIcon);

//            frame.add(myButton);
            frame.add(myLabel);
            frame.add(gifLabel);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(420, 420);
            frame.setLayout(null);
            frame.setVisible(true);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            HomePage myWindow = new HomePage();

        }
    }
}

