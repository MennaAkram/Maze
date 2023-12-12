package Pages.Instructions;

import Pages.Home.HomePage;
import Pages.UserName.userNameSingle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Objects;

public class SinglePlayerInstructions extends JFrame{
    public SinglePlayerInstructions() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @Override
    public void processWindowEvent(final WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
//            voice.stop();
            new HomePage().setVisible(true);
//            HomePage.voice.start();
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jButton1 = new JButton();
        jLabel1 = new JLabel();

        setBackground(Color.BLACK);
        setMinimumSize(new Dimension(600, 400));
        setResizable(false);
        setTitle("Maze");
        getContentPane().setLayout(null);

        jButton1.setFont(new java.awt.Font("Maiden Orange", Font.PLAIN, 32)); // NOI18N
        jButton1.setText("Play");
        jButton1.addActionListener(evt -> jButton1ActionPerformed());
        getContentPane().add(jButton1);
        jButton1.setBackground(new Color(217, 217, 217));
        jButton1.setBounds(255, 270, 150, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/Assets/Instruction2.png")))); // NOI18N
//        jLabel1.setMinimumSize(new java.awt.Dimension(200, 200));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 0, 110, 120);

        pack();
    }// </editor-fold>

    private void jButton1ActionPerformed() {
        this.dispose();
        new userNameSingle().setVisible(true);

    }


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new SinglePlayerInstructions().setVisible(true));
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration
}
