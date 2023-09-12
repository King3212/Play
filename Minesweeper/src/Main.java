import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import static java.lang.System.exit;

public class Main {
    private static Game game;
    private static Button retry;

    private static void chooseAndPlay(){
        Frame window = new Frame("Minesweeper");
        int plWidth = 240;
        int plHeight = 320;
        window.setSize(plWidth,plHeight);
        window.setBackground(Color.white);
        window.setLayout(new BorderLayout());

        Panel middle = new Panel(new GridLayout(4,1,0,20));

        window.add(middle,BorderLayout.CENTER);
        Button easy = new Button("Easy");
        Button normal = new Button("Normal");
        Button hard = new Button("hard");
        Button exit = new Button("exit");
        easy.setBackground(Color.lightGray);
        normal.setBackground(Color.lightGray);
        hard.setBackground(Color.lightGray);
        exit.setBackground(Color.GRAY);

        middle.add(easy);
        middle.add(normal);
        middle.add(hard);
        middle.add(exit);

        window.setVisible(true);
        ActionListener exitListener = actionEvent -> exit(0);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        exit.addActionListener(exitListener);


        ActionListener hardListener = actionEvent -> {
            window.setVisible(false);
            game = new Game(3);
            game.play(retry);
        };
        hard.addActionListener(hardListener);

        ActionListener easyListener = actionEvent -> {
            window.setVisible(false);
            game = new Game(1);
            game.play(retry);
        };
        easy.addActionListener(easyListener);

        ActionListener normalListener = actionEvent -> {
            window.setVisible(false);
            game = new Game(2);
            game.play(retry);
        };
        normal.addActionListener(normalListener);

    }
    public static void main(String[] args) {
        //init Platforms
        ActionListener retryListener = actionEvent -> {
            chooseAndPlay();
            game.resultWindow.dispose();
            game.window.dispose();
        };
        retry = new Button("Retry");
        retry.addActionListener(retryListener);
        chooseAndPlay();



        System.out.println("finish!");
    }

}
