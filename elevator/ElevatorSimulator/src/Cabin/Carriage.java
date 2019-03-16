package Cabin;

public class Carriage{
	private CabinPanel cabinPanel;
	private int capNumPerson;
	private int curNumPerson;
	private int currentFloor;
	
	public Carriage(int capacity, int floorNum) {
		this.cabinPanel = new CabinPanel(floorNum);
		this.capNumPerson = capacity;
		this.curNumPerson = 0;
		this.currentFloor = 1;
	}
	
//	public String toString() {
//		
//	}
}
