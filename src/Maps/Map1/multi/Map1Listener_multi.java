package Maps.Map1.multi;

import Core.AnimListener;
import Core.Directions;
import Core.Player;
import Core.texture.TextureReader;
import Pages.win.Player1Win;
import Pages.win.Player2Win;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.BitSet;

import static java.awt.event.KeyEvent.*;

public class Map1Listener_multi extends AnimListener {
    JFrame frame =null;
    String[] textureNames = {"Maps//Map1.png", "Player.png", "1.png",};
    TextureReader.Texture[] texture = new TextureReader.Texture[textureNames.length];
    int[] textures = new int[textureNames.length];
    int animationPlayerIndex = 1;
    int[][] map = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {-1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0},
            {0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };


    int row = map.length;
    int col = map[0].length;
    Player player1 = new Player();
    Player player2 = new Player();

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClearColor(0.16f, 0.52f, 0.52f, 1.0f);

        gl.glLoadIdentity();
        gl.glOrtho(0, 600, 0, 400, 0, 1.0);

        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glGenTextures(textureNames.length, textures, 0);

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
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] == -1) {
                    player2.i = i;
                    player2.j = j;
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

        handleKeyPress1();
        handleKeyPress2();
        handelWinning();

        gl.glPushMatrix();
        gl.glTranslated(135, 385, 0);
        gl.glScaled(1.6, 1.85, 1);
        gl.glRotated(-90, 0, 0, 1);
        player1.Draw(gl, textures[1]);
        player2.Draw(gl, textures[2]);
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

    private void handelWinning() {
        if ((map[player1.i][player1.j] == 2)) { // Winning
            frame.dispose();
            new Player1Win().setVisible(true);
        } else if ( map[player2.i][player2.j] == 2) {
            frame.dispose();
            new Player2Win().setVisible(true);
        }
    }

    public void handleKeyPress1() {
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

        switch (player1.direction) {
            case IDEAL -> {
            }
            case UP -> {
                if (player1.i - 1 < 0) return;
                if ((map[player1.i - 1][player1.j] == 1) || (map[player1.i - 1][player1.j] == 2)) {
                    player1.moveUP();
                }
            }
            case DOWN -> {
                if (player1.i + 1 >= col) return;
                if ((map[player1.i + 1][player1.j] == 1) || (map[player1.i + 1][player1.j] == 2)) {
                    player1.moveDown();
                }
            }
            case RIGHT -> {
                if (player1.j + 1 >= row) return;
                if ((map[player1.i][player1.j + 1] == 1) || (map[player1.i][player1.j + 1] == 2)) {
                    player1.moveRight();
                }
            }
            case LEFT -> {
                if (player1.j - 1 < 0) return;
                if ((map[player1.i][player1.j - 1] == 1) || (map[player1.i][player1.j - 1] == 2)) {
                    player1.moveLeft();
                }
            }
        }
    }

    public void handleKeyPress2() {
        if (isKeyPressed(VK_W)) {
            player2.direction = Directions.UP;
        }
        if (isKeyPressed(VK_S)) {
            player2.direction = Directions.DOWN;
        }
        if (isKeyPressed(VK_D)) {
            player2.direction = Directions.RIGHT;
        }
        if (isKeyPressed(VK_A)) {
            player2.direction = Directions.LEFT;
        }
        if (!(isKeyPressed(VK_W) || isKeyPressed(VK_S) || isKeyPressed(VK_D) || isKeyPressed(VK_A))) {
            player2.direction = Directions.IDEAL;
        }

        switch (player2.direction) {
            case IDEAL -> {
            }
            case UP -> {
                if (player2.i - 1 < 0) return;
                if ((map[player2.i - 1][player2.j] == 1) || (map[player2.i - 1][player2.j] == 2)) {
                    player2.moveUP();
                }
            }
            case DOWN -> {
                if (player2.i + 1 >= col) return;
                if ((map[player2.i + 1][player2.j] == 1) || (map[player2.i + 1][player2.j] == 2)) {
                    player2.moveDown();
                }
            }
            case RIGHT -> {
                if (player2.j + 1 >= row) return;
                if ((map[player2.i][player2.j + 1] == 1) || (map[player2.i][player2.j + 1] == 2)) {
                    player2.moveRight();
                }
            }
            case LEFT -> {
                if (player2.j - 1 < 0) return;
                if ((map[player2.i][player2.j - 1] == 1) || (map[player2.i][player2.j - 1] == 2)) {
                    player2.moveLeft();
                }
            }


        }
    }

    @Override
    public void keyTyped (KeyEvent e){
    }

    @Override
    public void keyPressed (KeyEvent e){
        int keyCode = e.getKeyCode();
        keyBits.set(keyCode);
    }

    @Override
    public void keyReleased (KeyEvent e){
        int keyCode = e.getKeyCode();
        keyBits.clear(keyCode);
        switch (keyCode) {
            case VK_UP, VK_DOWN, VK_RIGHT, VK_LEFT -> player1.direction = Directions.IDEAL;
        }
    }

    public boolean isKeyPressed ( final int keyCode){
        return keyBits.get(keyCode);
    }
}
