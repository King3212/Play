import java.util.Scanner;

public class Game {
    int level;
    int PlatformSize;
    int BoomNumber;

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

    private void printLines() {
        for (int i = 0; i <= 50; i++) {
            System.out.println();
        }
    }

    public void play() {
        printLines();
        setLevel();
        Platform platform = new Platform(PlatformSize, BoomNumber);
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入横纵坐标（请以空格隔开，例如：“ 5 5 ”）：");
        int x = sc.nextInt();
        int y = sc.nextInt();
        printLines();
        while (! platform.firstClick(x, y)){
            platform = new Platform(PlatformSize, BoomNumber);
        }
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