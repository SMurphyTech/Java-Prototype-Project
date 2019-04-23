package proto;

import proto.Player.*;
import proto.enemies.*;
import proto.tile.ProtoTile;
import proto.tile.ProtoMap;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ProtoPaint extends JPanel implements ActionListener, Runnable {

    final int DELAY = 10;
    private Timer timer;
    private Thread animator;
    static ProtoPlayer player;
    static ProtoEnemySeeker seek;
    static ProtoMap map;
    public static String currentMap = "src/maps/TestMap2.txt";

    public ProtoPaint() {
        loadFrame();
    }

    private void loadFrame() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);

        player = new ProtoPlayer();
        seek = new ProtoEnemySeeker(100, 100);

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

        //draws player so that the x and y coords are at the center of the sprite
        g2d.drawImage(player.getPlayer(), player.getX() - player.getSpacerX(), player.getY() - player.getSpacerY(), this);

        g2d.setColor(Color.WHITE);
        g2d.drawRect(seek.getaHitBox().x, seek.getaHitBox().y, seek.getaHitBox().width, seek.getaHitBox().height);

        g2d.drawRect(player.getRect().x, player.getRect().y, player.getRect().width, player.getRect().height);
        g2d.drawRect(player.getRect2().x, player.getRect2().y, player.getRect2().width, player.getRect2().height);
        g2d.drawRect(player.getFootright().x, player.getFootright().y, player.getFootright().width, player.getFootright().height);
        g2d.drawRect(player.getFootleft().x, player.getFootleft().y, player.getFootleft().width, player.getFootleft().height);
        g2d.drawRect(player.getYellowRed().x, player.getYellowRed().y, player.getYellowRed().width, player.getYellowRed().height);

        g2d.drawRect(ProtoAttack.getSliceBox().x, ProtoAttack.getSliceBox().y, ProtoAttack.getSliceBox().width, ProtoAttack.getSliceBox().height);

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
        player.update();
        if (player.isPaused() == false) {
            seek.update();
        }
    }

    public static void changeMaps() {
        map.getTilearray().clear();
 
        currentMap = "src/maps/TestMap.txt";
        
        try {
            map.loadMap(currentMap);
            System.out.println("works");
        } catch (IOException ex) {
            Logger.getLogger(ProtoPaint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ProtoPlayer getPlayer() {
        return player;
    }

    public static ProtoEnemySeeker getSeek() {
        return seek;
    }
    
}
