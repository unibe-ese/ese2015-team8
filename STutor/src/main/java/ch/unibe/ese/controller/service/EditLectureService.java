package ch.unibe.ese.controller.service;

import ch.unibe.ese.controller.exceptions.InvalidGradeException;
import ch.unibe.ese.controller.pojos.LectureForm;
import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Student;

public interface EditLectureService {
	public Lecture getChosenLecture(int index, Student tutor);
	public Lecture editFrom(LectureForm lectureForm, long lectureId) throws InvalidGradeException;
}
