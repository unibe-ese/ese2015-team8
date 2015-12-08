package ch.unibe.ese.testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ch.unibe.ese.controller.service.tests.CommentServiceTest;
import ch.unibe.ese.controller.service.tests.LectureServiceTest;
import ch.unibe.ese.controller.service.tests.OptionServiceTest;
import ch.unibe.ese.controller.service.tests.SignupServiceTest;
import ch.unibe.ese.controller.service.tests.TimeframeServiceTest;

//had to separate OptionServiceTest and SignupServiceTest because the mockDao's were overlapping
@RunWith(Suite.class)
@SuiteClasses({
	LectureServiceTest.class,
	SignupServiceTest.class,
	CommentServiceTest.class,
	TimeframeServiceTest.class,
	OptionServiceTest.class
})
public class ServiceTestSuite {

}
