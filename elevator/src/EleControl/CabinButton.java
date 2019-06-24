package EleControl;



class CabinButton extends Button {
    private int floorIndex;
//    private int cabinIndex;
    
    public CabinButton(int floorIndex) {
        super();
        this.floorIndex = floorIndex;
//        this.cabinIndex = cabinIndex;
    }

    @Override
    public String toString() {
        String ret = "The button on floor " + (this.floorIndex + 1); 
        String status = this.getStatus() ? " is pressed down" : " is not requested";
        return (ret + status + "\n");
    }

    public static void main(String[] args) {
        CabinButton a = new CabinButton(3);
        System.out.print(a);
        a.pressButton();
        System.out.print(a);

    }

}