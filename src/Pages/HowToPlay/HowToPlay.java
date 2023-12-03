package Pages.HowToPlay;

import Pages.Home.HomePage;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class HowToPlay extends JFrame {

    public static void main(String[] args) {
        new HowToPlay();
    }

    public HowToPlay() {
        JButton back = new JButton();
        JLabel label = new JLabel();
        JLabel text = new JLabel();

        back.setFont(new Font("Maiden Orange", Font.PLAIN, 32)); // NOI18N
        back.setText("Back");
        back.setBackground(new Color(217, 217, 217));
        back.addActionListener(evt -> backActionPerformed());
        getContentPane().add(back);
        back.setBounds(60, 280, 150, 40);

        label.setFont(new Font("Maiden Orange", Font.PLAIN, 32)); // NOI18N
        label.setForeground(new java.awt.Color(225, 225, 225));
        label.setText("How to play");
        getContentPane().add(label);
        label.setBounds(30, 60, 180, 40);

        text.setFont(new Font("Maiden Orange", Font.PLAIN, 20)); // NOI18N
        text.setForeground(new java.awt.Color(225, 225, 225));
        text.setText("Your goal is to find the secret path \n" +
                "from the start to end. ");
        getContentPane().add(text);
        text.setBounds(30, 140, 260, 40);

        GLCanvas glcanvas;
        Animator animator;

        HowToPlayGlEventListener listener = new HowToPlayGlEventListener();
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

    private void backActionPerformed() {
        this.dispose();
        new HomePage().setVisible(true);
    }

}
