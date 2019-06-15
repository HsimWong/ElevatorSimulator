import java.util.ArrayList;
class Panel {
    private int maxFloor;
    private Button[] buttons;
    public Panel(int maxFloor) {
        this.maxFloor = maxFloor;
        this.buttons = new Button[maxFloor];
        for (int i = 0; i < maxFloor; i++ ) {
            buttons[i] = new Button(i + 1);
        }
    }

    public String toString() {
        String ret = "";
        for(int i = 0; i < this.maxFloor; i++) {
            ret += buttons[i].toString();
        }
        return ret;
    }

    // public void respondFloor()

    public static void main(String[] args){
        Panel a = new Panel(6);
        System.out.println(a);
    }
}