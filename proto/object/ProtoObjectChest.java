
package proto.object;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import proto.Items.*;

public class ProtoObjectChest extends ProtoObject{
    String status = "locked";
    ProtoItem contents;
    Image closed;
    
    public ProtoObjectChest(int x, int y, ProtoItem contents){
        this.x = x;
        this.y = y;
        width = 77;
        height = 77;
        this.contents = contents;
        
        ImageIcon c = new ImageIcon("src\\sprites\\objects\\Chest.png");
        currentImage = c.getImage();
        ImageIcon cc = new ImageIcon("src\\sprites\\objects\\ChestClosed.png");
        closed = cc.getImage();
        spacerX = 3;
        spacerY = 3;
    }
    
    @Override
    public void update() {
        ohitbox.setBounds(x, y, width, height);
        imageX = x - spacerX;
        imageY = y - spacerY;
        if("opened".equals(status)){
            currentImage = closed;
        }
        
        checkOpen();
        checkCollision(player.yellowRed, player.rect, player.rect2, player.footright, player.footleft);
    }
    
    public void checkOpen() {
        for(int box = 0; box < player.hitboxes.size(); box++){
            if(player.hitboxes.get(box).intersects(ohitbox) && player.interacting == true){
                
                for(int item = 0; item < player.inventory.size(); item++){
                    
                    if(player.inventory.get(item) instanceof ProtoItemsKey){
                        
                        if("locked".equals(status)){
                            System.out.println("Obtained " + contents.name);
                            msg.removeText();
                            msg.loadMessage("Obtained " + contents.name + "!");
                            player.inventory.add(contents);            
                            //removes the key that opened the chest
                            player.inventory.remove(item);
                            status = "opened";
                        }
                    }
                }
            }
            
        }
    }
    
    public void checkCollision(Rectangle area, Rectangle upbox, Rectangle downbox, Rectangle rightbox, Rectangle leftbox){
        if(area.intersects(ohitbox)){
            if(upbox.intersects(ohitbox)){
                
                if (player.isMovingD() == false) {
                    player.setDy(0);
                } else if (player.getDy() < 0) {
                    player.setDy(0);
                }
                
            }else if(downbox.intersects(ohitbox)){
                
                if (player.isMovingU() == false) {
                    player.setDy(0);
                } else if (player.getDy() > 0) {
                    player.setDy(0);
                }
                
            }else if(rightbox.intersects(ohitbox)){
                
                if (player.isMovingL() == false) {
                    player.setDx(0);
                } else if (player.getDx() > 0) {
                    player.setDx(0);
                }
                
            }else if(leftbox.intersects(ohitbox)){
                
                if (player.isMovingR() == false) {
                    player.setDx(0);
                } else if (player.getDx() < 0) {
                    player.setDx(0);
                }
                
            }
        }
    }
}
