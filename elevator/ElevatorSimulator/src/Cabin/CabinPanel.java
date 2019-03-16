package Cabin;

public class CabinPanel {
	private int totalNumStories;
	private boolean[] storyChecked;
		
	/* The default values of storyCheck are false */
	public CabinPanel (int storyNum) {
		final int checkLength = storyNum;
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
	 * The story number begin with 0. */
	public String toString () {
		String retString = "";
		for (int i = 0; i < storyChecked.length; i++) {
			retString += "Floor" + i + ": " + this.storyChecked[i];
		}
		return retString;
	}
	
	public static void main(String[] args) {
		
	}
}
