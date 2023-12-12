package Maps.Map4;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Map4 extends JFrame {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException {
        new Map4();
    }

    public Map4() throws UnsupportedAudioFileException, IOException {
        GLCanvas glcanvas;
        Animator animator;

        Map4Listener listener = new Map4Listener();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(18);
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
