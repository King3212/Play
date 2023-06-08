import java.util.Scanner;

public class Game {
    int level;
    int PlatformSize;
    int BoomNumber;

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

    public void play() {
        printLines();
        setLevel();
        Platform platform = new Platform(PlatformSize, BoomNumber);
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入横纵坐标（请以空格隔开，例如：“ 5 5 ”）：");

        /*input
        * need to be changed
        *
        * need a function:
        * click button -->  get the location -->  find out the x and y --> active it
        */
        int x = sc.nextInt();
        int y = sc.nextInt();
        printLines();

        /*this make the first graphs*/
        while (! platform.firstClick(x, y)){
            platform = new Platform(PlatformSize, BoomNumber);
        }

        /*this make action to the data*/
        platform.checkGameWin();
        while (!platform.isGameOver()) {
            System.out.print("请输入横纵坐标：");
            x = sc.nextInt();
            y = sc.nextInt();
            System.out.print("若标记&取消标记该点请输入1，点开雷区输入0：");
            boolean mark = sc.nextInt() != 0;
            printLines();
            platform.click(x, y, mark);
            platform.checkGameWin();
        }
    }
}