package ElevatorGroup;

import Cabin.Carriage;

public class Elevator {
	private Carriage[] carriageArray;
	private int storyNum;
	
	public Elevator(int storyNum, int carriageNum) {
		this.storyNum = storyNum;
		for (int i = 0; i < carriageNum; i++) {
//			carriageArray[i] = new Carriage(storyNum);
		}
	}
	
	public String toString() {
		String ret = "Carriage Status:";
		for (int i = 0; i < this.carriageArray.length; i++) {
			ret += this.carriageArray[i].toString();
		}
		return ret;
	}
	
	public static void main(String[] args) {
		Elevator ele = new Elevator(1, 5);
		System.out.println(ele);
	}
}
