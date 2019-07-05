package EleControl;


/*
 * The index of floor starts from 0
 * under any circumstance, except for
 * displaying.
 */
public class Button {
    private boolean pressDownStatus;
    

    public Button() {
        // this.cabinIndex = cabinIndex;
		this.pressDownStatus = false;
    }

    public boolean getStatus() {
        return this.pressDownStatus;
    }
    
    
    
    

    // @Override
    public void pressButton() {
        this.pressDownStatus = true;
    }

    
    public void respondTheButton() {
        this.pressDownStatus = false;
    }

    @Override
    public String toString(){
        return "hahahhaha";
    }
    
    public static void main(String[] args) {
//    	Button btn = new Button();
    }
}