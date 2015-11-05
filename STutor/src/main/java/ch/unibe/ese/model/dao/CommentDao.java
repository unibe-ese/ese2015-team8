package ch.unibe.ese.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.model.Comment;

/**
 * 
 * @author Till Schnabel
 * @version 1.0
 * @since 4.11.2015
 */
public interface CommentDao extends CrudRepository<Comment,Long> {

}