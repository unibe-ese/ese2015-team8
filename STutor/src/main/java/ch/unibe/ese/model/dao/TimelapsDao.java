package ch.unibe.ese.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.model.Timelaps;

/**
 * 
 * @author Till Schnabel
 * @version 1.0
 * @since 4.11.2015
 */
public interface TimelapsDao extends CrudRepository<Timelaps,Long> {

}
