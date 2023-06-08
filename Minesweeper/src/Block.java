public class Block {
    private boolean boom;
    private int number;
    private boolean mark;
    private boolean open;

    public Block(){
        boolean boom = false;
        int number = 0;
        boolean mark = false;
        boolean open = false;
    }

    // it was needed to be changed, because in gui, print is not needed anymore
    // maybe change color is good? deep gray is normal; light gray is nothing green is mark; red is boom!
    public void print(){
        if (mark){
            System.out.print("▲");
        }else if (open){
            if (boom) {
                System.out.print("*");}

            else if (number == 0) {
                System.out.print(" ");
            } else {
                System.out.print(number);
            }
        }else {
            System.out.print("■");
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
