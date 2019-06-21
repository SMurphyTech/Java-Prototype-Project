package proto.Player;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

public class ProtoAttack extends ProtoPlayer {

    public static Rectangle sliceBox = new Rectangle(0, 0, 0, 0);
    private static Timer sliceTime;
    private static boolean contR, contL, contD, contU;

    public static void slice() {
        if (hit == true) {
            //you can't attack during your hitstun animation
        } else {
            attacking = true;

            if (null != dir) {
                switch (dir) {
                    case RIGHT:
                        sliceBox.setBounds(x + 30, y - 50, 80, 100);
                        contR = true;
                        break;
                    case LEFT:
                        sliceBox.setBounds(x - 110, y - 50, 80, 100);
                        contL = true;
                        break;
                    case UP:
                        sliceBox.setBounds(x - 50, y - 110, 100, 80);
                        contU = true;
                        break;
                    case DOWN:
                        sliceBox.setBounds(x - 50, y + 30, 100, 80);
                        contD = true;
                        break;
                    default:
                        break;
                }
            }

            stop();

            sliceTime = new Timer();
            sliceTime.schedule(new sliceOver(), 200);
        }
    }

    static class sliceOver extends TimerTask {

        @Override
        public void run() {

            sliceBox.setBounds(0, 0, 0, 0);
            mobile = true;
            attacking = false;
            //attack over
        }
    }

    public static void cancel() {
        sliceBox.setBounds(0, 0, 0, 0);
        mobile = true;
        attacking = false;
        System.out.println("cancelled");
    }

    public static Rectangle getSliceBox() {
        return sliceBox;
    }

}
