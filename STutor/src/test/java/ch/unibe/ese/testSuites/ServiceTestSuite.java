package ch.unibe.ese.testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ch.unibe.ese.controller.service.tests.*;

@RunWith(Suite.class)
@SuiteClasses({
	AddLectureServiceTest.class,
	OptionServiceTest.class,
	SignupServiceTest.class,
	CommentServiceTest.class,
	TimelapsServiceTest.class
})
public class ServiceTestSuite {}
