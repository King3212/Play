import java.awt.*;

public class Block {
    private boolean boom;
    private int number;
    private boolean mark;
    private boolean open;

    // it was needed to be changed, because in gui, print is not needed anymore
    // maybe change color is good? deep gray is normal; light gray is nothing green is mark; red is boom!
    public void print(Button x){
        if (mark){
            x.setLabel("P");
        }else if (open){
            if (boom) {
                x.setBackground(Color.red);}
            else if (number == 0) {
                x.setLabel("");
                x.setBackground(Color.white);
                x.setEnabled(false);
            } else {
                x.setLabel(String.valueOf(number));
                x.setBackground(Color.white);
                x.setEnabled(false);
            }
        }else {
            x.setLabel("");
        }

    }

    public boolean isBoom() {
        return boom;
    }
    public void setOpen(){
        open = true;
    }
    public void setMark(){
        mark = !mark;
    }
    public void setBoom(){
        boom = true;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public boolean isOpen() {
        return open;
    }
    public int getNumber() {
        return number;
    }
    public boolean isMark() {
        return mark;
    }

}
