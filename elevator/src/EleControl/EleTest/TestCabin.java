package EleControl.EleTest;

import static org.junit.Assert.*;

import org.junit.Test;

import EleControl.Cabin;
import EleControl.Elevator;

public class TestCabin {
	private Elevator ele = new Elevator(10, 7);
	private Cabin cb = ele.cabins[2];
	
	
	@Test
	public void testFetchRqID() {
		int IDa = Cabin.fetchRqID();
		int IDb = Cabin.fetchRqID();
		assertEquals((IDa == IDb), false);
	}

//
//	@Test
//	public void testDoRequest() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFloorDir() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDoNext() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetDir() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetCurPos() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetCabinIndex() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRespondFloor() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRequestFloorFromInside() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRequestFromOutside() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testToString() {
//		fail("Not yet implemented");
//	}

}
