package com.kindredgroup.unibetlivetest.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kindredgroup.unibetlivetest.dto.EventDto;
import com.kindredgroup.unibetlivetest.dto.SelectionDto;
import com.kindredgroup.unibetlivetest.exception.CustomException;
import com.kindredgroup.unibetlivetest.service.EventService;
import com.kindredgroup.unibetlivetest.types.ExceptionType;
import com.kindredgroup.unibetlivetest.types.SelectionState;

@RestController
@Log4j2
@RequestMapping(Urls.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EventApi {


	
	private final EventService eventService;
	
 
	@GetMapping(Urls.EVENTS)
	public List<EventDto> getEvents(@RequestParam(required = false) boolean isLive){
		List<EventDto> events = eventService.findLiveEvents(isLive);	
		if(events.size() == 0) { 
			throw new CustomException("Pas de live", ExceptionType.NO_LIVE_EVENTS);
		}
		return events;
	}


	@GetMapping(Urls.SELECTIONS)
	public List<SelectionDto> getSelectionsByEventId(
			@PathVariable Long id, 
			@RequestParam(required = false) SelectionState state){
		List<SelectionDto> selections = eventService.getSelectionsByEventId(id, state);	
		if(selections.size() == 0) { 
			throw new CustomException("Pas de résultat", ExceptionType.NO_SELECTIONS);
		}
		return selections;
	}




}
