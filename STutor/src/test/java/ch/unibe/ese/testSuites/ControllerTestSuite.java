package ch.unibe.ese.testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ch.unibe.ese.controller.tests.*;

@RunWith(Suite.class)
@SuiteClasses({
	LectureControllerTest.class,
	AfterLoginControllerTest.class,
	NotificationControllerTest.class,
	OptionControllerTest.class,
	ProfileControllerTest.class,
	RatingControllerTest.class,
	SearchControllerTest.class,
	SignUpControllerTest.class,
})
public class ControllerTestSuite {

}
