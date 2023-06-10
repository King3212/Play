import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.System.exit;

public class Game {
    int level;
    int PlatformSize;
    int BoomNumber;
    Frame window;

    //choose level
    public Game(int level) {
        this.level = level;
    }

    public void setLevel() {
        switch (level) {
            case 2 -> {
                PlatformSize = 16;
                BoomNumber = 40;
            }
            case 3 -> {
                PlatformSize = 25;
                BoomNumber = 90;
            }
            default -> {
                PlatformSize = 9;
                BoomNumber = 10;
            }
        }
    }

    // when in gui, it was needed no more
    private static void printLines() {
        try{
            String os = System.getProperty("os.name");
            if (os.contains("Windows")){
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            }else {
                for (int i = 0; i < 50 ; i++){
                    System.out.println();
                }
            }
        }
        catch (Exception exception){
            //Handle exception.
        }
    }

    private void click(int x, int y, int times, Platform platform, boolean mark, Button[] buttons){
        if (times == 1){
            while (! platform.firstClick(x, y,buttons)){
                platform = new Platform(PlatformSize, BoomNumber,buttons);
            }
        }
        else{
            platform.click(x, y, mark,buttons);
            platform.checkGameWin();
        }
        if (platform.isGameOver()){
            Frame window = new Frame("Result");
            window.setSize(240,180);
            window.setLayout(new GridLayout(2,1));
            window.setBackground(Color.lightGray);
            if(platform.is_win()){
                window.add(new TextField("You Win"));
            } else if (!platform.is_win()) {
                window.add(new TextField("You Lost"));
            }
            window.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    window.setVisible(false);
                }
            });
            window.add(new TextField("These windows will close in 20 sec."));
            window.setVisible(true);
            TimerTask quit = new TimerTask() {
                @Override
                public void run() {
                    window.setVisible(false);
                    exit(0);
                }
            };
            Timer timer = new Timer();
            timer.schedule(quit,10*1000L);
        }
    }



    public void play() {
        printLines();
        setLevel();
        final int[] times = {1};

        window = new Frame("Minesweeper");
        int plWidth = PlatformSize * 40;
        int plHeight = PlatformSize * 40;
        window.setSize(plWidth,plHeight);
        window.setLayout(new GridLayout(PlatformSize,PlatformSize,5,5));
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit(0);
            }
        });


        Button[] buttons = new Button[PlatformSize * PlatformSize];
        for (int i = 0; i < PlatformSize * PlatformSize; i++){
            buttons[i] = new Button();
            buttons[i].setBackground(Color.gray);
        }
        MouseListener[] mouseListeners = new MouseListener[PlatformSize*PlatformSize];

        Platform platform = new Platform(PlatformSize, BoomNumber,buttons);

        for (int i = 0; i < PlatformSize * PlatformSize; i++){
            int finalI = i;
            mouseListeners[i] = new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == e.BUTTON1){
                        click(finalI % PlatformSize + 1, finalI / PlatformSize + 1,times[0], platform, false,buttons);
                        times[0] ++;
                    }else if (e.getButton() == e.BUTTON3){
                        click(finalI % PlatformSize + 1, finalI / PlatformSize + 1,times[0], platform, true,buttons);
                        times[0] ++;
                    }

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            };

            buttons[i].addMouseListener(mouseListeners[i]);
            window.add(buttons[i]);
        }

        window.setVisible(true);


    }
}