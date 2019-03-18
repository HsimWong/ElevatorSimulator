package Cabin;

public class Carriage{
	private CabinPanel cabinPanel;
	private int capNumPerson;
	private int curNumPerson;
	private int currentFloor;
	private int maxFloor;
	private int minFloor;
	public Carriage(int capacity, int maxFloor, int minFloor) {
		if (maxFloor <= 0 || minFloor >=0) {
			throw exception
		
		this.cabinPanel = new CabinPanel(floorNum);
		this.capNumPerson = capacity;
		this.curNumPerson = 0;
		this.currentFloor = 1;
		this.maxFloor = maxFloor;
		this.minFloor = minFloor;


	}
	
	/* direction is indicated with boolean value.
	 * Up = true, Down = false */
	private moveUp1Floor(boolean direction) {
		if (direction) {
			if currentFloor
			this.currentFloor++;
		} else {
			this.currentFloor--;
		}
	}	

	public go2Floor(int floorNum){
		
	}

//	public String toString() {
//		
//	}
}
