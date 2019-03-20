package Cabin;

public class Carriage{
	private CabinPanel cabinPanel;
	private int capNumPerson;
	private int curNumPerson;
	private boolean curDirct;
	private int currentPosition;		// Current Floor
	private int floorNum;
//	private int baseFloor;
	
	public Carriage(int capacity, int baseFloor, int floorNum) {
		if (floorNum <= -baseFloor || baseFloor >=0) {
			throw new IllegalArgumentException("Floor number illegal");
		} else {
			this.cabinPanel = new CabinPanel(floorNum, baseFloor);
			this.capNumPerson = capacity;
			this.curNumPerson = 0;
			this.currentPosition = 0;

		}
	}
	
	/* ```direction``` indicates the direction of the 
	 * carriage with boolean value.
	 * Up = true, Down = false */
	public boolean moveUp1Floor(boolean direction) {
		if ((currentPosition == 0 && direction == false)
				|| (currentPosition == (this.floorNum) && direction)) {
			throw new IllegalArgumentException("Reached to the " +
						((currentPosition == 0) ? "BOTTOM" : "TOP") + ", cannot move any more.");
		} else {
			this.currentPosition += ((direction) ? 1 : -1);
			return true;
		}
	}	

	public int loadPerson(int personNum) {
		int personLoaded = 0;
		if ((this.capNumPerson - this.curNumPerson) >= personNum) {
			personLoaded = personNum;
			this.curNumPerson += personNum;
		} else {
			personLoaded = this.capNumPerson - this.curNumPerson;
			this.curNumPerson = this.capNumPerson;
		}		
		return personLoaded;
	}
	
	public void go2Floor(int floorNum){
		
	}

	
}
