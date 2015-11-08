package ch.unibe.ese.controller.service;

import ch.unibe.ese.controller.exceptions.*;
import ch.unibe.ese.controller.pojos.*;
import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Student;



public interface AddLectureService {

    public Lecture saveFrom(LectureForm lectureForm, Student loggedInTutor) throws InvalidDataException, InvalidGradeException;

}
