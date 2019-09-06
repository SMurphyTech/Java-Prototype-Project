
package proto.tile;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ProtoTileLoadImage {
    public Image tileImage, crate, PHT, lava, brick, nWallBottom, nWall, nWallTop, eWall, wWall, sWall, topRCorner, topLCorner, botRCorner, botLCorner;
    
    public void load(){
        ImageIcon ic = new ImageIcon("src\\sprites\\tiles\\Crate64.png");
        crate = ic.getImage();
        ImageIcon ig = new ImageIcon("src\\sprites\\tiles\\Place Holder Tile64.png");
        PHT = ig.getImage();
        ImageIcon id = new ImageIcon("src\\sprites\\tiles\\Danger Tile .png");
        lava = id.getImage();
        ImageIcon ib = new ImageIcon("src\\sprites\\tiles\\Brick Tile.png");
        brick = ib.getImage();
        ImageIcon nwb = new ImageIcon("src\\sprites\\tiles\\northWallBottom.png");
        nWallBottom = nwb.getImage();
        ImageIcon nw = new ImageIcon("src\\sprites\\tiles\\northWall.png");
        nWall = nw.getImage();
        ImageIcon nwt = new ImageIcon("src\\sprites\\tiles\\northWallTop.png");
        nWallTop = nwt.getImage();
        ImageIcon ew = new ImageIcon("src\\sprites\\tiles\\eastWall.png");
        eWall = ew.getImage();
        ImageIcon ww = new ImageIcon("src\\sprites\\tiles\\westWall.png");
        wWall = ww.getImage();
        ImageIcon sw = new ImageIcon("src\\sprites\\tiles\\southWall.png");
        sWall = sw.getImage();
        ImageIcon trc = new ImageIcon("src\\sprites\\tiles\\topRCorner.png");
        topRCorner = trc.getImage();
        ImageIcon tlc = new ImageIcon("src\\sprites\\tiles\\topLCorner.png");
        topLCorner = tlc.getImage();
        ImageIcon brc = new ImageIcon("src\\sprites\\tiles\\botRCorner.png");
        botRCorner = brc.getImage();
        ImageIcon blc = new ImageIcon("src\\sprites\\tiles\\botLCorner.png");
        botLCorner = blc.getImage();
    }
}
