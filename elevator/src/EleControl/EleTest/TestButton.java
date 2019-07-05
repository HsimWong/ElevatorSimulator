package EleControl.EleTest;

import static org.junit.Assert.*;

import org.junit.Test;

import EleControl.Button;

public class TestButton {

	@Test
	public void testGetStatus() {
//		fail("Not yet implemented");
		Button a = new Button();
		boolean status = a.getStatus();
		assertEquals(false, status);
	}

	@Test
	public void testPressButton() {
		Button a = new Button();
		a.pressButton();
		boolean status = a.getStatus();
		assertEquals(true, status);
	}

	@Test
	public void testRespondTheButton() {
		Button a = new Button();
		a.pressButton();
		a.respondTheButton();
		boolean status = a.getStatus();
		assertEquals(false, status);
	}

}
