package Maps.Map4.Single;

import Core.*;
import Core.texture.TextureReader;
import Pages.ChooseLevel.Single.ChooseLevel;
import Pages.Lose.Lose;
import Pages.win.Win;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLException;
import javax.media.opengl.glu.GLU;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;



import static Core.Utils.*;
import static Maps.Map1.single.Map1Listener.AddHighScore;
import static Maps.Map1.single.Map1Listener.ReadHighScore;
import static java.awt.event.KeyEvent.*;

public class Map4Listener extends AnimListener {
    JFrame frame =null;
    String[] textureNames = {"Ghost1.png" ,"Ghost2.png" ,"Ghost3.png" ,"Ghost4.png", "Maps//Map4.png", "Player.png",  "Star 2.png"};
    TextureReader.Texture[] texture = new TextureReader.Texture[textureNames.length];
    int[] textures = new int[textureNames.length];
    int animationPlayerIndex;
    int[][] map = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
            {0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 2},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0},
            {2, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0},
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0},
    };

    int row = map.length;
    int col = map[0].length;
    Player player = new Player();
    Random random = new Random();
    Ghost ghost;
    AStarAlgorithm aStarAlgorithm;
    int time;
    Timer timer = new Timer(1000, e -> time++);
    Timer ghostTimerMove = new Timer(500, e -> handleGhostMove());
    boolean pause = false;
    int lives = 3;
    ArrayList<BounceBalls> balls = new ArrayList<>(5);
    int score;
    int highScore = ReadHighScore();

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        aStarAlgorithm = new AStarAlgorithm(map);

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

        resetPlayer(map, row, col, player);
        initGhost();
        timer.start();
        ghostTimerMove.start();
        addBalls();
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer

        gl.glPushMatrix();
        gl.glTranslated(130, 10, 0);
        gl.glScaled(0.55, 0.95, 1);
        DrawBackground(gl, textures[4]);
        gl.glPopMatrix();

        animationPlayerIndex = animationPlayerIndex % 4;

        handlePlayerMove();
        handleLose();
        handleBallsCollision();

        gl.glPushMatrix();
        gl.glTranslated(135, 385, 0);
        gl.glScaled(0.95, 1.1, 1);
        gl.glRotated(-90, 0, 0, 1);
        player.Draw(gl, textures[5]);
        DrawBalles(gl);
        drawGhost(gl);
        gl.glPopMatrix();
        if (score > highScore) {
            AddHighScore(score);
            highScore = ReadHighScore();
        }
        handelWinning();
        try {
            drawString(gl, 8, 8, "Time: " + time);
            drawString(gl, 8, 40, "Lives: " + lives);
        } catch (GLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {
    }

    private void drawGhost(GL gl) {
        ghost.Draw(gl, textures[animationPlayerIndex]);
    }

    private void initGhost() {
        ArrayList<Pair> validPositions = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] == 1) {
                    validPositions.add(new Pair(i, j));
                }
            }
        }
        Pair item = validPositions.get(random.nextInt(validPositions.size()));
        ghost = new Ghost(item.i, item.j);
    }

    public BitSet keyBits = new BitSet(256);

    public void handlePlayerMove() {
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
                if (map[player.i - 1][player.j] == 1 || map[player.i - 1][player.j] == 2) {
                    player.moveUP();
                }
            }
            case DOWN -> {
                if (player.i + 1 >= col) return;
                if (map[player.i + 1][player.j] == 1 || map[player.i + 1][player.j] == 2) {
                    player.moveDown();
                }
            }
            case RIGHT -> {
                if (player.j + 1 >= row) return;
                if (map[player.i][player.j + 1] == 1 || map[player.i][player.j + 1] == 2) {
                    player.moveRight();
                }
            }
            case LEFT -> {
                if (player.j - 1 < 0) return;
                if (map[player.i][player.j - 1] == 1 || map[player.i][player.j - 1] == 2) {
                    player.moveLeft();
                }
            }
        }
    }

    private void handleLose() {
        if (player.i == ghost.i && player.j == ghost.j) {
            if (lives == 1) {
                frame.dispose();
                new Lose().setVisible(true);
            } else {
                lives--;
                resetPlayer(map, row, col, player);
            }
        }
    }

    private void handelWinning() {
        if ((map[player.i][player.j] == 2)) { // Winning
            frame.dispose();
            new Win().setVisible(true);
            ChooseLevel.enable2 = true;
            ChooseLevel.enable3 = true;
            ChooseLevel.enable4 = true;
        }
    }

    private void handleGhostMove() {
        List<Directions> optimalPath = aStarAlgorithm.findOptimalPath(ghost.i, ghost.j, player.i, player.j);

        if (!optimalPath.isEmpty()) {
            Directions nextMove = optimalPath.get(0);

            switch (nextMove) {
                case UP -> {
                    ghost.moveUP();
                    animationPlayerIndex++;
                }
                case DOWN -> {
                    ghost.moveDown();
                    animationPlayerIndex++;
                }
                case RIGHT -> {
                    ghost.moveRight();
                    animationPlayerIndex++;
                }
                case LEFT -> {
                    ghost.moveLeft();
                    animationPlayerIndex++;
                }
                case IDEAL -> animationPlayerIndex++;
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
        if (keyCode == VK_SPACE) {
            pause = !pause;
            if (pause) {
                timer.stop();
                ghostTimerMove.stop();
                Map4.animator.stop();
                JOptionPane.showMessageDialog(null, "Click Double Space to Resume", "Pause", JOptionPane.WARNING_MESSAGE);
            } else {
                Map4.animator.start();
                timer.start();
                ghostTimerMove.start();
            }
        }
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
            ball.Draw(gl, textures[6]);
        }
    }
}