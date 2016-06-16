import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Basti on 30.12.2015.
 */
public class BotThread extends Thread {

    private int key;

    public BotThread(int key){
        this.key=key;
    }

    public void run() {
        try {
            Robot r = new Robot();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r.keyPress(key);
            r.keyRelease(key);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
