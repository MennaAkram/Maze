package Core;

import javax.media.opengl.GL;

public class Player {
    public static int step = 10;
    public float x, y;
    public int i, j;

    public void updateXY() {
        x = (float) (i - 1) * step;
        y = (float) (j - 1) * step;
    }
    public void updateIJ() {
        i = (int) ((x/step) + 1);
        j = (int) ((y/step) + 1);
    }

    public Directions direction = Directions.IDEAL;

    public void Draw(GL gl, int texture) {
        float sz = step * 2;

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, texture);
        gl.glBegin(GL.GL_QUADS);

        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(x, y, -1.0f);

        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(x, y + sz, -1.0f);

        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(x + sz, y + sz, -1.0f);

        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(x + sz, y, -1.0f);

        gl.glEnd();
        gl.glDisable(GL.GL_BLEND);
    }



    public void moveUP() {
        direction = Directions.UP;
        x -= step;
        updateIJ();
    }

    public void moveDown() {
        direction = Directions.DOWN;
        x += step;
        updateIJ();
    }

    public void moveRight() {
        direction = Directions.RIGHT;
        y += step;
        updateIJ();
    }

    public void moveLeft() {
        direction = Directions.LEFT;
        y -= step;
        updateIJ();
    }
}
