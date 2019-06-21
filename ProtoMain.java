package proto;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class ProtoMain extends JFrame {

    public ProtoMain() {
        paint();
    }

    public void paint() {
        add(new ProtoPaint());
        
        setTitle("Proto Alpha");
        setSize(1600, 960);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            ProtoMain pm = new ProtoMain();
            pm.setVisible(true);
        });
    }

}
