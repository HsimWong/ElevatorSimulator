import java.util.*;

class Button {
    private boolean pressDownStatus;
    private int     floorValue;

    public Button(int floorValue) {
		this.floorValue = floorValue;
        this.pressDownStatus = false;
    }

    protected boolean getStatus() {
        return this.pressDownStatus;
    }

    protected void pressButton() {
        this.pressDownStatus = true;
    }

    protected void respondTheButton() {
        this.pressDownStatus = false;
    }

    public String toString() {
        String ret = "The button on floor " + this.floorValue; 
        String status = pressDownStatus ? " is pressed down" : " is not requested";
        return (ret + status + "\n");
    }

    public static void main(String[] args) {
        Button a = new Button(2);
        System.out.print(a);
        a.pressButton();
        System.out.print(a);  
        a.respondTheButton();
        System.out.print(a);
    }

}