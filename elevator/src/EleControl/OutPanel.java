package EleControl;
class OutPanel {
    private int floorIndex;
    // private int cabinIndex;
    private OutButton[] requestButtons;
    
    public OutPanel(int floorIndex, boolean ifTopFloor) {
        this.floorIndex = floorIndex;
        // this.ifTopFloor = ifTopFloor;
        // this.cabinIndex = cabinIndex;
        this.requestButtons = new OutButton[2];
        if (this.floorIndex == 0) {
            this.requestButtons[0] = new OutButton(0, true);
            this.requestButtons[1] = null;
        } else if (ifTopFloor) {
            this.requestButtons[0] = null;
            this.requestButtons[1] = new OutButton(floorIndex, false);
        } else {
            this.requestButtons[0] = new OutButton(floorIndex, true);
            this.requestButtons[1] = new OutButton(floorIndex, false);
        }
    }

    public boolean makeRequest(boolean dir) {
        if (dir) {
            if (!(this.requestButtons[0] == null)) {    
                this.requestButtons[0].pressButton();
                return true;
            } else {
                System.out.println("Bad request: Request up at top floor");
                return false;
            }
        } else {
            if (!(this.requestButtons[1] == null)){
                this.requestButtons[1].pressButton();
                return true;
            } else {
                System.out.println("Bad request: Request down at bottom floor");
                return false;
            }
        }
    }

    public boolean respondRequest(boolean dir) {
        if (dir) {
            if (!(this.requestButtons[0] == null)) {    
                this.requestButtons[0].respondTheButton();
                return true;
            } else {
                System.out.println("Bad respond: Respond up at top floor"); 
                return false;
            }
        } else {
            if (!(this.requestButtons[1] == null)){
                this.requestButtons[1].respondTheButton();
                return true;
            } else {
                System.out.println("Bad respond: Respond down at bottom floor");
                return false;
            }
        }
    }

    public String toString() {
        String ret = "";
        if(this.requestButtons[0] != null) {
            ret += this.requestButtons[0].toString();
        }
        if(this.requestButtons[1] != null) {
            ret += this.requestButtons[1].toString();
        }
        return ret;
    }

    public static void main(String[] args) {
        OutPanel a = new OutPanel(4, true);
        System.out.println(a);
        a.makeRequest(true);
        System.out.println(a);
        a.makeRequest(false);
        System.out.println(a);
        
        
    }

}