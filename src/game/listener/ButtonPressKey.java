package game.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import game.button.Btt;

public class ButtonPressKey extends MouseAdapter {

    private Btt button;

    public ButtonPressKey(Btt button) {
        this.button = button;
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            int mouseX = e.getX();
            int mouseY = e.getY();
            if(mouseX > button.x-button.width/2 && mouseX < button.x + button.width/2 && mouseY > button.y-button.height/2 && mouseY < button.y + button.height/2) {
                button.action();
            }
        }
    }

}
