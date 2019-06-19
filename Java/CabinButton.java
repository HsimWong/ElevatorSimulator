

class CabinButton extends Button {
    private int floorIndex;
    public CabinButton(int floorIndex, int cabinIndex) {
        super(cabinIndex);
        this.floorIndex = floorIndex;
    }

    @Override
    public String toString() {
        String ret = "The button of cabin " + (this.getCabinIdx() + 1) + " on floor " + (this.floorIndex + 1); 
        String status = this.getStatus() ? " is pressed down" : " is not requested";
        return (ret + status + "\n");
    }

    public static void main(String[] args) {
        CabinButton a = new CabinButton(3, 5);
        System.out.print(a);
        a.pressButton();
        System.out.print(a);

    }

}