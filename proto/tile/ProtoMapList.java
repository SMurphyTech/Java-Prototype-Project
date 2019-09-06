package proto.tile;

import java.util.ArrayList;

public class ProtoMapList {

    public String empty;
    //public String ;
    //public String ;
    public String mapX3Y3, mapX4Y3;
    public String mapX1Y4, mapX2Y4, mapX3Y4, mapX4Y4, mapX5Y4;
    public String mapX4Y5;
    public String mapX4Y6;

    public static ArrayList<ArrayList> mapX = new ArrayList<ArrayList>();

    public static ArrayList<String> X1 = new ArrayList<String>();
    public static ArrayList<String> X2 = new ArrayList<String>();
    public static ArrayList<String> X3 = new ArrayList<String>();
    public static ArrayList<String> X4 = new ArrayList<String>();
    public static ArrayList<String> X5 = new ArrayList<String>();
    public static ArrayList<String> X6 = new ArrayList<String>();
    public static ArrayList<String> X7 = new ArrayList<String>();

    public static int currentMapX = 0, currentMapY = 0;

    public static int northEntranceY, northEntranceX,
            southEntranceY, southEntranceX,
            eastEntranceY, eastEntranceX,
            westEntranceY, westEntranceX;

    public void load() {
        //starting map coordinates ( - 1 cause Arraylists start at 0)
        currentMapX = 1 - 1;
        currentMapY = 4 - 1;

        mapX.add(X1);
        mapX.add(X2);
        mapX.add(X3);
        mapX.add(X4);
        mapX.add(X5);
        mapX.add(X6);
        mapX.add(X7);

        mapX1Y4 = "src\\maps\\TestMap_1_4.txt";

        mapX2Y4 = "src\\maps\\TestMap_2_4.txt";

        mapX3Y3 = "src\\maps\\TestMap_3_3.txt";
        mapX3Y4 = "src\\maps\\TestMap_3_4.txt";

        mapX4Y3 = "src\\maps\\TestMap_4_3.txt";
        mapX4Y4 = "src\\maps\\TestMap_4_4.txt";
        mapX4Y5 = "src\\maps\\TestMap_4_5.txt";
        mapX4Y6 = "src\\maps\\TestMap_4_6.txt";

        mapX5Y4 = "src\\maps\\TestMap_5_4.txt";

        addMap(X1, 4, 6, mapX1Y4);

        addMap(X2, 4, 6, mapX2Y4);

        addMap(X3, 3, 6, mapX3Y3);
        addMap(X3, 4, 6, mapX3Y4);

        addMap(X4, 3, 6, mapX4Y3);
        addMap(X4, 4, 6, mapX4Y4);
        addMap(X4, 5, 6, mapX4Y5);
        addMap(X4, 6, 6, mapX4Y6);

        addMap(X5, 4, 6, mapX5Y4);
    }

    public void addMap(ArrayList x, int y, int y_capacity, String name) {
        for (int map_y = 0; map_y < y_capacity; map_y++) {

            // place holder variable is so the set() command has switch to
            if (x.size() < y_capacity) {
                String placeholder = null;
                x.add(placeholder);
            }

            if (map_y == y - 1) {
                x.set(map_y, name);
            } else {
                if (x.get(map_y) == null) {
                    x.set(map_y, empty);
                }
            }
        }
    }

}
