package proto.message;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import proto.Player.ProtoPlayer;
import proto.ProtoPaint;

public class ProtoMessage extends ProtoMessageLoadImage {

    public String msg = "default";
    public static ArrayList<Image> letterImgs = new ArrayList<>();
    public static int xlet = 300, yline = 550;
    public static boolean loading = false;
    
    public static ProtoPlayer player = ProtoPaint.getPlayer();

    public ProtoMessage() {
        load();
    }

    public static void loadMessage(String msg) {
        letterImgs.clear();

        player.paused = true;
        
        for (int chara = 0; chara < msg.length(); chara++) {
            switch (msg.charAt(chara)) {
                case 'a':
                    letterImgs.add(a_sprite);
                    break;
                case 'b':
                    letterImgs.add(b_sprite);
                    break;
                case 'c':
                    letterImgs.add(c_sprite);
                    break;
                case 'd':
                    letterImgs.add(d_sprite);
                    break;
                case 'e':
                    letterImgs.add(e_sprite);
                    break;
                case 'f':
                    letterImgs.add(f_sprite);
                    break;
                case 'g':
                    letterImgs.add(g_sprite);
                    break;
                case 'h':
                    letterImgs.add(h_sprite);
                    break;
                case 'i':
                    letterImgs.add(i_sprite);
                    break;
                case 'j':
                    letterImgs.add(j_sprite);
                    break;
                case 'k':
                    letterImgs.add(k_sprite);
                    break;
                case 'l':
                    letterImgs.add(l_sprite);
                    break;
                case 'm':
                    letterImgs.add(m_sprite);
                    break;
                case 'n':
                    letterImgs.add(n_sprite);
                    break;
                case 'o':
                    letterImgs.add(o_sprite);
                    break;
                case 'p':
                    letterImgs.add(p_sprite);
                    break;
                case 'q':
                    letterImgs.add(q_sprite);
                    break;
                case 'r':
                    letterImgs.add(r_sprite);
                    break;
                case 's':
                    letterImgs.add(s_sprite);
                    break;
                case 't':
                    letterImgs.add(t_sprite);
                    break;
                case 'u':
                    letterImgs.add(u_sprite);
                    break;
                case 'v':
                    letterImgs.add(v_sprite);
                    break;
                case 'w':
                    letterImgs.add(w_sprite);
                    break;
                case 'x':
                    letterImgs.add(x_sprite);
                    break;
                case 'y':
                    letterImgs.add(y_sprite);
                    break;
                case 'z':
                    letterImgs.add(z_sprite);
                    break;
                case 'A':
                    letterImgs.add(A_sprite);
                    break;
                case 'B':
                    letterImgs.add(B_sprite);
                    break;
                case 'C':
                    letterImgs.add(C_sprite);
                    break;
                case 'D':
                    letterImgs.add(D_sprite);
                    break;
                case 'E':
                    letterImgs.add(E_sprite);
                    break;
                case 'F':
                    letterImgs.add(F_sprite);
                    break;
                case 'G':
                    letterImgs.add(G_sprite);
                    break;
                case 'H':
                    letterImgs.add(H_sprite);
                    break;
                case 'I':
                    letterImgs.add(I_sprite);
                    break;
                case 'J':
                    letterImgs.add(J_sprite);
                    break;
                case 'K':
                    letterImgs.add(K_sprite);
                    break;
                case 'L':
                    letterImgs.add(L_sprite);
                    break;
                case 'M':
                    letterImgs.add(M_sprite);
                    break;
                case 'N':
                    letterImgs.add(N_sprite);
                    break;
                case 'O':
                    letterImgs.add(O_sprite);
                    break;
                case 'P':
                    letterImgs.add(P_sprite);
                    break;
                case 'Q':
                    letterImgs.add(Q_sprite);
                    break;
                case 'R':
                    letterImgs.add(R_sprite);
                    break;
                case 'S':
                    letterImgs.add(S_sprite);
                    break;
                case 'T':
                    letterImgs.add(T_sprite);
                    break;
                case 'U':
                    letterImgs.add(U_sprite);
                    break;
                case 'V':
                    letterImgs.add(V_sprite);
                    break;
                case 'W':
                    letterImgs.add(W_sprite);
                    break;
                case 'X':
                    letterImgs.add(X_sprite);
                    break;
                case 'Y':
                    letterImgs.add(Y_sprite);
                    break;
                case 'Z':
                    letterImgs.add(Z_sprite);
                    break;
                case '.':
                    letterImgs.add(period_sprite);
                    break;
                case ',':
                    letterImgs.add(comma_sprite);
                    break;
                case '!':
                    letterImgs.add(exclam_sprite);
                    break;
                case '?':
                    letterImgs.add(quest_sprite);
                    break;
                case '\'':
                    letterImgs.add(apost_sprite);
                    break;
                case ' ':
                    letterImgs.add(freespace);
                    break;
                case '1':
                    letterImgs.add(one);
                    break;
                case '2':
                    letterImgs.add(two);
                    break;
                case '3':
                    letterImgs.add(three);
                    break;
                case '4':
                    letterImgs.add(four);
                    break;
                case '5':
                    letterImgs.add(five);
                    break;
                case '6':
                    letterImgs.add(six);
                    break;
                case '7':
                    letterImgs.add(seven);
                    break;
                case '8':
                    letterImgs.add(eight);
                    break;
                case '9':
                    letterImgs.add(nine);
                    break;
                case '0':
                    letterImgs.add(zero);
                    break;
            }

        }

    }

    public static void drawUpdate(Graphics2D g2d, ImageObserver io) {
        
        int lx = 300;
        yline = 550;
        int plus = 0;
        for (Image letterImg : letterImgs) {
            //g2d.drawImage(textbox, 250, 500, io);

            g2d.drawImage(letterImg, lx + plus, yline, io);

            if (lx + plus >= 1260) {
                plus = 0;
                yline += 50;
            } else {
                plus += letterImg.getWidth(io) + 6;
            }
        }
    }
    
    public static void removeText(){
        letterImgs.clear();
        player.paused = false;
    }
            
}
