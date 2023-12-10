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
        JLabel text1 = new JLabel();
        JLabel text2 = new JLabel();

        back.setFont(new Font("Maiden Orange", Font.PLAIN, 32)); // NOI18N
        back.setText("Back");
        back.setBackground(new Color(217, 217, 217));
        back.addActionListener(evt -> backActionPerformed());
        getContentPane().add(back);
        back.setBounds(60, 280, 150, 40);

        label.setFont(new Font("Maiden Orange", Font.PLAIN, 38)); // NOI18N
        label.setOpaque(true);
        label.setBackground(new Color(0,0,0));
        label.setForeground(new java.awt.Color(225, 225, 225));
        label.setText("How to play");
        getContentPane().add(label);
        label.setBounds(30, 60, 180, 40);

        text1.setFont(new Font("Maiden Orange", Font.PLAIN, 20)); // NOI18N
        text1.setOpaque(true);
        text1.setBackground(new Color(0,0,0));
        text1.setForeground(new java.awt.Color(225, 225, 225));
        text1.setText("Your goal is to find the secret path");
        getContentPane().add(text1);
        text1.setBounds(30, 140, 260, 20);

        text2.setFont(new Font("Maiden Orange", Font.PLAIN, 20)); // NOI18N
        text2.setOpaque(true);
        text2.setBackground(new Color(0,0,0));
        text2.setForeground(new java.awt.Color(225, 225, 225));
        text2.setText("from the start to end. ");
        getContentPane().add(text2);
        text2.setBounds(30, 150, 260, 40);

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
