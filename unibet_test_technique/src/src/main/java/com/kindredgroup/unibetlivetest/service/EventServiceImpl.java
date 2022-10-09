package com.kindredgroup.unibetlivetest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kindredgroup.unibetlivetest.dto.EventDto;
import com.kindredgroup.unibetlivetest.dto.SelectionDto;
import com.kindredgroup.unibetlivetest.entity.Event;
import com.kindredgroup.unibetlivetest.entity.Market;
import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.exception.CustomException;
import com.kindredgroup.unibetlivetest.mapper.EventMapper;
import com.kindredgroup.unibetlivetest.mapper.SelectionMapper;
import com.kindredgroup.unibetlivetest.repository.EventRepository;
import com.kindredgroup.unibetlivetest.types.ExceptionType;
import com.kindredgroup.unibetlivetest.types.SelectionState;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventRepository eventRepository;
	
	
	@Override
	public List<EventDto> findLiveEvents(boolean isLive) {
		List<Event> events = null ;
		if(isLive) {
			events = eventRepository.findByMarkets_Selections_State(SelectionState.OPENED);
		}else {
			events = eventRepository.findAll();	
		}
		return EventMapper.mapEventsToEventsDto(events);
	}

	@Override
	public List<SelectionDto> getSelectionsByEventId(Long eventId, SelectionState state) {

		Optional<Event> event = eventRepository.findById(eventId);
		if(event.isEmpty()) {
			throw new CustomException("Evénement introuvable", ExceptionType.EVENTS_NOT_FOUND);
		}

		List<Selection> selections = null;
		
		if(state == null) {
			selections = new ArrayList<Selection>();
			for(Market market: event.get().getMarkets()) {
				if(market.getSelections() != null) {
					selections.addAll(market.getSelections());
				}
			}	
		}else {
			selections = eventRepository.findByIdAndMarkets_Selections_State(eventId, state);
		}
		
		return SelectionMapper.mapSelectionsToSelectionsDto(selections);
	}
	
	
}
