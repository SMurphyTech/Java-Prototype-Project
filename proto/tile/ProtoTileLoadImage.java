
package proto.tile;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ProtoTileLoadImage {
    public Image tileImage, crate, PHT, lava, brick;
    
    public void load(){
        ImageIcon ic = new ImageIcon("src\\sprites\\tiles\\Crate64.png");
        crate = ic.getImage();
        ImageIcon ig = new ImageIcon("src\\sprites\\tiles\\Place Holder Tile64.png");
        PHT = ig.getImage();
        ImageIcon id = new ImageIcon("src\\sprites\\tiles\\Danger Tile .png");
        lava = id.getImage();
        ImageIcon ib = new ImageIcon("src\\sprites\\tiles\\Brick Tile.png");
        brick = ib.getImage();
    }
}
