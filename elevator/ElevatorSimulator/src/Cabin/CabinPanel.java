package Cabin;

public class CabinPanel {
	private int totalNumStories;
	private boolean[] storyChecked;
		
	/* The default values of storyCheck are false */
	public CabinPanel (int storyNum) {
		this.totalNumStories = storyNum;
		final int checkLength = totalNumStories;
		this.storyChecked = new boolean[checkLength];
		
	}
	
	
	/* Request story from the panel,
	 * return the reverse value of the original status. */
	protected boolean requestStory (int storyNum) {
		if (storyChecked[storyNum - 1] == false) {
			this.storyChecked[storyNum - 1] = true;
			return true;
		} else {
			return false;
		}
	}
	
	protected void arriveStory (int storyNum) {
		this.storyChecked[storyNum - 1] = false;
	}
	
	/* Print the current status of the cabin panel
	 * The story number begin with 0. */
	public String toString () {
		String retString = "";
		for (int i = 0; i < storyChecked.length; i++) {
			retString += "Floor" + (i+1) + ": " + this.storyChecked[i] + '\t';
		}
		return retString;
	}
	
	public static void main(String[] args) {
		CabinPanel aaa = new CabinPanel(4);
		aaa.requestStory(3);
		System.out.println(aaa);
	}
}
