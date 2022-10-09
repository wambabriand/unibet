package com.kindredgroup.unibetlivetest.repository;

import com.kindredgroup.unibetlivetest.entity.Event;
import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.types.SelectionState;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Long> {
	
	
	@Query(" SELECT e FROM Event e WHERE e.id IN ( SELECT s.market.event.id FROM Selection s WHERE s.state = ?1  )  ")
	public List<Event> findByMarkets_Selections_State(SelectionState state);
	

	@Query(" SELECT s FROM Selection s WHERE s.market.event.id = ?1 AND s.state = ?2   ")
	public List<Selection> findByIdAndMarkets_Selections_State(Long idEvent, SelectionState state);
	
}
