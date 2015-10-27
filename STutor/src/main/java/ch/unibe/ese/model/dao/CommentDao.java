package ch.unibe.ese.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.model.Comment;

public interface CommentDao extends CrudRepository<Comment,Long> {

}