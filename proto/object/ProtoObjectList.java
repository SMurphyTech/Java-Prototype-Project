
package proto.object;

import java.util.ArrayList;
import proto.Player.ProtoPlayer;
import proto.ProtoPaint;
import proto.Items.*;

public class ProtoObjectList {
     public ProtoPlayer player = ProtoPaint.getPlayer();
     
     public static ArrayList<ProtoObjectChest> usedItems = new ArrayList<>(); 
     public ProtoItemsKey chestKey1, chestKey2;
     public ProtoItemHealth chestHealth1, chestHealth2, chestHealth3;
     public ProtoObjectChest chest1, chest2, chest3;
     public ProtoObjectDoor door1, door2;
     public ProtoObjectEDoor edoor1;
     public static ProtoObjectSign sign1, sign2;
     
     public ProtoObjectList(){
         chestKey1 = new ProtoItemsKey(0, 0);
         chestKey2 = new ProtoItemsKey(0, 0);
         chestHealth1 = new ProtoItemHealth(0, 0);
         chestHealth2 = new ProtoItemHealth(0, 0);
         chestHealth3 = new ProtoItemHealth(0, 0);
         chest1 = new ProtoObjectChest(0, 0, chestHealth2);
         chest2 = new ProtoObjectChest(0, 0, chestHealth3);
         chest3 = new ProtoObjectChest(0, 0, chestHealth1);
         door1 = new ProtoObjectDoor(0, 0, "vertical");
         door2 = new ProtoObjectDoor(0, 0, "vertical");
         edoor1 = new ProtoObjectEDoor(0, 0, "horizontal");
         sign1 = new ProtoObjectSign(0, 0, "I make very good game?");
         sign2 = new ProtoObjectSign(0, 0, "CONTROLS                                     W,A,S,D...move                                  K...attack                                       L...interact");
     }
     
     public void updateObjects(){
         
         ProtoObject.presentObjects.clear();
         
         if(null != ProtoPaint.currentMap)switch (ProtoPaint.currentMap) {
             case "src\\maps\\TestMap_1_4.txt":
                 sign2.setXY(800, 400);
                 
                 ProtoObject.presentObjects.add(sign2);
                 break;
             case "src\\maps\\TestMap_3_4.txt":
                 addObject(chest1, 18, 7);
                 break;
             case "src\\maps\\TestMap_4_4.txt":
                 addObject(door1, 20, 6);
                 addObject(door2, 21, 6);
                 break;
             case "src\\maps\\TestMap_4_5.txt":
                 addObject(chest2, 19, 11);
                 break;
             default:
                 break;
         }
     }
     
     public void addObject(ProtoObject object, int x, int y){
         object.setXY(x * 64, y * 64);
         
         ProtoObject.presentObjects.add(object);
     }
     
}
