package Maps.Map3.Multiplayer;

import Core.AnimListener;
import Core.Directions;
import Core.Player;
import Core.texture.TextureReader;
import Pages.win.Win;
import com.sun.opengl.util.GLUT;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import javax.sound.sampled.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.BitSet;

import static java.awt.event.KeyEvent.*;

public class Map3MultiPlayerListener extends AnimListener {
    String gameAudio = "src/music/music.wav";
    String[] textureNames = {"Maps//Map3.png", "Player.png" , "22.png"};
    TextureReader.Texture[] texture = new TextureReader.Texture[textureNames.length];
    int[] textures = new int[textureNames.length];
    int animationPlayerIndex = 1;
    int[][] map = new int[][]{
            {0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1,0},
            {0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
            {0,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,0,1,0},
            {0,1,0,1,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,1,0,0,0,1,0,1,0,1,0,1,0},
            {0,1,0,1,0,1,0,1,0,1,1,1,1,1,0,1,1,1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,0},
            {0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0},
            {0,1,1,1,1,1,1,1,0,1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,0},
            {0,0,0,0,0,0,0,1,0,1,0,1,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0},
            {0,1,0,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,0},
            {0,1,0,0,0,1,0,0,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0},
            {0,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,0},
            {0,1,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0},
            {0,1,1,1,1,1,0,1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,1,0},
            {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0},
            {0,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,0,1,0},
            {0,1,0,0,0,1,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1,0},
            {0,1,1,1,0,1,0,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,0},//
            {0,1,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,1,0,1,0,0,0,1,0,1,0},
            {0,1,0,1,1,1,0,1,1,1,1,1,0,1,0,1,1,1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,0},
            {0,0,0,0,0,1,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,1,0},
            {0,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,0,1,1,1,0},
            {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0},
            {0,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1,1,1,1,1,1,1,0},
            {0,1,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0},
            {0,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1,1,1,1,1,0,1,0,1,1,1,1,1,0,1,1,1,0},
            {0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0},
            {0,1,0,1,1,1,0,1,1,1,1,1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,1,1,0},
            {0,1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0},
            {0,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,0},
            {0,1,0,1,0,1,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,1,0,0,0},
            {0,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0},

    };

    int row = map.length;
    int col = map[0].length;
    Player player1 = new Player();

    Player player2 = new Player();

    public Map3MultiPlayerListener() {
    }
//    private void initializeAudio() {
//        try {
//            // Load an audio file (change the path to your audio file)
//            File audioFile = new File("E:\\idea\\Maze\\src\\music\\music.wav");
//            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
//            Clip audioClip = AudioSystem.getClip();
//            audioClip.open(audioInputStream);
//
//            audioClip.start();
//            Thread.sleep(audioClip.getMicrosecondLength() / 1000);
//
//        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClearColor(0.16f, 0.52f, 0.52f, 1.0f);

        gl.glLoadIdentity();
        gl.glOrtho(0, 600, 0, 400, 0, 1.0);

        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glGenTextures(textureNames.length, textures, 0);
        GLUT glut = new GLUT();
        for (int i = 0; i < textureNames.length; i++) {
            try {
                texture[i] = TextureReader.readTexture(assetsFolderName + "//" + textureNames[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        texture[i].getWidth(), texture[i].getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        texture[i].getPixels() // Image data
                );
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] == -1) {
                    player1.i = i;
                    player1.j = j;
                    player1.updateXY();
                }
            }
        }for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] == 2) {
                    player1.updateXY();
                    new Win();
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] == -1) {
                    player2.i = i;
                    player2.j = j;
                    player2.updateXY();
                }
            }
        }for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] == 2) {
                    player2.updateXY();
                }
            }
        }


    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer

        gl.glPushMatrix();
        gl.glTranslated(130, 10, 0);
        gl.glScaled(0.55, 0.95, 1);
        DrawBackground(gl);
        gl.glPopMatrix();


        animationPlayerIndex = animationPlayerIndex % 4;

        handleKeyPress();

        gl.glPushMatrix();
        gl.glTranslated(135, 385, 0);
        gl.glScaled(1, 1.25, 1);
        gl.glRotated(-90, 0, 0, 1);
        player1.Draw(gl,textures[1]);
        player2.Draw(gl,textures[2]);
        gl.glPopMatrix();
    }

    public void DrawBackground(GL gl) {
        gl.glEnable(GL.GL_BLEND);    // Turn Blending On
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[0]);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(0.0f, 0.0f, -1.0f);

        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(600.0f, 0.0f, -1.0f);

        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(600.0f, 400.0f, -1.0f);

        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(0.0f, 400.0f, -1.0f);
        gl.glEnd();

        gl.glDisable(GL.GL_BLEND);
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {
    }

    public BitSet keyBits = new BitSet(256);

    public void handleKeyPress() {
        if (isKeyPressed(VK_UP)) {
            player1.direction = Directions.UP;
        }
        if (isKeyPressed(VK_DOWN)) {
            player1.direction = Directions.DOWN;
        }
        if (isKeyPressed(VK_RIGHT)) {
            player1.direction = Directions.RIGHT;
        }
        if (isKeyPressed(VK_LEFT)) {
            player1.direction = Directions.LEFT;
        }
        if (!(isKeyPressed(VK_UP) || isKeyPressed(VK_DOWN) || isKeyPressed(VK_RIGHT) || isKeyPressed(VK_LEFT))) {
            player1.direction = Directions.IDEAL;
        }
        if (isKeyPressed(VK_W)) {
            player2.direction = Directions.W;
        }
        if (isKeyPressed(VK_S)) {
            player2.direction = Directions.S;
        }
        if (isKeyPressed(VK_D)) {
            player2.direction = Directions.D;
        }
        if (isKeyPressed(VK_A)) {
            player2.direction = Directions.A;
        }
        if (!(isKeyPressed(VK_W) || isKeyPressed(VK_S) || isKeyPressed(VK_D) || isKeyPressed(VK_A))) {
            player2.direction = Directions.IDEAL;
        }

        switch (player1.direction) {
            case IDEAL -> {
            }
            case UP -> {
                if (player1.i - 1 < 0) return;
                if (map[player1.i - 1][player1.j] == 1) {
                    player1.moveUP();
                }
            }
            case DOWN -> {
                if (player1.i + 1 >= col) return;
                if (map[player1.i + 1][player1.j] == 1) {
                    player1.moveDown();
                }
            }
            case RIGHT -> {
                if (player1.j + 1 >= row) return;
                if (map[player1.i][player1.j + 1] == 1) {
                    player1.moveRight();
                }
            }
            case LEFT -> {
                if (player1.j - 1 < 0) return;
                if (map[player1.i][player1.j - 1] == 1) {
                    player1.moveLeft();
                }
            }
        }
        switch (player2.direction) {
            case IDEAL -> {
            }
            case W -> {
                if (player2.i - 1 < 0) return;
                if (map[player2.i - 1][player2.j] == 1) {
                    player2.moveUP();
                }
            }
            case S -> {
                if (player2.i + 1 >= col) return;
                if (map[player2.i + 1][player2.j] == 1) {
                    player2.moveDown();
                }
            }
            case D -> {
                if (player2.j + 1 >= row) return;
                if (map[player2.i][player2.j + 1] == 1) {
                    player2.moveRight();
                }
            }
            case A -> {
                if (player2.j - 1 < 0) return;
                if (map[player2.i][player2.j - 1] == 1) {
                    player2.moveLeft();
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        keyBits.set(keyCode);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        keyBits.clear(keyCode);
        switch (keyCode) {
            case VK_UP, VK_DOWN, VK_RIGHT, VK_LEFT -> player1.direction = Directions.IDEAL;
        }
    }

    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }

}
