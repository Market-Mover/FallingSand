package falling.sand;

import java.awt.Color;
import java.awt.Graphics;

public class Sand {

    public int x, y;


    public Sand(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.drawRect(this.x, this.y, 1, 1);
    }

}

