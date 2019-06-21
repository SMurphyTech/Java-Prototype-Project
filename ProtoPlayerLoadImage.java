
package proto.Player;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import proto.framework.ProtoAnimation;

public class ProtoPlayerLoadImage {
    static Image player, playerDown, playerUp, playerRight, playerLeft, playerRunR, playerRunR2, playerRunL, playerRunL2, playerRunU, playerRunU2, playerRunU3, 
            playerRunD, playerRunD2, playerSwipeR, playerSwipeL, playerSwipeU, playerSwipeD, playerHurt, playerHurt2, playerHurtSparks, fullHeart, emptyHeart, nothing;
    
    static ProtoAnimation currentAnim, storeAnim, playerMoveR, playerMoveL, playerMoveU, playerMoveD, playerIdleR, playerIdleL, playerIdleU, playerIdleD, hitFrames, invinciFramesR, 
            invinciFramesL, invinciFramesU, invinciFramesD, playerSwipeAnimR, playerSwipeAnimL, playerSwipeAnimU, playerSwipeAnimD;
    
    public ArrayList<Image> hearts = new ArrayList<Image>();
    
    public void load(){
        ImageIcon non = new ImageIcon("src\\sprites\\Nothing.png"); 
        nothing = non.getImage();
        
        ImageIcon pd = new ImageIcon("src\\sprites\\player\\Green Boi Down64.png");
        playerDown = pd.getImage();
        ImageIcon pu = new ImageIcon("src\\sprites\\player\\Green Boi Up64.png");
        playerUp = pu.getImage();
        ImageIcon pr = new ImageIcon("src\\sprites\\player\\Green Boi Right64.png");
        playerRight = pr.getImage();
        ImageIcon pl = new ImageIcon("src\\sprites\\player\\Green Boi Left64.png");
        playerLeft = pl.getImage();
        player = playerDown;
        
        playerIdleD = new ProtoAnimation();
        playerIdleD.addFrame(playerDown, 100);
        
        playerIdleU = new ProtoAnimation();
        playerIdleU.addFrame(playerUp, 100);
        
        playerIdleR = new ProtoAnimation();
        playerIdleR.addFrame(playerRight, 100);
        
        playerIdleL = new ProtoAnimation();
        playerIdleL.addFrame(playerLeft, 100);        
        
        currentAnim = playerIdleD;
        
        //running sprites
        ImageIcon pmd1 = new ImageIcon("src\\sprites\\player\\Green Boi MoveD 1.png");
        ImageIcon pmd2 = new ImageIcon("src\\sprites\\player\\Green Boi MoveD 2.png");
        playerRunD = pmd1.getImage();
        playerRunD2 = pmd2.getImage();
        ImageIcon pmu1 = new ImageIcon("src\\sprites\\player\\Green Boi MoveU 1.png");
        ImageIcon pmu2 = new ImageIcon("src\\sprites\\player\\Green Boi MoveU 2.png");
        ImageIcon pmu3 = new ImageIcon("src\\sprites\\player\\Green Boi MoveU 3.png");
        playerRunU = pmu1.getImage();
        playerRunU2 = pmu2.getImage();
        playerRunU3 = pmu3.getImage();
        ImageIcon pmr1 = new ImageIcon("src\\sprites\\player\\Green Boi MoveR 1.png");
        ImageIcon pmr2 = new ImageIcon("src\\sprites\\player\\Green Boi MoveR 2.png");
        playerRunR = pmr1.getImage();
        playerRunR2 = pmr2.getImage();
        ImageIcon pml1 = new ImageIcon("src\\sprites\\player\\Green Boi MoveL 1.png");
        ImageIcon pml2 = new ImageIcon("src\\sprites\\player\\Green Boi MoveL 2.png");
        playerRunL = pml1.getImage();
        playerRunL2 = pml2.getImage();
        
        playerMoveD = new ProtoAnimation();
        playerMoveD.addFrame(playerRunD, 100);
        playerMoveD.addFrame(playerRunD2, 100);
        
        playerMoveU = new ProtoAnimation();
        playerMoveU.addFrame(playerRunU, 100);
        playerMoveU.addFrame(playerRunU2, 100);
        playerMoveU.addFrame(playerRunU3, 100);
        playerMoveU.addFrame(playerRunU2, 100);
        
        playerMoveR = new ProtoAnimation();
        playerMoveR.addFrame(playerRunR, 100);
        playerMoveR.addFrame(playerRight, 100);
        playerMoveR.addFrame(playerRunR2, 100);
        playerMoveR.addFrame(playerRight, 100);
        
        playerMoveL = new ProtoAnimation();
        playerMoveL.addFrame(playerRunL, 100);
        playerMoveL.addFrame(playerLeft, 100);
        playerMoveL.addFrame(playerRunL2, 100);
        playerMoveL.addFrame(playerLeft, 100);
        
        //attack sprites
        ImageIcon psr = new ImageIcon("src\\sprites\\player\\Green Boi Right Swipe.png");
        playerSwipeR = psr.getImage();
        ImageIcon psl = new ImageIcon("src\\sprites\\player\\Green Boi Left Swipe.png");
        playerSwipeL = psl.getImage();
        ImageIcon psu = new ImageIcon("src\\sprites\\player\\Green Boi Up Swipe.png");
        playerSwipeU = psu.getImage();
        ImageIcon psd = new ImageIcon("src\\sprites\\player\\Green Boi Down Swipe.png");
        playerSwipeD = psd.getImage();
        
        playerSwipeAnimR = new ProtoAnimation();
        playerSwipeAnimR.addFrame(playerSwipeR, 100);
        
        playerSwipeAnimL = new ProtoAnimation();
        playerSwipeAnimL.addFrame(playerSwipeL, 100);        
        
        playerSwipeAnimU = new ProtoAnimation();
        playerSwipeAnimU.addFrame(playerSwipeU, 100);
        
        playerSwipeAnimD = new ProtoAnimation();
        playerSwipeAnimD.addFrame(playerSwipeD, 100);
        
        //hurt sprites
        ImageIcon ph = new ImageIcon("src\\sprites\\player\\Green Boi Hurt.png");
        ImageIcon ph2 = new ImageIcon("src\\sprites\\player\\Green Boi Hurt2.png");
        ImageIcon phs = new ImageIcon("src\\sprites\\player\\Green Boi Hurt sparks.png");
        playerHurt = ph.getImage();
        playerHurt2 = ph2.getImage();
        playerHurtSparks = phs.getImage();
        
        hitFrames = new ProtoAnimation();
        hitFrames.addFrame(playerHurt, 100);
        hitFrames.addFrame(playerHurt2, 100);
        
        invinciFramesR = new ProtoAnimation();
        invinciFramesR.addFrame(playerRight, 100);
        invinciFramesR.addFrame(nothing, 100);

        invinciFramesL = new ProtoAnimation();
        invinciFramesL.addFrame(playerLeft, 100);
        invinciFramesL.addFrame(nothing, 100);

        invinciFramesU = new ProtoAnimation();
        invinciFramesU.addFrame(playerUp, 100);
        invinciFramesU.addFrame(nothing, 100);

        invinciFramesD = new ProtoAnimation();
        invinciFramesD.addFrame(playerDown, 100);
        invinciFramesD.addFrame(nothing, 100);
        
        //Health image setups
        ImageIcon fhi = new ImageIcon("src\\sprites\\player\\Full Heart 66.png");
        fullHeart = fhi.getImage();
        ImageIcon ehi = new ImageIcon("src\\sprites\\player\\Empty Heart 66.png");
        emptyHeart = ehi.getImage();
        
        hearts.add(fullHeart);
        hearts.add(fullHeart);
        hearts.add(fullHeart);
        hearts.add(fullHeart);    
    }    
    
    public Image getFullHeart() {
        return fullHeart;
    }

    public Image getEmptyHeart() {
        return emptyHeart;
    }

    public ArrayList<Image> getHearts() {
        return hearts;
    }
}
