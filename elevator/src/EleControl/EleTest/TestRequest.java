package EleControl.EleTest;

import static org.junit.Assert.*;
import org.junit.Test;
import EleControl.Cabin;
import EleControl.Cabin.Request;
import EleControl.Elevator;


public class TestRequest {

	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int STOP = 0;
	public static final int INREQ = 0;
	public static final int OUTREQ = 1;
	public static final int GREATER = 1;
	public static final int EQUALSTO = 1;
	public static final int LESSTHAN = 1;
	public static final int STOPCMPERROR = 0;
	
	@Test
	public void testCompareTo() {
		Elevator ele = new Elevator(10, 7);
		Cabin cb = ele.cabins[2];
		cb.curFloor = 5;
		
		int[] thisFloorVal = {8,6,8,3,3,1,5,8,6,8,3,3,1};
		
		int[] compFloorVal = {6,8,3,8,1,3,5,6,8,3,8,1,3};
		int[] thisDirection = {UP,UP,UP,UP,UP,UP,UP,DOWN,DOWN,DOWN,DOWN,DOWN,DOWN};
//		int[] compDirection = {DOWN,DOWN,DOWN,DOWN,DOWN,DOWN,UP,UP,UP,UP,UP,UP,UP};
		int[] compResult = {-1,1,1,-1,-1,1,STOPCMPERROR,-1,1,-1,1,1,-1};
		for (int i = 0; i < thisFloorVal.length; i++) {
			if (i == 7) {
				int bo = 5;
				bo += 1;
			}
			cb.dir = thisDirection[i];
			Cabin.Request rqThis = cb.new Request(thisFloorVal[i], INREQ, thisDirection[i], i);
			Cabin.Request rqTest = cb.new Request(compFloorVal[i], INREQ, thisDirection[i], i);
			assertEquals(rqThis.compareTo(rqTest), compResult[i]);
		}
	}

}
