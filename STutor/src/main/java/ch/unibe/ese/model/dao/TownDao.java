package ch.unibe.ese.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.model.Town;

public interface TownDao extends CrudRepository<Town, Long>{

}