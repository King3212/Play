import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;


public class Game {
    private final int level;
    private int PlatformSize;
    private int BoomNumber;
    public JFrame window;
    private long sTime;
    private Button retry;
    private RecordRW record;
    public JFrame resultWindow;
    Platform platform;
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


    private void click(int x, int y, int times, boolean mark, Button[] buttons) throws IOException {

        if (times == 1){
            sTime = System.nanoTime();
            while (! platform.firstClick(x, y,buttons)){
                platform = new Platform(PlatformSize, BoomNumber,buttons);
                sTime = System.nanoTime();
            }
        }
        else{
            platform.click(x, y, mark,buttons);
            platform.checkGameWin();
        }
        if (platform.isGameOver()){
            this.gameOver();
//
        }
    }
    private void gameOver() throws IOException {
        resultWindow = new JFrame("Result");
        resultWindow.setSize(240,150);
        resultWindow.setLayout(new GridLayout(3,1));
        resultWindow.setBackground(Color.lightGray);

        record = new RecordRW();
        int usingTime = (int) ((System.nanoTime()-sTime)/1e9);

        if(platform.is_win()){
            String finalText = "恭喜，你赢了!";

            if (record.newRecord(usingTime)){
                finalText = finalText + "   新纪录";
            }

            resultWindow.add(new TextField(finalText));
        } else{
            resultWindow.add(new TextField("很遗憾，输掉了!"));
        }

        resultWindow.add(new TextField("花费时间："+ usingTime + "秒"));
        resultWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                resultWindow.setVisible(false);
                System.exit(0);
            }
        });
        resultWindow.add(retry);
        resultWindow.setVisible(true);
    }


    public void play(Button retry) {
        this.retry = retry;
        setLevel();
        final int[] times = {1};

        window = new JFrame("Minesweeper");
        int plWidth = PlatformSize * 40;
        int plHeight = PlatformSize * 40;
        window.setSize(plWidth,plHeight);
        window.setLayout(new GridLayout(PlatformSize,PlatformSize,5,5));
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        Button[] buttons = new Button[PlatformSize * PlatformSize];
        for (int i = 0; i < PlatformSize * PlatformSize; i++){
            buttons[i] = new Button();
            buttons[i].setBackground(Color.gray);
        }
        MouseListener[] mouseListeners = new MouseListener[PlatformSize*PlatformSize];

        platform = new Platform(PlatformSize, BoomNumber,buttons);

        for (int i = 0; i < PlatformSize * PlatformSize; i++){
            int finalI = i;
            mouseListeners[i] = new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1){
                        try {
                            click(finalI % PlatformSize + 1, finalI / PlatformSize + 1,times[0], false,buttons);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        times[0] ++;
                    }else if (e.getButton() == MouseEvent.BUTTON3){
                        try {
                            click(finalI % PlatformSize + 1, finalI / PlatformSize + 1,times[0], true,buttons);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
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