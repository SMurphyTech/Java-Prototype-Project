package proto.Player;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import proto.ProtoPaint;
import proto.Items.ProtoItem;
import proto.framework.ProtoAnimation;
import proto.message.ProtoMessage;
import proto.tile.ProtoMap;

public class ProtoPlayer extends ProtoPlayerLoadImage {

    public static int dy, dx, x = 400, y = 200;
    //determines where the sprites are displayed in relation to the x coordinate: (x - spacerX)
    public static int spacerX = 32, spacerY = 36;
    private final int move = 7;
    public static int health = 4, animSpeed = 7;
    public static boolean movingR, movingL, movingU, movingD;

    public static enum Dir {
        DOWN, UP, RIGHT, LEFT;
    }
    static Dir dir = Dir.DOWN;
    public static boolean paused = false, canCancel = false;
    public static boolean invincible = false, hit = false;
    public static boolean mobile = true, attacking = false, swiped = false, sliding = false, interacting = false, leaving = false, dead = false;
    public static Rectangle yellowRed = new Rectangle(0, 0, 0, 0);
    public static Rectangle rect = new Rectangle(0, 0, 0, 0);
    public static Rectangle rect2 = new Rectangle(0, 0, 0, 0);
    public static Rectangle footleft = new Rectangle(0, 0, 0, 0);
    public static Rectangle footright = new Rectangle(0, 0, 0, 0);
    public static ArrayList<Rectangle> hitboxes = new ArrayList<Rectangle>();
    private Timer invincibility, damage;
    public static ArrayList<ProtoItem> inventory = new ArrayList<ProtoItem>();
    private ProtoMessage msg = ProtoPaint.getMsg();

    public ProtoPlayer() {
        loadImage();
        hitboxes.add(rect);
        hitboxes.add(rect2);
        hitboxes.add(footleft);
        hitboxes.add(footright);
    }

    private void loadImage() {
        load();
    }

    public void update() {
        x = dx + x;
        y = dy + y;

        currentAnim.update(animSpeed);
        player = currentAnim.getImage();

        // so that the player can't jump 2 rooms at a time by touching 2 door tiles at once
        leaving = false;
        //System.out.println(ProtoMessage.letterImgs);
        //failsafe for movement (keypresses alone produce weird stutter-stops)
        if (mobile == true) {
            if (movingU == true && movingD == false) {
                dy = -move;
                dir = Dir.UP;
            }
            if (movingD == true && movingU == false) {
                dy = move;
                dir = Dir.DOWN;
            }

            if (movingR == true && movingL == false) {
                dx = move;
                dir = Dir.RIGHT;
            }
            if (movingL == true && movingR == false) {
                dx = -move;
                dir = Dir.LEFT;
            }
        }

        //failsafe for hitstun while attacking (you can move during the hitstun
        //animation if you attack on the same frame as getting hit)
        if (hit == true) {
            dx = 0;
            dy = 0;
        } else if (sliding == true) {
            if (dy > 0) {
                dy -= 1;
            }
            if (dx > 0) {
                dx -= 1;
            }
            if (dy < 0) {
                dy += 1;
            }
            if (dx < 0) {
                dx += 1;
            }
        }

        updateHitBoxes();
        updateAnimation();
    }

    public void updateHitBoxes() {
        rect.setRect(x - 10 * 2, y - 16 * 2, 19 * 2, 16 * 2);
        rect2.setRect(rect.getX(), rect.getY() + 16 * 2, 19 * 2, 20 * 2);
        yellowRed.setRect(x - 43 * 2, y - 47 * 2, 87 * 2, 97 * 2);
        footleft.setRect(x - 14 * 2, y - 10 * 2, 20 * 2, 20 * 2);
        footright.setRect(x, y - 10 * 2, 13 * 2, 20 * 2);
    }

    public void updateAnimation() {
        //sets spacers to original values 
        spacerX = 32;
        spacerY = 36;

        if (hit == true) {
            //hitstun animation
            currentAnim = hitFrames;
        } else if (attacking == true) {
            if (null != dir) //attacking animation
            {
                switch (dir) {
                    case UP:
                        spacerY = 66;
                        spacerX = 56;
                        currentAnim = playerSwipeAnimU;
                        break;
                    case DOWN:
                        spacerX = 68;
                        currentAnim = playerSwipeAnimD;
                        break;
                    case RIGHT:
                        currentAnim = playerSwipeAnimR;
                        break;
                    case LEFT:
                        spacerX = 104;
                        currentAnim = playerSwipeAnimL;
                        break;
                    default:
                        break;
                }
            }
        } else {
            //when moving
            if (movingU == true && movingR == false && movingL == false) {
                if (invincible == true) {
                    currentAnim = invinciFramesU;
                } else {
                    currentAnim = playerMoveU;
                }
            } else if (movingD == true && movingR == false && movingL == false) {
                if (invincible == true) {
                    currentAnim = invinciFramesD;
                } else {
                    currentAnim = playerMoveD;
                }
            } else if (movingR == true) {
                if (invincible == true) {
                    currentAnim = invinciFramesR;
                } else {
                    currentAnim = playerMoveR;
                }
            } else if (movingL == true) {
                if (invincible == true) {
                    currentAnim = invinciFramesL;
                } else {
                    currentAnim = playerMoveL;
                }
            } else {
                if (null != dir) //when idle
                {
                    switch (dir) {
                        case UP:
                            currentAnim = playerIdleU;
                            break;
                        case DOWN:
                            currentAnim = playerIdleD;
                            break;
                        case RIGHT:
                            currentAnim = playerIdleR;
                            break;
                        case LEFT:
                            currentAnim = playerIdleL;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    //lowers health when hit by an attack
    public void takeDamage() {
        if (invincible == false && hit == false) {
            health = health - 1;
            hit = true;
            animSpeed = 10;
            stop();
//            System.out.println(health);
            damage = new Timer();
            damage.schedule(new damageOver(), 600);
        }
    }

    class damageOver extends TimerTask {

        @Override
        public void run() {
//            System.out.println("damage anim over");
            hit = false;
            mobile = true;
            invincible = true;
            damage.cancel();
            if(health > 0){
            invincibility = new Timer();
            invincibility.schedule(new InvinceOver(), 2000);
            }else{
                paused = true;
                dead = true;
            }
        }
    }

    //the class called by the Timer after three seconds of invincibilty
    class InvinceOver extends TimerTask {

        @Override
        public void run() {
            invincible = false;
            animSpeed = 7;
//            System.out.println("invincibility over");
            invincibility.cancel(); //Terminate the timer thread
        }
    }

    public static void stop() {
        mobile = false;
        dx = 0;
        dy = 0;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (attacking == true) {
            ProtoAttack.cancel();
        }

        if (mobile == true) {
            if (key == KeyEvent.VK_W) {
                dy = -move;
                movingU = true;
                //displays upwards running animation if the player is not running
                //up or down
                if (movingR == false && movingL == false) {
                    dir = Dir.UP;
                }
            }

            if (key == KeyEvent.VK_S) {
                dy = move;
                movingD = true;
                if (movingR == false && movingL == false) {
                    dir = Dir.DOWN;
                }
            }

            if (key == KeyEvent.VK_D) {
                dx = move;
                movingR = true;
                dir = Dir.RIGHT;
            }

            if (key == KeyEvent.VK_A) {
                dx = -move;
                movingL = true;
                dir = Dir.LEFT;
            }
        }

        if (key == KeyEvent.VK_K) {
            if (swiped == false) {
                ProtoAttack.slice();
                swiped = true;
            }
        }

        if (key == KeyEvent.VK_J) {
            System.out.println(x);
            System.out.println(y);
            System.out.println(inventory);
            System.out.println(ProtoMap.currentMapX);
            System.out.println(ProtoMap.currentMapY);
            System.out.println(ProtoMap.X3);
            if (paused == false) {
                paused = true;
            } else if(dead == true){
                
            } else {
                paused = false;
            }

        }

        if (key == KeyEvent.VK_L) {
            interacting = true;
            
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            dy = 0;
            //sliding = true;
            movingU = false;
            //makes sure the idle animation doesn't flicker for a sec
            //after letting go of W
            if (movingR == false && movingL == false) {
                dir = Dir.UP;
            }
        }

        if (key == KeyEvent.VK_S) {
            dy = 0;
            //sliding = true;
            movingD = false;
            if (movingR == false && movingL == false) {
                dir = Dir.DOWN;
            }
        }

        if (key == KeyEvent.VK_D) {
            dx = 0;
            //sliding = true;
            movingR = false;
            dir = Dir.RIGHT;
        }

        if (key == KeyEvent.VK_A) {
            dx = 0;
            //sliding = true;
            movingL = false;
            dir = Dir.LEFT;
        }

        if (key == KeyEvent.VK_K) {
            swiped = false;
        }

        if (key == KeyEvent.VK_L) {
            interacting = false;
            
            if (canCancel == false) {
                canCancel = true;
            } else if (canCancel == true) {
                msg.removeText();
                canCancel = false;
                interacting = false;
            }

        }

    }

    public Image getPlayer() {
        return player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDy() {
        return dy;
    }

    public int getDx() {
        return dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public static Rectangle getYellowRed() {
        return yellowRed;
    }

    public static Rectangle getRect() {
        return rect;
    }

    public static Rectangle getRect2() {
        return rect2;
    }

    public static Rectangle getFootleft() {
        return footleft;
    }

    public static Rectangle getFootright() {
        return footright;
    }

    public static void setMobile(boolean mobile) {
        ProtoPlayer.mobile = mobile;
    }

    public boolean isMovingR() {
        return movingR;
    }

    public boolean isMovingL() {
        return movingL;
    }

    public boolean isMovingU() {
        return movingU;
    }

    public boolean isMovingD() {
        return movingD;
    }

    public static int getHealth() {
        return health;
    }

    public static Dir getDir() {
        return dir;
    }

    public static int getSpacerX() {
        return spacerX;
    }

    public static int getSpacerY() {
        return spacerY;
    }

    public static boolean isPaused() {
        return paused;
    }

    public static ProtoAnimation getCurrentAnim() {
        return currentAnim;
    }

    public static ProtoAnimation getPlayerSwipeAnimL() {
        return playerSwipeAnimL;
    }

}
