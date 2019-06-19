class OutPanel {
    private int floorIndex;
    private int cabinIndex;
    private OutButton[] requestButtons;
    
    public OutPanel(int floorIndex, int cabinIndex, boolean ifTopFloor) {
        this.floorIndex = floorIndex;
        this.ifTopFloor = ifTopFloor;
        this.cabinIndex = cabinIndex;
        this.requestButtons = new OutButton[2];
        if (this.floorIndex == 0) {
            this.requestButtons[0] = new OutButton(0, this.cabinIndex, true);
            this.requestButtons[1] = null;
        } else if (this.ifTopFloor) {
            this.requestButtons[0] = null;
            this.requestButtons[1] = new OutButton(floorIndex, cabinIndex, false);
        } else {
            this.requestButtons[0] = new OutButton(floorIndex, cabinIndex, true);
            this.requestButtons[1] = new OutButton(floorIndex, cabinIndex, false);
        }
    }

    public boolean makeRequest(boolean dir) {
        if (dir) {
            if (!(this.requestButtons[0] == null)) {    
                this.requestButtons[0].pressButton();
                return true;
            } else {
                System.out.println("Bad request: Request up at top floor")
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
                System.out.println("Bad respond: Respond up at top floor")
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

    

}