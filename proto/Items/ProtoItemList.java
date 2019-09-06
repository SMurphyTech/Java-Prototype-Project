package proto.Items;

import java.util.ArrayList;
import proto.Player.ProtoPlayer;
import proto.ProtoPaint;

public class ProtoItemList {
    public ProtoPlayer player = ProtoPaint.getPlayer();
    //because the items stop appearing in a room once they are added to the inventory,
    //each item can be used only once.
    public static ArrayList<ProtoItem> collected = new ArrayList<>(); 
    public ProtoItemsKey key1, key2, key3, key4;
    public ProtoItemHealth health1, health2;
    
    public ProtoItemList(){
        key1 = new ProtoItemsKey(0, 0);
        key2 = new ProtoItemsKey(0, 0);
        key3 = new ProtoItemsKey(0, 0);
        key4 = new ProtoItemsKey(0, 0);
        health1 = new ProtoItemHealth(0, 0);
        health2 = new ProtoItemHealth(0, 0);
    }
    
    public void updateItems(){
        
        ProtoItem.inOverworld.clear();
        
        if(null != ProtoPaint.currentMap)switch (ProtoPaint.currentMap) {
            case "src\\maps\\TestMap_3_4.txt":
                addItem(key1, 8, 7);
                break;
            case "src\\maps\\TestMap_4_5.txt":
                addItem(health1, 5, 12);
                break;
            case "src\\maps\\TestMap_4_6.txt":
                addItem(key2, 5, 6);
                addItem(key3, 20, 6);
                break;
            case "src\\maps\\TestMap_3_3.txt":
                addItem(key4, 9, 3);
                break;
            default:
                break;
        }
    }
    
    public void addItem(ProtoItem k, int x, int y){
        if(k.collected == false){
            k.setXY(x * 64, y * 64);
            
            ProtoItem.inOverworld.add(k);
        }
    }
            
}
