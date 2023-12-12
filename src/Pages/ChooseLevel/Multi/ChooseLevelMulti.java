package Pages.ChooseLevel.Multi;

import Maps.Map1.single.Map1;
import Maps.Map2.SinglePlayer.Map2;
import Maps.Map3.SinglePlayer.Map3;
import Maps.Map4.Single.Map4;
import Pages.Home.HomePage;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class ChooseLevelMulti extends JFrame {

    public static void main(String[] args) {
        new ChooseLevelMulti();
    }

    public ChooseLevelMulti() {
        JButton level1 = new JButton();
        JButton level2 = new JButton();
        JButton level3 = new JButton();
        JButton level4 = new JButton();

        level1.setFont(new Font("Maiden Orange", Font.PLAIN, 32)); // NOI18N
        level1.setText("Level 1");
        level1.setBackground(new Color(217, 217, 217));

        level1.addActionListener(evt -> level1ActionPerformed());
        getContentPane().add(level1);
        level1.setBounds(225, 70, 150, 40);

        level2.setFont(new Font("Maiden Orange", Font.PLAIN, 32)); // NOI18N
        level2.setText("Level 2");
        level2.setBackground(new Color(217, 217, 217));

        level2.addActionListener(evt -> level2ActionPerformed());
        getContentPane().add(level2);
        level2.setBounds(225, 120, 150, 40);

        level3.setFont(new Font("Maiden Orange", Font.PLAIN, 32)); // NOI18N
        level3.setText("Level 3");
        level3.setBackground(new Color(217, 217, 217));

        level3.addActionListener(evt -> level3ActionPerformed());
        getContentPane().add(level3);
        level3.setBounds(225, 170, 150, 40);

        level4.setFont(new Font("Maiden Orange", Font.PLAIN, 32)); // NOI18N
        level4.setText("Level 4");
        level4.setBackground(new Color(217, 217, 217));

        level4.addActionListener(evt -> level4ActionPerformed());
        getContentPane().add(level4);
        level4.setBounds(225, 220, 150, 40);

        GLCanvas glcanvas;
        Animator animator;

        ChooseLevelMultiGlEventListener listener = new ChooseLevelMultiGlEventListener();
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

    private void level1ActionPerformed() {
        this.dispose();
        new Map1().setVisible(true);
    }

    private void level2ActionPerformed() {
        this.dispose();
        new Map2().setVisible(true);
    }

    private void level3ActionPerformed() {
        this.dispose();
        new Map3().setVisible(true);
    }

    private void level4ActionPerformed() {
        this.dispose();
        new Map4().setVisible(true);
    }

    public void processWindowEvent(final WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            new HomePage().setVisible(true);
        }
    }

}