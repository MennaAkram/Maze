package Maps.Map3.Multiplayer;

import Maps.Map3.SinglePlayer.Map3Listener;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class Map3MultiPlayer extends JFrame {
    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(i%4);
//        }
        new Map3MultiPlayer();
    }


    public Map3MultiPlayer() {
        GLCanvas glcanvas;
        Animator animator;

        Map3MultiPlayerListener listener = new Map3MultiPlayerListener();
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
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }

}
