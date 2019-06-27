package EleControl;

//import OutPanel.OutPanel;
//import java.util.*;

class Elevator {
	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int STOP = 0;
	public static final int INREQ = 0;
	public static final int OUTREQ = 1;
	public static final int INFTY = 0xfffff;
	

	private Cabin[] cabins;
	private OutPanel[] outPanels;
//	private PanelStatus[][]


	public Elevator(int maxFloor, int cabinNum) {
		this.cabins = new Cabin[cabinNum];
		this.outPanels = new OutPanel[maxFloor];
		
		/* create cabins */
		for (int i = 0; i < cabinNum; i++) {
			this.cabins[i] = new Cabin(maxFloor, i, outPanels);
		}
		/* create outPanels for each floor */
		for (int i = 0; i < maxFloor; i++) {
			this.outPanels[i] = new OutPanel(i, i == (maxFloor - 1));
		}
	}
	
	public void makeRequestFromOut(int floorInd, int direction) {
		this.outPanels[floorInd].makeRequest(direction == UP);
		int dispatchedCabinInd = this.dispatcher(floorInd, direction);
		this.cabins[dispatchedCabinInd].requestFromOutside(floorInd, direction);
	}
	
	private int getCalPos(Cabin cabin) {
		return cabin.ifOccupied() ? cabin.getNextFloor() : cabin.getCurPos();
	}
		
	public int dispatcher(int floorInd, int direction) {
		int retCabinInd = 0;
//		int[] status = new int[cabins.length];
		if (direction == UP) {
			Cabin bestUnderUp = null;
			Cabin bestUnderDown = null;
			
			int bestUpInd = INFTY;
			int bestDownInd = INFTY;
			for (int i = 0; i < cabins.length; i++) {
				if (getCalPos(cabins[i]) <= floorInd && (cabins[i].getDir() == UP)) {
					if (bestUnderUp == null 
							|| ((floorInd - this.getCalPos(cabins[i])) < (floorInd - this.getCalPos(bestUnderUp)))) {
						bestUnderUp = cabins[i];
						bestUpInd = i;
					}
				} else if (getCalPos(cabins[i]) <= floorInd && (cabins[i].getDir() == DOWN
						&& (bestUnderDown == null
						|| ((floorInd - this.getCalPos(cabins[i])) > (floorInd - this.getCalPos(bestUnderDown)))))) {
					bestUnderDown = cabins[i];
					bestDownInd = i;
				} 
			}
			
			if (bestUnderUp != null) {
				return bestUpInd;
			} else if (bestUnderDown != null) {
				return bestDownInd;	
			} else {
				return (int)(Math.random() * this.cabins.length);
			}
		}
		
		return retCabinInd;
	}
}

	
