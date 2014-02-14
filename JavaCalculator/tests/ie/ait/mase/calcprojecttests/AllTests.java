package ie.ait.mase.calcprojecttests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CalculatorParserTest.class, CalculatorTest.class,
		ReversePolishParserTest.class, CalculatorUITest.class })

public class AllTests { 

}
