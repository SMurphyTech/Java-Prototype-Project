package proto.tile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import proto.object.*;

public class ProtoMap extends ProtoMapList {

    private static ArrayList<ProtoTile> tilearray = new ArrayList<ProtoTile>();

    public ProtoMap() {
        loadMapList();
    }

    private void loadMapList() {
        load();
    }

    //methods that load, update, and display the tiles
    public static void loadMap(String filename) throws IOException {
        ArrayList lines = new ArrayList();
        boolean enemyLocked = false;
        int width = 0;
        int height = 0;

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while (true) {
            String line = reader.readLine();
            // no more lines to read
            if (line == null) {
                reader.close();
                break;
            } else if (line.startsWith("e")) {
                enemyLocked = true;
            } else if (!line.startsWith("!")) {
                lines.add(line);
                width = Math.max(width, line.length());

            }
        }
        height = lines.size();

        for (int j = 0; j < 15; j++) {
            String line = (String) lines.get(j);
            for (int i = 0; i < width; i++) {

                if (i < line.length()) {
                    char ch = line.charAt(i);
                    ProtoTile t = new ProtoTile(i, j, Character.getNumericValue(ch));
                    tilearray.add(t);
                }

            }
        }

        if (enemyLocked == true) {
            enemyLock();
        }

    }

    public static void updateTiles() {
        for (int i = 0; i < tilearray.size(); i++) {
            ProtoTile t = (ProtoTile) tilearray.get(i);
            t.update();
        }
    }

    public static void setEntrances() {
        for (int i = 0; i < tilearray.size(); i++) {
            ProtoTile t = (ProtoTile) tilearray.get(i);
            if (t.getType() == 3) {
                //northern entrance

                northEntranceY = t.getTileY() + 100;
                northEntranceX = t.getTileX();

            } else if (t.getType() == 4) {
                //southern entrance

                southEntranceY = t.getTileY() - 50;
                southEntranceX = t.getTileX();

            } else if (t.getType() == 5) {
                //eastern entrance

                eastEntranceX = t.getTileX() - 30;
                eastEntranceY = t.getTileY();

            } else if (t.getType() == 6) {
                //western entrance

                westEntranceX = t.getTileX() + 100;
                westEntranceY = t.getTileY();

            }
        }
    }

    public static void enemyLock() {
        System.out.println("Going");
        System.out.println(tilearray);
        boolean northDoor = false;
        boolean southDoor = false;
        boolean eastDoor = false;
        boolean westDoor = false;
        for (int i = 0; i < tilearray.size(); i++) {
            ProtoTile t = (ProtoTile) tilearray.get(i);
            if (t.getType() == 3) {
                //northern door
                if (northDoor == false) {
                    ProtoObjectEDoor northEDoor = new ProtoObjectEDoor(t.getTileX(), t.getTileY(), "horizontal");
                    ProtoObject.presentObjects.add(northEDoor);
                    northDoor = true;
                }

            } else if (t.getType() == 4) {
                System.out.println("Gu");
                //southern door
                if (southDoor == false) {
                    ProtoObjectEDoor southEDoor = new ProtoObjectEDoor(t.getTileX(), t.getTileY(), "horizontal");
                    ProtoObject.presentObjects.add(southEDoor);
                    southDoor = true;
                }

            } else if (t.getType() == 5) {
                //eastern door
                if (eastDoor == false) {
                    ProtoObjectEDoor eastEDoor = new ProtoObjectEDoor(t.getTileX(), t.getTileY(), "vertical");
                    ProtoObject.presentObjects.add(eastEDoor);
                    eastDoor = true;
                }

            } else if (t.getType() == 6) {
                //western door
                if (westDoor == false) {
                    ProtoObjectEDoor westEDoor = new ProtoObjectEDoor(t.getTileX(), t.getTileY(), "vertical");
                    ProtoObject.presentObjects.add(westEDoor);
                    westDoor = true;
                }
            }
        }
    }

    public static ArrayList<ProtoTile> getTilearray() {
        return tilearray;
    }

}
