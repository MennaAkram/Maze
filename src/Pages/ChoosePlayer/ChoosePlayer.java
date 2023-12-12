package Pages.ChoosePlayer;

import Pages.UserName.userNameMultiplayer;
import Pages.UserName.userNameSingle;
import Pages.Home.HomePage;
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }

    private void singleActionPerformed() {
        this.dispose();
        new userNameSingle().setVisible(true);
    }

    private void multiActionPerformed() {
        this.dispose();
        new userNameMultiplayer().setVisible(true);
    }

    public void processWindowEvent(final WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            new HomePage().setVisible(true);
//            HomePage.voice.start();
        }
    }

}