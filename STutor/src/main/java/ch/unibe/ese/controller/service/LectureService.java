package ch.unibe.ese.controller.service;

import ch.unibe.ese.controller.exceptions.InvalidDataException;
import ch.unibe.ese.controller.exceptions.InvalidGradeException;
import ch.unibe.ese.controller.pojos.LectureForm;
import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Student;

/**
 * @author Till
 * Processes saving, editing and deleting the lectures.
 */
public interface LectureService {
	
    public Lecture saveFrom(LectureForm lectureForm, Student loggedInTutor) throws InvalidDataException, InvalidGradeException;

	public Lecture editFrom(LectureForm lectureForm, long lectureId) throws InvalidGradeException;
	
	public Lecture remove(Lecture lecture);
	
	public LectureForm getLectureFormFrom(Lecture chosenLecture);

}
