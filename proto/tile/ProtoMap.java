package proto.tile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ProtoMap extends ProtoMapList{
    
    private static ArrayList<ProtoTile> tilearray = new ArrayList<ProtoTile>();
    
    public ProtoMap(){
        loadMapList();
        System.out.println("HIW");
    }
    
    private void loadMapList(){
        load();
    }
    
    //methods that load, update, and display the tiles
    public static void loadMap(String filename) throws IOException {
        ArrayList lines = new ArrayList();
        int width = 0;
        int height = 0;

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while (true) {
            String line = reader.readLine();
            // no more lines to read
            if (line == null) {
                reader.close();
                break;
            }

            if (!line.startsWith("!")) {
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

    }

    public static void updateTiles() {
        for (int i = 0; i < tilearray.size(); i++) {
            ProtoTile t = (ProtoTile) tilearray.get(i);
            t.update();
        }
    }

    public static ArrayList<ProtoTile> getTilearray() {
        return tilearray;
    }
    

}
