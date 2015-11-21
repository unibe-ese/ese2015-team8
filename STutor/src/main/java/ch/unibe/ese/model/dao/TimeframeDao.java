package ch.unibe.ese.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.model.Timeframe;

/**
 * 
 * @author ESE Team 8
 * @version 1.0
 * @since 28.10.2015
 */
public interface TimeframeDao extends CrudRepository<Timeframe,Long> {

}