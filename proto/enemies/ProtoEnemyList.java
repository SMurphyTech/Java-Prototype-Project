package proto.enemies;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import proto.tile.ProtoMap;

public class ProtoEnemyList {

    public static ArrayList<ProtoEnemySeeker> seekSupply = new ArrayList<>();
    public static ArrayList<ProtoEnemySeeker> shootSupply = new ArrayList<>();
    public static ProtoEnemySeeker testSeek1, testSeek2, testSeek3, testSeek4, testSeek5,
            testSeek6, testSeek7, testSeek8, testSeek9, testSeek10;
    public static ProtoEnemyShooter shoot1, shoot2, shoot3, shoot4, shoot5, shoot6, shoot7, shoot8, shoot9, shoot10;
    public static ProtoEnemyCharger charge1, charge2;
    public static ProtoEnemySpinner spin1;
    public static ProtoEnemyLaser laser1, laser2;
    public static monsterList en;

    public ProtoEnemyList() {
        load();
    }

    public void load() {

        spin1 = new ProtoEnemySpinner(900, 100);
        shoot1 = new ProtoEnemyShooter(600, 400);
        shoot2 = new ProtoEnemyShooter(200, 300);
        charge1 = new ProtoEnemyCharger(1100, 500);
        charge2 = new ProtoEnemyCharger(1250, 700);
        laser1 = new ProtoEnemyLaser(0, 0);
        laser2 = new ProtoEnemyLaser(0, 0);

        seekSupply.add(testSeek1);
        seekSupply.add(testSeek2);
        seekSupply.add(testSeek3);
        seekSupply.add(testSeek4);
        seekSupply.add(testSeek5);
        seekSupply.add(testSeek6);
        seekSupply.add(testSeek7);
        seekSupply.add(testSeek8);
        seekSupply.add(testSeek9);
        seekSupply.add(testSeek10);
        
        
    }


    public static ArrayList monsterTest() throws IOException {
        en = new monsterList(ProtoMap.currentMapX, ProtoMap.currentMapY);
        return en.loadMons();

    }

    public static class monsterList {

        int mapx, mapy;
        ArrayList<ProtoEnemy> monsters = new ArrayList<>();
        ArrayList<String> Xcontainer = new ArrayList<>();
        String room;
        boolean found_room;

        public monsterList(int mapx, int mapy) {
            // + 1 because it isn't an arraylist
            this.mapx = mapx + 1;
            this.mapy = mapy + 1;

            Xcontainer = ProtoMap.mapX.get(mapx);
            this.room = Xcontainer.get(mapy);
        }

        public ArrayList loadMons() throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader("src\\enemy_list\\enemy_placement.txt"));
            while (true) {
                String line = reader.readLine();
                System.out.println(line);
                if (line == null) {
                    System.out.println("Enemies for this room not found");
                    reader.close();
                    break;
                } else if (line.startsWith("X" + mapx)) {
                    System.out.println("X found");
                    if (line.startsWith("Y" + mapy, 2)) {
                        System.out.println("Y found");
                        found_room = true;
                    }
                } else if (found_room == true) {
                    if (line.startsWith("end")) {
                        reader.close();
                        break;
                    } else {
                        IDmonster(findID(line), findXY(line, "x"), findXY(line, "y"));
                        System.out.println(monsters);
                    }
                }
            }
            return monsters;
        }
        
        public int findID(String entry){
            return Integer.parseInt(entry.substring(0, 3));
        }

        public int findXY(String entry, String either) {
            int startIndex = 0, endIndex = 0;
            for (int n = 0; n < entry.length(); n++) {
                if ("x".equals(either)) {
                    if (entry.charAt(n) == '(') {
                        startIndex = n + 1;
                    }
                    if (entry.charAt(n) == ',') {
                        endIndex = n;
                    }
                } else if ("y".equals(either)) {
                    if (entry.charAt(n) == ',') {
                        startIndex = n + 1;
                    }
                    if (entry.charAt(n) == ')') {
                        endIndex = n;
                    }
                }
            }
            return Integer.parseInt(entry.substring(startIndex, endIndex));
        }

        public void IDmonster(int id, int x, int y) {
            switch (id) {
                case 1:
                    ProtoEnemySeeker s;
                    s = new ProtoEnemySeeker(x * 64, y * 64);
                    monsters.add(s);
                    break;
                case 2:
                    ProtoEnemyShooter sh;
                    sh = new ProtoEnemyShooter(x * 64, y * 64);
                    monsters.add(sh);
                    break;
                case 3:
                    ProtoEnemyCharger ch;
                    ch = new ProtoEnemyCharger(x * 64, y * 64);
                    monsters.add(ch);
                    break;
                default:
                    break;
            }
        }
    }
}
