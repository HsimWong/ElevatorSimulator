package EleControl;


/*
 * The index of floor starts from 0
 * under any circumstance, exept for
 * displaying.
 */
class Button {
    private boolean pressDownStatus;
    // private int     cabinIndex;

    public Button() {
        // this.cabinIndex = cabinIndex;
		this.pressDownStatus = false;
    }

    protected boolean getStatus() {
        return this.pressDownStatus;
    }

    // @Override
    protected void pressButton() {
        this.pressDownStatus = true;
    }

    
    protected void respondTheButton() {
        this.pressDownStatus = false;
    }

    @Override
    public String toString(){
        return "hahahhaha";
    }
}