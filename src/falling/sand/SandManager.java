package falling.sand;

import falling.sand.input.ClickType;
import falling.sand.input.Mouse;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SandManager {

    private final List<Sand> sandList;
    private Mouse mouse;

    public SandManager() {
        this.sandList = new ArrayList<>();
    }

    public void init(Mouse mouse) {
        this.sandList.add(new Sand(100, 100));
        this.mouse = mouse;
    }

    public void update() {
        if (this.mouse.getButton() == ClickType.LeftClick) {
            // Adding sand!
            int mX = this.mouse.getX();
            int mY = this.mouse.getY();

            if (this.findSand(mX, mY) == null) {
                this.sandList.add(new Sand(mX, mY));
            }
        }

        // Sort sand
        Collections.sort(this.sandList, new Comparator<Sand>() {
            @Override
            public int compare(Sand s1, Sand s2) {
                return s1.y - s2.y;
            }
        });

        // Apply gravity to sand
        this.dropSand();
    }


    public void render(Graphics g) {
        for (Sand sand : this.sandList) {
            sand.render(g);
        }
    }

    private Sand findSand(int x, int y){
        Sand sand = null;

        for(Sand s : this.sandList) {
            if(s.x == x && s.y == y) {
                sand = s;
            }
        }

        return sand;
    }

    private boolean isEmpty(int x, int y) {
       return x >= 0 && x < Display.WIDTH && y >= 0 && y < Display.HEIGHT && this.findSand(x, y) == null;

    }
    private void dropSand() {
        for (Sand sand : this.sandList) {
          if(isEmpty(sand.x, sand.y + 1)) {
                sand.y++;
            }
        }
    }
}
