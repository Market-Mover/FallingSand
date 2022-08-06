package falling.sand;

import falling.sand.input.ClickType;
import falling.sand.input.Mouse;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class SandManager {

    private List<Sand> sandList;
    private Mouse mouse;

    public SandManager() {
        this.sandList = new ArrayList<Sand>();
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
    }

    public void render(Graphics g) {
        for (Sand sand : this.sandList) {
            sand.render(g);
        }
    }

    private Sand findSand(int x, int y){
        Sand sand = null;

        for(Sand s : this.sandList) {
            if(s.getX() == x && s.getY() == y) {
                sand = s;
            }
        }

        return sand;
    }

}