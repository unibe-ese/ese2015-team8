package ch.unibe.ese.testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ch.unibe.ese.controller.service.tests.*;

//Part 2 of ServiceTestSuite.class
@RunWith(Suite.class)
@SuiteClasses({
	OptionServiceTest.class
})
public class ServiceTestSuite2 {

}
