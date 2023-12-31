package Pages.ChoosePlayer;

import Pages.Home.HomePage;
import Pages.Instructions.MultiPlayerInstructions;
import Pages.Instructions.SinglePlayerInstructions;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class ChoosePlayer extends JFrame {

    public static void main(String[] args) {
        new ChoosePlayer();
    }

    public ChoosePlayer() {
        JButton single = new JButton();
        JButton multi = new JButton();
        JButton back = new JButton();

        single.setFont(new java.awt.Font("Maiden Orange", Font.PLAIN, 32)); // NOI18N
        single.setText("Single Player");
        single.setBackground(new Color(217, 217, 217));
        single.addActionListener(evt -> singleActionPerformed());
        getContentPane().add(single);
        single.setBounds(110, 160, 180, 40);

        multi.setFont(new java.awt.Font("Maiden Orange", Font.PLAIN, 32)); // NOI18N
        multi.setText("Multi Player");
        multi.setBackground(new Color(217, 217, 217));
        multi.addActionListener(evt -> multiActionPerformed());
        getContentPane().add(multi);
        multi.setBounds(300, 160, 180, 40);

        back.setFont(new Font("Maiden Orange", Font.PLAIN, 32)); // NOI18N
        back.setText("Back");
        back.setBackground(new Color(217, 217, 217));
        back.addActionListener(evt -> backActionPerformed());
        getContentPane().add(back);
        back.setBounds(225, 280, 150, 40);

        GLCanvas glcanvas;
        Animator animator;

        ChoosePlayerGlEventListener listener = new ChoosePlayerGlEventListener();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(24);
        animator.add(glcanvas);
        animator.start();

        setTitle("Maze");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }

    private void singleActionPerformed() {
        this.dispose();
        new SinglePlayerInstructions().setVisible(true);
    }

    private void multiActionPerformed() {
        this.dispose();
        new MultiPlayerInstructions().setVisible(true);
    }

    private void backActionPerformed() {
        this.dispose();
        new HomePage().setVisible(true);
    }

    public void processWindowEvent(final WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            new HomePage().setVisible(true);
        }
    }

}