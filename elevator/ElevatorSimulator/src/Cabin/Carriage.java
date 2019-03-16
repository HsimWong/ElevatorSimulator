package Cabin;

public class Carriage{
	private CabinPanel cabinPanel;
	private int personCapacity;
	private int currentCapacity;
	private int currentFloor;
	
	public Carriage(int capacity, int floorNum) {
		this.cabinPanel = new CabinPanel(floorNum);
	}
	
	public String toString() {
		
	}
}
