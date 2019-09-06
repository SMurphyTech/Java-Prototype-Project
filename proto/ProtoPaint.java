package proto;

import proto.Player.*;
import proto.enemies.*;
import proto.Items.*;
import proto.tile.ProtoTile;
import proto.tile.ProtoMap;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import proto.message.ProtoMessage;
import proto.object.*;

public class ProtoPaint extends JPanel implements ActionListener, Runnable {
    //Window: 1600 by 960 (25 tiles by 15 tiles)
    final int DELAY = 10;
    private Timer timer;
    private Thread animator;
    static ProtoPlayer player;
    static ProtoMap map;
    static ProtoEnemySeeker seek;
    static ProtoEnemyList enemy;
    static ProtoItemList items;
    static ProtoObjectList objects;
    static ProtoMessage msg;
    public static String previousMap;
    public static String currentMap = "src\\maps\\TestMap_1_4.txt";
    public boolean textPresent;

    public ProtoPaint() {
        loadFrame();
    }

    private void loadFrame() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);

        player = new ProtoPlayer();
        map = new ProtoMap();
        //seek = new ProtoEnemySeeker(500, 500);
        //ProtoEnemy.remainingEnemies.add(seek);
        enemy = new ProtoEnemyList();
        items = new ProtoItemList();
        objects = new ProtoObjectList();
        ProtoObjectList.sign2.setXY(800, 400);
        ProtoObject.presentObjects.add(ProtoObjectList.sign2);
        msg = new ProtoMessage();

        try {
            map.loadMap(currentMap);
        } catch (IOException ex) {
            Logger.getLogger(ProtoPaint.class.getName()).log(Level.SEVERE, null, ex);
        }

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void addNotify() {
        super.addNotify();

        //updates images
        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        //--- prevents visuals from smearing (really cool to turn off!) ---
        super.paintComponent(g);

        doDrawing(g);

        //--- May not be needed ---
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        paintTiles(g2d);

        g2d.setColor(Color.YELLOW);
        //draws items
        for (int i = 0; i < ProtoItem.inOverworld.size(); i++) {
//            g2d.drawRect(ProtoItem.inOverworld.get(i).ihitbox.x, ProtoItem.inOverworld.get(i).ihitbox.y, ProtoItem.inOverworld.get(i).ihitbox.width,
//                    ProtoItem.inOverworld.get(i).ihitbox.height);

            g2d.drawImage(ProtoItem.inOverworld.get(i).currentImage, ProtoItem.inOverworld.get(i).x, ProtoItem.inOverworld.get(i).y, this);
        }
        
        g2d.setColor(Color.MAGENTA);
        //draws objects
        for (int o = 0; o < ProtoObject.presentObjects.size(); o++) {

            g2d.drawImage(ProtoObject.presentObjects.get(o).currentImage, ProtoObject.presentObjects.get(o).imageX, ProtoObject.presentObjects.get(o).imageY, this);

            //g2d.drawRect(ProtoObject.presentObjects.get(o).ohitbox.x, ProtoObject.presentObjects.get(o).ohitbox.y, ProtoObject.presentObjects.get(o).ohitbox.width,
                    //ProtoObject.presentObjects.get(o).ohitbox.height);
        }

        //draws player so that the x and y coords are at the center of the sprite
        g2d.drawImage(player.getPlayer(), player.getX() - player.getSpacerX(), player.getY() - player.getSpacerY(), this);

        g2d.setColor(Color.WHITE);

        //draws enemies
        for (int s = 0; s < ProtoEnemy.remainingEnemies.size(); s++) {
            g2d.drawRect(ProtoEnemy.remainingEnemies.get(s).getaHitBox().x, ProtoEnemy.remainingEnemies.get(s).getaHitBox().y,
                    ProtoEnemy.remainingEnemies.get(s).getaHitBox().width, ProtoEnemy.remainingEnemies.get(s).getaHitBox().height);

            g2d.drawImage(ProtoEnemy.remainingEnemies.get(s).getCurrentImage(), ProtoEnemy.remainingEnemies.get(s).getImageX(),
                    ProtoEnemy.remainingEnemies.get(s).getImageY(), this);

            ProtoEnemy mon = ProtoEnemy.remainingEnemies.get(s);
            for (int m = 0; m < mon.projectiles.size(); m++) {
                g2d.drawRect(mon.projectiles.get(m).x, mon.projectiles.get(m).y, mon.projectiles.get(m).width, mon.projectiles.get(m).height);
            }

            if (mon instanceof ProtoEnemyLaser) {
                Rectangle laser = mon.projectiles.get(0);
                int x = 0;
                if (ProtoEnemyLaser.facing == ProtoEnemyLaser.Facing.RIGHT) {
                    x = laser.x;
                } else if (ProtoEnemyLaser.facing == ProtoEnemyLaser.Facing.LEFT) {
                    x = laser.x + laser.width;
                }
                for (int e = -1; e < laser.width / 99; e++) {

                    if (ProtoEnemyLaser.facing == ProtoEnemyLaser.Facing.LEFT) {
                        //System.out.println(x);
                        g2d.drawImage(ProtoEnemyLaser.laser2, x - 102, laser.y - 3, this);
                        x -= 99;
                    }
                    if (ProtoEnemyLaser.facing == ProtoEnemyLaser.Facing.RIGHT) {
                        //System.out.println(x);
                        g2d.drawImage(ProtoEnemyLaser.laser2, x, laser.y - 3, this);
                        x += 99;
                    }

                }
            }
        }

        
        //draws key counter
        g2d.drawImage(ProtoItemsKey.keyCounter, 20, 100, this);
        g2d.drawImage(ProtoMessage.x_sprite, 75, 120, this);
        g2d.drawImage(ProtoItemsKey.updateKeyCounter(), 100, 125, this);

        //draws text
        //g2d.drawRect(250, 500, 1100, 300);
        
        msg.drawUpdate(g2d, this);
        

        /*
        g2d.drawRect(player.getRect().x, player.getRect().y, player.getRect().width, player.getRect().height);
        g2d.drawRect(player.getRect2().x, player.getRect2().y, player.getRect2().width, player.getRect2().height);
        g2d.drawRect(player.getFootright().x, player.getFootright().y, player.getFootright().width, player.getFootright().height);
        g2d.drawRect(player.getFootleft().x, player.getFootleft().y, player.getFootleft().width, player.getFootleft().height);
        g2d.drawRect(player.getYellowRed().x, player.getYellowRed().y, player.getYellowRed().width, player.getYellowRed().height);

        g2d.drawRect(ProtoAttack.getSliceBox().x, ProtoAttack.getSliceBox().y, ProtoAttack.getSliceBox().width, ProtoAttack.getSliceBox().height);
         */
        switch (player.getHealth()) {
            case 4:
                g2d.drawImage(player.getFullHeart(), 7 * 2, 5 * 2, this);
                g2d.drawImage(player.getFullHeart(), 47 * 2, 5 * 2, this);
                g2d.drawImage(player.getFullHeart(), 87 * 2, 5 * 2, this);
                g2d.drawImage(player.getFullHeart(), 127 * 2, 5 * 2, this);
                break;
            case 3:
                g2d.drawImage(player.getFullHeart(), 7 * 2, 5 * 2, this);
                g2d.drawImage(player.getFullHeart(), 47 * 2, 5 * 2, this);
                g2d.drawImage(player.getFullHeart(), 87 * 2, 5 * 2, this);
                g2d.drawImage(player.getEmptyHeart(), 127 * 2, 5 * 2, this);
                break;
            case 2:
                g2d.drawImage(player.getFullHeart(), 7 * 2, 5 * 2, this);
                g2d.drawImage(player.getFullHeart(), 47 * 2, 5 * 2, this);
                g2d.drawImage(player.getEmptyHeart(), 87 * 2, 5 * 2, this);
                g2d.drawImage(player.getEmptyHeart(), 127 * 2, 5 * 2, this);
                break;
            case 1:
                g2d.drawImage(player.getFullHeart(), 7 * 2, 5 * 2, this);
                g2d.drawImage(player.getEmptyHeart(), 47 * 2, 5 * 2, this);
                g2d.drawImage(player.getEmptyHeart(), 87 * 2, 5 * 2, this);
                g2d.drawImage(player.getEmptyHeart(), 127 * 2, 5 * 2, this);
                break;
            case 0:
                g2d.drawImage(player.getEmptyHeart(), 7 * 2, 5 * 2, this);
                g2d.drawImage(player.getEmptyHeart(), 47 * 2, 5 * 2, this);
                g2d.drawImage(player.getEmptyHeart(), 87 * 2, 5 * 2, this);
                g2d.drawImage(player.getEmptyHeart(), 127 * 2, 5 * 2, this);
                break;
        }
    }

    private void paintTiles(Graphics g) {
        for (int i = 0; i < map.getTilearray().size(); i++) {
            ProtoTile t = (ProtoTile) map.getTilearray().get(i);
            g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }

    @Override
    public void run() {
        /*
        while (true) {
            repaint();      
        }
         */

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {

                String msg = String.format("Thread interrupted: %s", e.getMessage());

                JOptionPane.showMessageDialog(this, msg, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            beforeTime = System.currentTimeMillis();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        step();
    }

    private void step() {
        map.updateTiles();

        if (player.isPaused() == false) {
        //updates objects
        for (int o = 0; o < ProtoObject.presentObjects.size(); o++) {
            ProtoObject.presentObjects.get(o).update();
        }

        player.update();

            for (int s = 0; s < ProtoEnemy.remainingEnemies.size(); s++) {
                ProtoEnemy.remainingEnemies.get(s).update();
            }

            //updates overworld items
            for (int i = 0; i < ProtoItem.inOverworld.size(); i++) {
                ProtoItem.inOverworld.get(i).update();
            }
            //updates inventory items
            for (int i = 0; i < player.inventory.size(); i++) {
                player.inventory.get(i).update();
            }

        }
    }

    public static void changeMaps(String whichway) throws IOException {
        ArrayList<String> Xcontainer = new ArrayList<>();
        ArrayList<ArrayList> enemyXcontainer = new ArrayList<ArrayList>();

        previousMap = currentMap;

        map.getTilearray().clear();

        if (whichway == "north") {
            ProtoMap.currentMapY -= 1;

            Xcontainer = ProtoMap.mapX.get(ProtoMap.currentMapX);
            currentMap = Xcontainer.get(ProtoMap.currentMapY);
            

        } else if (whichway == "south") {
            ProtoMap.currentMapY += 1;

            Xcontainer = ProtoMap.mapX.get(ProtoMap.currentMapX);
            currentMap = Xcontainer.get(ProtoMap.currentMapY);
            

        } else if (whichway == "east") {
            ProtoMap.currentMapX += 1;

            Xcontainer = ProtoMap.mapX.get(ProtoMap.currentMapX);
            currentMap = Xcontainer.get(ProtoMap.currentMapY);


        } else if (whichway == "west") {
            ProtoMap.currentMapX -= 1;

            Xcontainer = ProtoMap.mapX.get(ProtoMap.currentMapX);
            currentMap = Xcontainer.get(ProtoMap.currentMapY);
            
        }

        items.updateItems();
        objects.updateObjects();

        try {
            map.loadMap(currentMap);
            //loads Enemies
            ProtoEnemy.loadEnemies(ProtoEnemyList.monsterTest());
        } catch (IOException ex) {
            Logger.getLogger(ProtoPaint.class.getName()).log(Level.SEVERE, null, ex);
        }

        //determines where the player appears when they enters a room
        ProtoMap.setEntrances();

        if (whichway == "north") {
            player.setY(ProtoMap.southEntranceY);
            player.setX(ProtoMap.southEntranceX);
        } else if (whichway == "south") {
            player.setY(ProtoMap.northEntranceY);
            player.setX(ProtoMap.northEntranceX);
        } else if (whichway == "east") {
            player.setX(ProtoMap.westEntranceX);
            player.setY(ProtoMap.westEntranceY);
        } else if (whichway == "west") {
            player.setX(ProtoMap.eastEntranceX);
            player.setY(ProtoMap.eastEntranceY);
        }
        
        
    }

    public static ProtoPlayer getPlayer() {
        return player;
    }
    
    
    public static ProtoMessage getMsg() {
        return msg;
    }
    
    public static ProtoEnemyList getEmy() {
        return enemy;
    }

}
