package falling.sand;

import falling.sand.input.ClickType;
import falling.sand.input.Mouse;

import java.awt.Graphics;


public class SandManager {

    private final Sand[][] sandList;
    private Mouse mouse;

    private final int width, height;

    public SandManager() {
        this.width = Display.WIDTH;
        this.height = Display.HEIGHT;
        this.sandList = new Sand[this.width][this.height];
    }

    public void init(Mouse mouse) {
        this.mouse = mouse;
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                this.sandList[x][y] = null;
            }
        }
    }

    public void update() {
        if (this.mouse.getButton() == ClickType.LeftClick) {
            // Adding sand!
            int mX = this.mouse.getX();
            int mY = this.mouse.getY();

            if (this.isEmpty(mX, mY)) {
                this.sandList[mX][mY] = new Sand(mX, mY);
            }
        }

        // Apply gravity to sand
        this.dropSand();
    }


    public void render(Graphics g) {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (this.sandList[x][y] == null) continue;
                this.sandList[x][y].render(g);
            }
        }
    }


    private boolean isEmpty(int x, int y) {
        return x >= 0 && x < this.width && y >= 0 && y < this.height && this.sandList[x][y] == null;

    }

    private void dropSand() {
        for (int y = this.height - 1; y >= 0; y--) {
            for (int x = 0; x < this.height; x++) {
                Sand sand = this.sandList[x][y];
                if (sand == null) continue;
                  if (isEmpty(sand.x,sand.y + 1)) {
                    sand.y +=1;
                    this.sandList[x][y] = null;
                    this.sandList[x][y+1] = sand;
                } else if (isEmpty(sand.x - 1, sand.y + 1)) {
                      sand.x -=1;
                      sand.y +=1;
                      this.sandList[x][y] = null;
                      this.sandList[x-1][y+1] = sand;
                } else if (isEmpty(sand.x + 1, sand.y + 1)) {
                      sand.x  +=1;
                      sand.y  +=1;
                      this.sandList[x][y] = null;
                      this.sandList[x+1][y+1] = sand;
                }

            }
        }
    }
}