package Cabin;

import Functions.Function;

public class CabinPanel {
	private int toppest;
	private int lowest;
	private boolean[] storyChecked;
		
	/* The default values of storyCheck are false */
	public CabinPanel (int length, int lowest) {
		Function f = new Function();
		this.toppest = f.realFloor(length, lowest);
		this.lowest = lowest;
		final int checkLength = toppest - lowest;
		this.storyChecked = new boolean[checkLength];
		
	}
	
	
	/* Request story from the panel,
	 * return the reverse value of the original status. */
	protected boolean requestStory (int storyNum) {
		if (storyChecked[storyNum] == false) {
			this.storyChecked[storyNum] = true;
			return true;
		} else {
			return false;
		}
	}
	
	protected void arriveStory (int storyNum) {
		this.storyChecked[storyNum] = false;
	}
	
	/* Print the current status of the cabin panel
	 * The ground floor is indicated with 0. */
	public String toString () {
		String retString = "";
		for (int i = 0; i < storyChecked.length; i++) {
			retString += "Floor" + (i + this.lowest + ((i + this.lowest) >=0 ? 1 : 0))
					+ ": " + this.storyChecked[i] + (((i + this.lowest) ==  -1|| i % 5 == -1) ? '\n' : '\t');
		}
		return retString;
	}
	
	
	
	public static void main(String[] args) {
		CabinPanel aaa = new CabinPanel(4, -3);
		aaa.requestStory(3);
		System.out.println(aaa);
	}
}
