/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proto.object;

import java.awt.Rectangle;
import javax.swing.ImageIcon;
import proto.ProtoPaint;
import proto.enemies.ProtoEnemy;
import proto.enemies.ProtoEnemyList;

public class ProtoObjectEDoor extends ProtoObject {
    public boolean open = false;
    String orientation;

    public ProtoObjectEDoor(int x, int y, String orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        if ("vertical".equals(orientation)) {
            width = 84;
            height = 148;
            ImageIcon d = new ImageIcon("src\\sprites\\objects\\VDoor.png");
            currentImage = d.getImage();
        } else if ("horizontal".equals(orientation)) {
            width = 148;
            height = 84;
            ImageIcon d = new ImageIcon("src\\sprites\\objects\\HDoor.png");
            currentImage = d.getImage();
        }
        spacerX = 0;
        spacerY = 0;
    }
    
    @Override
    public void update() {
        if(open == true){
            ProtoObject.presentObjects.remove(this);
        }
        
        ohitbox.setBounds(x, y, width, height);
        imageX = x - spacerX;
        imageY = y - spacerY;

        checkOpen();
        checkCollision(player.yellowRed, player.rect, player.rect2, player.footright, player.footleft);
    }

    public void checkOpen() {
        if(ProtoEnemy.remainingEnemies.isEmpty()){
            ProtoObject.presentObjects.remove(this);
            open = true;
        }
    }

    public void checkCollision(Rectangle area, Rectangle upbox, Rectangle downbox, Rectangle rightbox, Rectangle leftbox) {
        if (area.intersects(ohitbox)) {
            if (upbox.intersects(ohitbox)) {

                if (player.isMovingD() == false) {
                    player.setDy(0);
                } else if (player.getDy() < 0) {
                    player.setDy(0);
                }

            } else if (downbox.intersects(ohitbox)) {

                if (player.isMovingU() == false) {
                    player.setDy(0);
                } else if (player.getDy() > 0) {
                    player.setDy(0);
                }

            } else if (rightbox.intersects(ohitbox)) {

                if (player.isMovingL() == false) {
                    player.setDx(0);
                } else if (player.getDx() > 0) {
                    player.setDx(0);
                }

            } else if (leftbox.intersects(ohitbox)) {

                if (player.isMovingR() == false) {
                    player.setDx(0);
                } else if (player.getDx() < 0) {
                    player.setDx(0);
                }

            }
        }
    }
}


