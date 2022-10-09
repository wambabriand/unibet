package com.kindredgroup.unibetlivetest.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.kindredgroup.unibetlivetest.entity.Bet;
import com.kindredgroup.unibetlivetest.types.BetState;
import com.kindredgroup.unibetlivetest.types.SelectionState;

public interface BetRepository extends JpaRepository<Bet, Long>{
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
	public Optional<Bet> findById(Long id);
	
	
	@Query("SELECT b FROM Bet b WHERE b.betState = ?1 AND b.selection.state = ?2 AND evaluate = ?3 ")
	public List<Bet> findByBetStateAndBySelectionStateAndByEvaluation(BetState betState, SelectionState selectionState, boolean evaluate);


}
