
package proto.Items;

import java.awt.Image;
import javax.swing.ImageIcon;
import proto.message.ProtoMessage;

public class ProtoItemsKey extends ProtoItem{
    public static Image keyCounter;
    
    public ProtoItemsKey(int x, int y){
        this.x = x;
        this.y = y;
        width = 50;
        height = 70;
        name = "Key";
        
        ImageIcon k = new ImageIcon("src\\sprites\\items\\Key.png");
        keyCounter = k.getImage();
        currentImage = k.getImage();
    }
    
    public static Image updateKeyCounter(){
        int count = 0;
        for (ProtoItem i : player.inventory) {
            if(i instanceof ProtoItemsKey){
                count += 1;
            }
        }
        if(count < 10){
            return ProtoMessage.numbers.get(count);
        }else{
            return ProtoMessage.numbers.get(0);
        }
    }
}
