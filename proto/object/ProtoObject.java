
package proto.object;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import proto.Player.ProtoPlayer;
import proto.ProtoPaint;
import proto.message.ProtoMessage;

public class ProtoObject {
    public int x, y, width, height;
    public int id;
    public Rectangle ohitbox = new Rectangle(0, 0, 0, 0);
    public Image currentImage;
    public int imageX, imageY, spacerX, spacerY;
    public String name;
    public static ArrayList<ProtoObject> presentObjects = new ArrayList<ProtoObject>();
    
    public ProtoPlayer player = ProtoPaint.getPlayer();
    public ProtoMessage msg = ProtoPaint.getMsg();
    
    public void update(){
        //update
    }
    
    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }
}
