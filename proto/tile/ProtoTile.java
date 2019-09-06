package proto.tile;

import proto.ProtoPaint;
import proto.enemies.*;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import proto.Player.ProtoPlayer;

public class ProtoTile extends ProtoTileLoadImage {

    private int tileX, tileY, speedX, speedY, type;
    public static Rectangle r;
    private ProtoPlayer player = ProtoPaint.getPlayer();
    boolean clear = true;

    public ProtoTile(int x, int y, int typeInt) {
        loadImage();
        tileX = x * 64;
        tileY = y * 64;

        type = typeInt;

        r = new Rectangle();

        setImage();

    }

    private void loadImage() {
        load();
    }

    public void setImage() {
        switch (type) {
            case 1:
                //open space
                break;
            case 2:
                tileImage = brick;
                break;
            case 3:
                //door sprite goes here
                break;
            case 4:
                //door sprite goes here
                break;
            case 5:
                //door sprite goes here
                break;
            case 6:
                //door sprite goes here
                break;
            case 7:
                tileImage = nWallBottom;
                break;
            case 8:
                tileImage = nWall;
                break;
            case 9:
                tileImage = nWallTop;
                break;
            case 10:
                tileImage = eWall;
                break;
            case 11:
                tileImage = sWall;
                break;
            case 12:
                tileImage = wWall;
                break;
            case 13:
                tileImage = topRCorner;
                break;
            case 14:
                tileImage = topLCorner;
                break;
            case 15:
                tileImage = botRCorner;
                break;
            case 16:
                tileImage = botLCorner;
                break;
            default:
                type = 0;
                break;
        }
    }

    public void update() {
        speedY = 0;

        tileY += speedY;

        speedX = 0;

        tileX += speedX;

        r.setBounds(tileX, tileY, 32 * 2, 32 * 2);

        if (r.intersects(ProtoPlayer.yellowRed)) {
            checkSideCollision(ProtoPlayer.footleft, ProtoPlayer.footright);
            checkVerticalCollision(ProtoPlayer.rect, ProtoPlayer.rect2);
        }

        for (int s = 0; s < ProtoEnemy.remainingEnemies.size(); s++) {
            if (ProtoEnemy.remainingEnemies.get(s).collision == true) {
                checkMonsterCollision(ProtoEnemy.remainingEnemies.get(s));
            }
        }

        setImage();
    }

    public void checkVerticalCollision(Rectangle rtop, Rectangle rbot) {

        if (rtop.intersects(r)) {
            if (type >= 7 && type <= 13) {
                if (player.isMovingD() == false) {
                    player.setDy(0);
                } else if (player.getDy() < 0) {
                    player.setDy(0);
                }
            }
            //north exit
            if (type == 3) {
                if (player.leaving == false) {
                    player.leaving = true;
                    try {
                        ProtoPaint.changeMaps("north");
                    } catch (IOException ex) {
                        Logger.getLogger(ProtoTile.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }

        if (rbot.intersects(r)) {
            if (type >= 7 && type <= 13) {
                if (player.isMovingU() == false) {
                    player.setDy(0);
                } else if (player.getDy() > 0) {
                    player.setDy(0);
                }
            }
            //south exit
            if (type == 4) {
                if (player.leaving == false) {
                    player.leaving = true;
                    try {
                        ProtoPaint.changeMaps("south");
                    } catch (IOException ex) {
                        Logger.getLogger(ProtoTile.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }

    }

    public void checkSideCollision(Rectangle leftfoot, Rectangle rightfoot) {

        if (rightfoot.intersects(r)) {
            if (type >= 7 && type <= 13) {
                if (player.isMovingL() == false) {
                    player.setDx(0);
                } else if (player.getDx() > 0) {
                    player.setDx(0);
                }
            }
            // east exit
            if (type == 5) {
                if (player.leaving == false) {
                    player.leaving = true;
                    try {
                        ProtoPaint.changeMaps("east");
                    } catch (IOException ex) {
                        Logger.getLogger(ProtoTile.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        if (leftfoot.intersects(r)) {
            if (type >= 7 && type <= 13) {
                if (player.isMovingR() == false) {
                    player.setDx(0);
                } else if (player.getDx() < 0) {
                    player.setDx(0);
                }
            }
            //west exit
            if (type == 6) {
                if (player.leaving == false) {
                    player.leaving = true;
                    try {
                        ProtoPaint.changeMaps("west");
                    } catch (IOException ex) {
                        Logger.getLogger(ProtoTile.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        for (int s = 0; s < ProtoEnemy.remainingEnemies.size(); s++) {
            if (rightfoot.intersects(ProtoEnemy.remainingEnemies.get(s).getaHitBox())) {
                player.takeDamage();
            }
        }

        for (int f = 0; f < ProtoEnemy.remainingEnemies.size(); f++) {
            ProtoEnemy mon = ProtoEnemy.remainingEnemies.get(f);
            for (int m = 0; m < mon.projectiles.size(); m++) {
                if (rightfoot.intersects(mon.projectiles.get(m))) {
                    player.takeDamage();
                }
            }
        }

    }

    public void checkMonsterCollision(ProtoEnemy m) {
        if (m.aHitBox.intersects(r)) {
            if (type >= 7 && type <= 13) {
                m.dx = 0;
            }
        }
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public int getType() {
        return type;
    }

    public Image getTileImage() {
        return tileImage;
    }

}
