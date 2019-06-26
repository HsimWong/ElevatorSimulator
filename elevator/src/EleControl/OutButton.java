package EleControl;
//import java.util.*;
/*
 * The index of floor starts from 0
 * under any circumstance, exept for
 * displaying.
 */
class OutButton extends Button {
    private int     pos;
    private boolean ifUp;

    public OutButton(int pos, boolean ifUp) {
        super();
        this.pos = pos; 
        this.ifUp = ifUp;
    }

    public boolean getIfUp() {
        return this.ifUp;
    }

    public int getPos() {
        return this.pos;
    }

    @Override
    public String toString() {
        String direction = this.getIfUp() ? "Up" : "Down";
        String status = this.getStatus() ? " is pressed down" : " is not requested";
        String ret = "The outside button of cabin "
                     + "indicating " 
                     + direction + " on the floor " 
                     + (this.getPos() + 1) + status +"\n";
        return ret;
    }

    public static void main(String[] args) {
        // OutButton a = new OutButton(3, 5, false);
        // System.out.print(a);
        // a.pressButton();
        // System.out.print(a);
        // a.respondTheButton();
        // System.out.println(a);
    }


}