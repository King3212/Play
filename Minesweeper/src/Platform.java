public class Platform {
    private final int PlatformSize = 16;
    private final int BoomNumber = 40;
    private Block[][] Blocks;

    private int numOpen = 1;


    private boolean gameOver = false;

    private void setGameOver() {
        this.gameOver = true;
        System.out.println("GameOver");
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Platform (){
        Blocks = new Block[PlatformSize+2][PlatformSize+2];
        for (int i = 0; i < PlatformSize+2; i++){
            for (int j = 0; j < PlatformSize+2; j++){
                Blocks[i][j] = new Block();
            }
        }
        randomSetBoom();
        setNumber();
        print();
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

    private void print(){
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
                Blocks[x][y].print();
                System.out.print(" | ");
            }
            System.out.println();

        }
    }
    private void Open(){
        boolean repeat = false;
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
                            numOpen++;
                        }
                    }
                }
            }
        }while(repeat);
    }


    public void firstClick(int x, int y){
        while (Blocks[x+1][y+1].isBoom()){
            setNumber();
            randomSetBoom();
        }
        Blocks[x][y].setOpen();
        for (int i = 0; i < PlatformSize + 2; i++){
            Blocks[PlatformSize + 1][i].setNumber(9);
            Blocks[i][PlatformSize + 1].setNumber(9);
            Blocks[0][i].setNumber(9);
            Blocks[i][0].setNumber(9);
        }
        Open();
        print();
    }

    public void click(int x, int y, boolean mark){
        if (mark){
            Blocks[x][y].setMark();
            print();
        }
        else {
            Blocks[x][y].setOpen();
            if (Blocks[x][y].isBoom()){
                print();
                setGameOver();
            }
            else {
                numOpen ++;
                Open();
                print();
            }
        }

    }

    public void checkGameWin() {
        int numMarkBut = 0;
        for (int x = 1; x < PlatformSize + 1; x++) {
            for (int y = 1; y < PlatformSize + 1; y++) {
                if (Blocks[x][y].isMark() && !Blocks[x][y].isBoom()){
                    numMarkBut++;
                }
            }
        }
        if (numMarkBut != 0 ){
            if (BoomNumber + numOpen == PlatformSize * PlatformSize){
                System.out.println("You Win!");
                print();
                gameOver = true;
            }
        }
    }
}
