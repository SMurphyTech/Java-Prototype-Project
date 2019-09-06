
package proto.object;

import java.awt.Rectangle;
import javax.swing.ImageIcon;
import proto.ProtoPaint;
import proto.message.ProtoMessage;

public class ProtoObjectSign extends ProtoObject{
    String message;
    
    private ProtoMessage msger = ProtoPaint.getMsg();
    
    public ProtoObjectSign(int x, int y, String message){
        this.x = x;
        this.y = y;
        width = 77;
        height = 77;
        this.message = message;
        
        ImageIcon s = new ImageIcon("src\\sprites\\objects\\sign.png");
        currentImage = s.getImage();
    }
    
    @Override
    public void update() {
        ohitbox.setBounds(x, y, width, height);
        imageX = x - spacerX;
        imageY = y - spacerY;
        
        checkRead();
        checkCollision(player.yellowRed, player.rect, player.rect2, player.footright, player.footleft);
    }
    
    public void checkRead(){
        for(int box = 0; box < player.hitboxes.size(); box++){
            if(player.hitboxes.get(box).intersects(ohitbox) && player.interacting == true){
                msger.loadMessage(message);
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
