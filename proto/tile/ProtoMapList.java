
package proto.tile;

import java.util.ArrayList;

public class ProtoMapList {
    public String map1, map2, map3, map4;
    public int enter1X, enter2X, enter3X, enter4X;
    public int enter1Y, enter2Y, enter3Y, enter4Y;
    public int exit1X, exit2X, exit3X, exit4X;
    public int exit1Y, exit2Y, exit3Y, exit4Y;
    public static ArrayList<String> maps = new ArrayList<String>();
    
    public static ArrayList<Integer> enterCoordsX = new ArrayList<Integer>();
    public static ArrayList<Integer> enterCoordsY = new ArrayList<Integer>();
    
    public static ArrayList<Integer> exitCoordsX = new ArrayList<Integer>();
    public static ArrayList<Integer> exitCoordsY = new ArrayList<Integer>();
    public static int mapCount = 0;
    
    public void load() {
        map1 = "src\\maps\\TestMap.txt";
        map2 = "src\\maps\\TestMap3.txt";
        map3 = "src\\maps\\TestMap2.txt";
        map4 = "src\\maps\\TestMap4.txt";
        
        maps.add(map1);
        maps.add(map2);
        maps.add(map3);
        maps.add(map4);
        
        //coords for entering a new room
        enter1X = 1350;
        enter1Y = 650;
        enter2X = 800;
        enter2Y = 180;
        enter3X = 800;
        enter3Y = 180;
        enter4X = 760;
        enter4Y = 750;
        
        enterCoordsX.add(enter1X);
        enterCoordsY.add(enter1Y);
        
        enterCoordsX.add(enter2X);
        enterCoordsY.add(enter2Y);     
        
        enterCoordsX.add(enter3X);
        enterCoordsY.add(enter3Y); 
        
        enterCoordsX.add(enter4X);
        enterCoordsY.add(enter4Y); 
        
        //coords for exiting to a previous room
        exit1X = 1350;
        exit1Y = 650;
        exit2X = 800;
        exit2Y = 770;
        exit3X = 1300;
        exit3Y = 770;
        exit4X = 1310;
        exit4Y = 310;
        
        exitCoordsX.add(exit1X);
        exitCoordsY.add(exit1Y);
        
        exitCoordsX.add(exit2X);
        exitCoordsY.add(exit2Y);
        
        exitCoordsX.add(exit3X);
        exitCoordsY.add(exit3Y);
        
        exitCoordsX.add(exit4X);
        exitCoordsY.add(exit4Y);
    }   

    public static ArrayList<Integer> getEnterCoordsX() {
        return enterCoordsX;
    }

    public static ArrayList<Integer> getEnterCoordsY() {
        return enterCoordsY;
    }
    
    public static ArrayList<Integer> getExitCoordsX() {
        return exitCoordsX;
    }

    public static ArrayList<Integer> getExitCoordsY() {
        return exitCoordsY;
    }
    
    public static ArrayList<String> getMaps() {
        return maps;
    }
    
            
}
