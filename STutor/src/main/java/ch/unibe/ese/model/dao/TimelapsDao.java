package ch.unibe.ese.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.model.Timelaps;

public interface TimelapsDao extends CrudRepository<Timelaps,Long> {

}
