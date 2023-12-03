package Pages.ChooseLevel;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class ChooseLevel extends JFrame {

    public static void main(String[] args) {
        new ChooseLevel();
    }

    public ChooseLevel() {
        JButton level1 = new JButton();
        JButton level2 = new JButton();
        JButton level3 = new JButton();
        JButton level4 = new JButton();
        JButton level5 = new JButton();

        level1.setFont(new Font("Maiden Orange", Font.PLAIN, 32)); // NOI18N
        level1.setText("Level 1");
        level1.setBackground(new Color(217, 217, 217));

        level1.addActionListener(evt -> level1ActionPerformed());
        getContentPane().add(level1);
        level1.setBounds(225, 110, 150, 40);

        level2.setFont(new Font("Maiden Orange", Font.PLAIN, 32)); // NOI18N
        level2.setText("Level 2");
        level2.setBackground(new Color(217, 217, 217));

        level2.addActionListener(evt -> level2ActionPerformed());
        getContentPane().add(level2);
        level2.setBounds(225, 160, 150, 40);

        level3.setFont(new Font("Maiden Orange", Font.PLAIN, 32)); // NOI18N
        level3.setText("Level 3");
        level3.setBackground(new Color(217, 217, 217));

        level3.addActionListener(evt -> level3ActionPerformed());
        getContentPane().add(level3);
        level3.setBounds(225, 210, 150, 40);

        level4.setFont(new Font("Maiden Orange", Font.PLAIN, 32)); // NOI18N
        level4.setText("Level 3");
        level4.setBackground(new Color(217, 217, 217));

        level4.addActionListener(evt -> level4ActionPerformed());
        getContentPane().add(level4);
        level4.setBounds(225, 210, 150, 40);

        level5.setFont(new Font("Maiden Orange", Font.PLAIN, 32)); // NOI18N
        level5.setText("Level 5");
        level5.setBackground(new Color(217, 217, 217));

        level5.addActionListener(evt -> level5ActionPerformed());
        getContentPane().add(level5);
        level5.setBounds(225, 210, 150, 40);

        GLCanvas glcanvas;
        Animator animator;

        ChooseLevelGlEventListener listener = new ChooseLevelGlEventListener();
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

    private void level1ActionPerformed() {
        this.dispose();
//        new ChooseLevel().setVisible(true);
    }

    private void level2ActionPerformed() {
        this.dispose();
//        new ChooseLevel().setVisible(true);
    }

    private void level3ActionPerformed() {
        this.dispose();
//        new ChooseLevel().setVisible(true);
    }

    private void level4ActionPerformed() {
        this.dispose();
//        new ChooseLevel().setVisible(true);
    }

    private void level5ActionPerformed() {
        this.dispose();
//        new ChooseLevel().setVisible(true);
    }
}