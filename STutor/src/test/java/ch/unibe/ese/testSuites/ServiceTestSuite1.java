package ch.unibe.ese.testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ch.unibe.ese.controller.service.tests.*;

// Part 1 of ServiceTestSuite.class
@RunWith(Suite.class)
@SuiteClasses({
	LectureServiceTest.class,
	SignupServiceTest.class,
	CommentServiceTest.class,
	TimeframeServiceTest.class
})
public class ServiceTestSuite1 {}
