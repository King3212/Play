import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Platform platform = new Platform();
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入横纵坐标：");
        int x = sc.nextInt();
        int y = sc.nextInt();
        for(int i=0;i<=50;i++){ System.out.println();}
        platform.firstClick(x,y);
        while(platform.isGameOver() == false){
            System.out.print("请输入横纵坐标：");
            x = sc.nextInt();
            y = sc.nextInt();
            System.out.print("若标记&取消标记该点请输入1，否则输入0：");
            boolean mark = sc.nextInt() != 0;
            for(int i=0;i<=50;i++){ System.out.println();}
            platform.click(x,y,mark);
            platform.checkGameWin();
        }
    }


}
