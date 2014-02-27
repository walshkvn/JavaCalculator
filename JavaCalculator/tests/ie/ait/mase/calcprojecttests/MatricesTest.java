package ie.ait.mase.calcprojecttests;

import static org.junit.Assert.*;
import ie.ait.mase.calcproject.Matrices;

import org.junit.Before;
import org.junit.Test;

public class MatricesTest {

	Matrices mt;
	
	@Before
	public void setUp() throws Exception {
		mt = new Matrices();
	}

	@Test
	public void testValidateForNewLine() {
		//assertTrue(mt.validateMatrixString("1,2,3,4\n"));
	}

}
