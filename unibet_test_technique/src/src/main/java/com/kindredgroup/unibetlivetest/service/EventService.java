package com.kindredgroup.unibetlivetest.service;

import java.util.List;

import com.kindredgroup.unibetlivetest.dto.EventDto;
import com.kindredgroup.unibetlivetest.dto.SelectionDto;
import com.kindredgroup.unibetlivetest.types.SelectionState;

public interface EventService {
	
	/*
	 * un Event est live si et seulement si il contient au moins une selection qui est OPENED
	 * 
	 * */
	public List<EventDto> findLiveEvents(boolean isLive);
	
	public List<SelectionDto> getSelectionsByEventId(Long eventId, SelectionState state);

}
