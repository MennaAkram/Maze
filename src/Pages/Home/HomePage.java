package Pages.Home;

import Pages.ChoosePlayer.ChoosePlayer;
import Pages.HowToPlay.HowToPlay;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import javax.media.opengl.GLCanvas;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import static Core.Utils.playMusic;

public class HomePage extends JFrame {
    public static Clip voice;

    public static void main(String[] args) {
        new HomePage();
    }

    public HomePage() {
        if (voice == null) voice = playMusic("src/Assets/home.wav", true);
        JButton start = new JButton();
        JButton exit = new JButton();
        JButton help = new JButton();
//        JLabel jLabel2 = new JLabel();

//        jLabel2.setIcon(new javax.swing.ImageIcon("src\\Assets\\Home.png"));
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
//        Animator animator;

        HomePageGlEventListener listener = new HomePageGlEventListener();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
//        animator = new FPSAnimator(12);
//        animator.add(glcanvas);
//        animator.start();

        setTitle("Maze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
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
