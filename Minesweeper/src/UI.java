import java.awt.*;
import java.awt.event.*;

import static java.lang.System.exit;

public class UI extends Platform{
    public UI(int PlatformSize, int BoomNumber) {
        super(PlatformSize, BoomNumber);
    }


    public static void main(String[] args) {
        //init Platforms
        Frame window = new Frame("Minesweeper");
        int plWidth = 640;
        int plHeight = 640;
        window.setSize(plWidth,plHeight);
        window.setBackground(Color.lightGray);
        window.setLayout(new BorderLayout());

        Panel middle = new Panel(new GridLayout(4,1,5,50));

        window.add(middle,BorderLayout.CENTER);
        Button easy = new Button("Easy");
        Button normal = new Button("Normal");
        Button hard = new Button("hard");
        Button exit = new Button("exit");
        easy.setBackground(Color.white);
        normal.setBackground(Color.white);
        hard.setBackground(Color.white);
        exit.setBackground(Color.white);

        middle.add(easy);
        middle.add(normal);
        middle.add(hard);
        middle.add(exit);

        window.setVisible(true);
        ActionListener exitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                exit(0);
            }

        };

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        exit.addActionListener(exitListener);



    }
}
