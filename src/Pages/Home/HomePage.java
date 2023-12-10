package Pages.Home;

import Maps.Map4.Sounds;
import Pages.ChoosePlayer.ChoosePlayer;
import Pages.HowToPlay.HowToPlay;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {

    public static void main(String[] args) {
        new HomePage();
    }
    public static Clip voice;
    public HomePage(){
        JButton start = new JButton();
        JButton exit = new JButton();
        JButton help = new JButton();

        start.setFont(new java.awt.Font("Maiden Orange", Font.PLAIN, 32)); // NOI18N
        start.setText("Start");
        start.setBackground(new Color(217, 217, 217));

        start.addActionListener(evt -> startActionPerformed());
        getContentPane().add(start);
        start.setBounds(225, 220, 150, 40);

        help.setFont(new java.awt.Font("Maiden Orange", Font.PLAIN, 32)); // NOI18N
        help.setText("How to play");
        help.setBackground(new Color(217, 217, 217));

        help.addActionListener(evt -> helpActionPerformed());
        getContentPane().add(help);
        help.setBounds(30, 30, 170, 40);

        exit.setFont(new java.awt.Font("Maiden Orange", Font.PLAIN, 32)); // NOI18N
        exit.setText("Exit");
        exit.setBackground(new Color(217, 217, 217));

        exit.addActionListener(evt -> exitActionPerformed());
        getContentPane().add(exit);
        exit.setBounds(225, 280, 150, 40);

        GLCanvas glcanvas;
        Animator animator;

        HomePageGlEventListener listener = new HomePageGlEventListener();
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
        if (voice == null) voice = Sounds.playMusic("/music/wiki_home.wav",true);

    }

    private void exitActionPerformed() {
        System.exit(0);
    }

    private void startActionPerformed() {
        this.dispose();
        new ChoosePlayer().setVisible(true);

    }

    private void helpActionPerformed() {
        this.dispose();
        new HowToPlay().setVisible(true);
    }
}
