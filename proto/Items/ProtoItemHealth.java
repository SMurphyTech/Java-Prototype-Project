
package proto.Items;

import javax.swing.ImageIcon;

public class ProtoItemHealth extends ProtoItem {
    
    public ProtoItemHealth(int x, int y) {
        this.x = x;
        this.y = y;
        width = 40;
        height = 40;
        name = "Extra Health";
        
        ImageIcon h = new ImageIcon("src\\sprites\\items\\Item Heart.png");
        currentImage = h.getImage();
    }
    
    @Override
    public void customUpdate(){
        //automatically heals player
        if(player.inventory.contains(this)){
            player.health += 1;
            player.inventory.remove(this);
        }
    }
}
