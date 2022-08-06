package falling.sand.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Mouse implements MouseListener, MouseMotionListener {
    private int mouseX = -1;
    private int mouseY = -1;
    private int mouseB = -1;


    public int getX() {
        return this.mouseX;
    }

    public int getY() {
        return this.mouseY;
    }

    public ClickType getButton() {
        switch(this.mouseB) {
            case 1:
                return ClickType.LeftClick;
            case 2:
                return ClickType.ScrollClick;
            case 3:
                return ClickType.RightClick;
            case 4:
                return ClickType.BackPage;
            case 5:
                return ClickType.ForwardPage;
            default:
                return ClickType.Unknown;
        }
    }

    public void resetButton() {
        this.mouseB = -1;
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        this.mouseX = event.getX();
        this.mouseY = event.getY();
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        this.mouseX = event.getX();
        this.mouseY = event.getY();
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent event) {
        this.mouseB = event.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        this.mouseB = -1;
    }
}
