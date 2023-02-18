import java.util.Scanner;
class ThreadPause {
    // method to pause the string
    // here we will pass the time in seconds
    public void wait(int sec) {
        try {
            Thread.currentThread().sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请选择难度\n\n 1.初级：9*9 10颗雷\n 2.中级：16*16 40颗雷\n 3.高级：25*25 90颗雷 \n" +
                "输入序号并回车完成选择：");
        Game game = new Game(sc.nextInt());
        game.play();
        ThreadPause TP = new ThreadPause();
        System.out.println("The windows will close in 60 seconds.");
        TP.wait(60);

    }
}