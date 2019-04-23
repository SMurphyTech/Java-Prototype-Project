
package proto.tile;

import java.util.ArrayList;

public class ProtoMapList {
    public String map1, map2;
    private static ArrayList<String> maps = new ArrayList<String>();
    
    public void load() {
        map1 = "src/maps/TestMap.txt";
        map2 = "src/maps/TestMap2.txt";
        
        maps.add(map1);
        maps.add(map2);
    }
            
}
