import java.lang.Thread;

/*
 * The index of floor starts from 0
 * under any circumstance, exept for
 * displaying.
 * UP == 1;
 * DOWN == 0;
 */

class Cabin {
    private int maxFloor;
    private int timeLag;
    private int cabinIndex;
    private CabinButton[] buttons;
    private int dir;
    private int curFloor; 

    public Cabin(int maxFloor, cabinIndex) {
        this.timeLag = 5;
        this.maxFloor = maxFloor;
        this.cabinIndex = cabinIndex;
        this.dir = 1;
        this.curFloor = 0;
        this.buttons = new CabinButton[maxFloor];
        for (int i = 0; i < maxFloor; i++ ) {
            buttons[i] = new CabinButton(i, cabinIndex);
        }
    }

    private void wait(int timeLag) {

    }

    public int getDir() {
        return this.dir;
    }

    public int getCurPos() {
        return this.curFloor;
    }

    public boolean assignCommand(int destFloorIndex) {
        if (!(destFloorIndex == this.curFloor)) {
            this.dir = ((destFloorIndex - this.curFloor) > 0) ? 1 : 0;
            try {
                this.wait(this.timeLag * (destFloorIndex - this.curFloor));
                this.curFloor = destFloorIndex;
                this.respondFloor(destFloorIndex);
                return true;   
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
                return false;
            }
        } else {
            return true;
        }
    }



    public void respondFloor(int floorIndex) {
        this.buttons[floorIndex].respondTheButton();
    }

    public void requestFloor(int floorIndex) {
        this.buttons[floorIndex].pressButton();
    }

    public String toString() {
        String ret = "";
        for(int i = 0; i < this.maxFloor; i++) {
            ret += buttons[i].toString();
        }
        return ret;
    }

    public static void main(String[] args){
        Cabin a = new Cabin(6);
        a.assignCommand(4);
        System.out.println(a.getCurPos());
        a.assignCommand(1);
        System.out.println(a.getCurPos());
        System.out.println(a.getDir());
    }
}