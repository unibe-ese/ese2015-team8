package ch.unibe.ese.testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//had to separate OptionServiceTest and SignupServiceTest because the mockDao's were overlapping
@RunWith(Suite.class)
@SuiteClasses({
	ServiceTestSuite1.class,
	ServiceTestSuite2.class
})
public class ServiceTestSuit {

}
