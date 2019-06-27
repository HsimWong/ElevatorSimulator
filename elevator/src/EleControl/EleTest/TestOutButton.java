package EleControl.EleTest;

import static org.junit.Assert.*;

import org.junit.Test;

import EleControl.OutButton;

public class TestOutButton {

	
	@Test
	public void testGetIfUp() {
		OutButton obt = new OutButton(3, true);
		assertEquals(obt.getIfUp(), true);
	}

	@Test
	public void testGetPos() {
		OutButton obt = new OutButton(3, true);
		assertEquals(obt.getPos(), 3);
	}

}
