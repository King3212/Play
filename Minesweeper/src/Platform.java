import java.awt.*;

public class Platform {
    private final int PlatformSize;
    private final int BoomNumber;
    private final Block[][] Blocks;

    private boolean is_win = false;


    private boolean gameOver = false;

    private void setGameOver() {
        this.gameOver = true;
        System.out.println("GameOver");
    }

    public boolean is_win() {
        return is_win;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Platform (int PlatformSize,int BoomNumber,Button[] buttons){
        this.BoomNumber = BoomNumber;
        this.PlatformSize = PlatformSize;
        Blocks = new Block[PlatformSize+2][PlatformSize+2];
        for (int i = 0; i < PlatformSize+2; i++){
            for (int j = 0; j < PlatformSize+2; j++){
                Blocks[i][j] = new Block();
            }
        }
        randomSetBoom();
        setNumber();
        print(buttons);
    }


    /**实际上，这里创建了比需求大一圈的blocks
     * |* * * *|
     * |* + + *|
     * |* + + *|
     * |* * * *|
     * “+”是有意义的
     * “*”是无意义的
     * 方便后面计算数字而已
     * */
    private void randomSetBoom(){
        for (int i = 0; i < BoomNumber; i++){
            int X;
            int Y;
            do {
                X = (int)(Math.random() * PlatformSize + 1);
                Y = (int)(Math.random() * PlatformSize + 1);
            }while (Blocks[X][Y].isBoom());
            Blocks[X][Y].setBoom();
        }
    }

    private void setNumber() {
        for (int x = 1; x < PlatformSize + 1; x++) {
            for (int y = 1; y < PlatformSize + 1; y++) {
                int sum = 0;
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (Blocks[x + i][y + j].isBoom()) {
                            sum += 1;
                        }
                    }
                }
                Blocks[x][y].setNumber(sum);
            }
        }
    }

    private void print(Button[] buttons){
        System.out.print("   ");
        for (int x = 1; x < PlatformSize + 1; x++) {
            System.out.printf(" %2d ",x);
            if (x == 9){
                System.out.print(" ");
            }
        }
        System.out.println();
        for (int y = 1; y < PlatformSize + 1; y++) {
            System.out.printf("%2d | ",y);
            for (int x = 1; x < PlatformSize + 1; x++) {
                Blocks[x][y].print(buttons[(x-1) + (y-1) * PlatformSize]);
                System.out.print(" | ");
            }
            System.out.println();

        }
    }
    private void Open(){
        boolean repeat;
        do {
            repeat = false;
            for (int x = 1; x < PlatformSize + 1; x++) {
                for (int y = 1; y < PlatformSize + 1; y++) {
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (Blocks[x + i][y + j].isOpen() && Blocks[x + i][y + j].getNumber() == 0 && !Blocks[x][y].isOpen()) {
                                Blocks[x][y].setOpen();
                                repeat = true;
                            }
                        }
                    }
                }
            }
        }while(repeat);
    }


    public boolean firstClick(int x, int y,Button[] buttons){
        if (Blocks[x][y].isBoom()){
            return false;
        }
        Blocks[x][y].setOpen();
        for (int i = 0; i < PlatformSize + 2; i++){
            Blocks[PlatformSize + 1][i].setNumber(9);
            Blocks[i][PlatformSize + 1].setNumber(9);
            Blocks[0][i].setNumber(9);
            Blocks[i][0].setNumber(9);
        }
        Open();
        print(buttons);
        return true;
    }


    /*the note can be show int one title or just make it can be clicked*/
    public void click(int x, int y, boolean mark,Button[] buttons){
        if (mark){
            if (Blocks[x][y].isOpen()) {
                Blocks[x][y].setMark();
                System.out.println("已完成搜索，禁止标记！");
            }
            Blocks[x][y].setMark();
            print(buttons);
        }
        else {
            if (Blocks[x][y].isMark()){
                System.out.println("该点已标记，请取消标记后再尝试搜索");
                print(buttons);
            }
            else {
                Blocks[x][y].setOpen();
                if (Blocks[x][y].isBoom()) {
                    print(buttons);
                    setGameOver();
                } else {
                    Open();

                    print(buttons);
                }
            }
            if (!gameOver) checkGameWin();
        }

    }

    public void checkGameWin() {
        int numOpen = 0;
        for (int x = 1; x < PlatformSize + 1; x++) {
            for (int y = 1; y < PlatformSize + 1; y++) {
                if (Blocks[x][y].isOpen()){
                    numOpen++;
                }
            }
        }
        if (numOpen == (PlatformSize * PlatformSize - BoomNumber )&& !isGameOver()){
            gameOver = true;
            is_win = true;
            System.out.println("You Win!"); // must be changed
        }
    }
}
