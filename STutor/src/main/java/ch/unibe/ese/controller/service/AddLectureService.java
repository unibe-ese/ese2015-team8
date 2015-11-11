package ch.unibe.ese.controller.service;

import ch.unibe.ese.controller.exceptions.*;
import ch.unibe.ese.controller.pojos.*;
import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Student;

/**
 * 
 * @author ESE Team 8
 * @version 1.0
 * @since 28.10.2015
 */
public interface AddLectureService {

    public Lecture saveFrom(LectureForm lectureForm, Student loggedInTutor) throws InvalidDataException, InvalidGradeException;

}
