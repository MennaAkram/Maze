package Maps.Map3.Multiplayer;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Map3MultiPlayer extends JFrame {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException {
        new Map3MultiPlayer();
    }

    public Map3MultiPlayer() throws UnsupportedAudioFileException, IOException {
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
