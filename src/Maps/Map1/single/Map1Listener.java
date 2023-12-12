package Maps.Map1.single;

import Core.*;
import Core.texture.TextureReader;
import static Core.Utils.DrawBackground;
import Pages.ChooseLevel.Single.ChooseLevel;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;
import java.util.Scanner;

import static Core.Utils.resetPlayer;
import static java.awt.event.KeyEvent.*;

public class Map1Listener extends AnimListener {
    String[] textureNames = {"Maps//Map1.png", "Player.png", "Star 2.png"};
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
    Player player = new Player();
    Random random = new Random();
    ArrayList<BounceBalls> balls = new ArrayList<>(5);
    int score;
    int highScore = ReadHighScore();

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

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

        resetPlayer(map, row, col, player);
        addBalls();
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer

        gl.glPushMatrix();
        gl.glTranslated(130, 10, 0);
        gl.glScaled(0.55, 0.95, 1);
        DrawBackground(gl, textures[0]);
        gl.glPopMatrix();

        animationPlayerIndex = animationPlayerIndex % 4;

        handleKeyPress();
        handleBallsCollision();

        gl.glPushMatrix();
        gl.glTranslated(135, 385, 0);
        gl.glScaled(1.6, 1.85, 1);
        gl.glRotated(-90, 0, 0, 1);
        player.Draw(gl, textures[1]);
        DrawBalles(gl);
        gl.glPopMatrix();
        if (score > highScore) {
            AddHighScore(score);
            highScore = ReadHighScore();
        }
        handelWinning();
    }


    private void addBalls() {
        ArrayList<Pair> validPositions = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] == 1) {
                    validPositions.add(new Pair(i, j));
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            Pair item = validPositions.get(random.nextInt(validPositions.size()));
            balls.add(new BounceBalls(item.i, item.j));
            validPositions.remove(item);
        }
    }

    private void handelWinning() {
        if ((map[player.i][player.j] == 2)) { // Winning
            System.out.println("Win");
            //  frame.dispose();
            ChooseLevel.enable = true;
        }
    }

    private void handleBallsCollision() {
        BounceBalls ballToRemove = null;
        for (BounceBalls ball : balls) {
            if (player.i == ball.i && player.j == ball.j) {
                ballToRemove = ball;
                score = score + 10;
                System.out.println(score);
                break;
            }
        }
        if (ballToRemove != null) {
            balls.remove(ballToRemove);
        }
    }

    public static void AddHighScore(int score) {
        try (FileWriter f = new FileWriter("Score.txt", false);
             Scanner input = new Scanner(new File("Score.txt"))) {
            int highScore = input.hasNext() ? input.nextInt() : 0;
            if (score > highScore) highScore = score;
            f.write(highScore + "");
            f.flush();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static int ReadHighScore() {
        try (Scanner input = new Scanner(new File("Score.txt"));) {
            return (input.hasNext()) ? input.nextInt() : 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void DrawBalles(GL gl) {
        for (BounceBalls ball : balls) {
            ball.Draw(gl, textures[2]);
        }
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
            player.direction = Directions.UP;
        }
        if (isKeyPressed(VK_DOWN)) {
            player.direction = Directions.DOWN;
        }
        if (isKeyPressed(VK_RIGHT)) {
            player.direction = Directions.RIGHT;
        }
        if (isKeyPressed(VK_LEFT)) {
            player.direction = Directions.LEFT;
        }
        if (!(isKeyPressed(VK_UP) || isKeyPressed(VK_DOWN) || isKeyPressed(VK_RIGHT) || isKeyPressed(VK_LEFT))) {
            player.direction = Directions.IDEAL;
        }

        switch (player.direction) {
            case IDEAL -> {
            }
            case UP -> {
                if (player.i - 1 < 0) return;
                if ((map[player.i - 1][player.j] == 1) || (map[player.i - 1][player.j] == 2)) {
                    player.moveUP();
                }
            }
            case DOWN -> {
                if (player.i + 1 >= col) return;
                if ((map[player.i + 1][player.j] == 1) || (map[player.i + 1][player.j] == 2)) {
                    player.moveDown();
                }
            }
            case RIGHT -> {
                if (player.j + 1 >= row) return;
                if ((map[player.i][player.j + 1] == 1) || (map[player.i][player.j + 1] == 2)) {
                    player.moveRight();
                }
            }
            case LEFT -> {
                if (player.j - 1 < 0) return;
                if ((map[player.i][player.j - 1] == 1) || (map[player.i][player.j - 1] == 2)) {
                    player.moveLeft();
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
            case VK_UP, VK_DOWN, VK_RIGHT, VK_LEFT -> player.direction = Directions.IDEAL;
        }
    }

    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }
}