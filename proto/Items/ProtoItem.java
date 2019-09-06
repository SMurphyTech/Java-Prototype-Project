
package proto.Items;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import proto.Player.ProtoPlayer;
import proto.ProtoPaint;

public class ProtoItem {
    public int id;
    public static ArrayList<ProtoItem> inOverworld = new ArrayList<ProtoItem>();
    public int x, y, width, height; //only need in overworld
    public Rectangle ihitbox = new Rectangle(0, 0, 0, 0);
    public Image currentImage;
    public int imageX, imageY;
    public String name;
    public boolean collected = false;
    
    public static ProtoPlayer player = ProtoPaint.getPlayer();
    
    public void update() {
        if(inOverworld.contains(this)){
            ihitbox.setBounds(x, y, width, height);
        }else{
            ihitbox.setBounds(0, 0, 0, 0);
        }
        
        customUpdate();
        
        //collect item
        if(player.rect.intersects(ihitbox) || player.rect2.intersects(ihitbox)){
            player.inventory.add(this);
            System.out.println(player.inventory);
            inOverworld.remove(this);
            ProtoItemList.collected.add(this);
            collected = true;
        }
    }
    
    public void customUpdate(){
        //item-specific rules go here
    }

    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }
}
