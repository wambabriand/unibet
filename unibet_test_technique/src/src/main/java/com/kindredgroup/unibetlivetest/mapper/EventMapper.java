package com.kindredgroup.unibetlivetest.mapper;

import java.util.ArrayList;
import java.util.List;


import com.kindredgroup.unibetlivetest.dto.EventDto;
import com.kindredgroup.unibetlivetest.entity.Event;
import com.kindredgroup.unibetlivetest.entity.Market;
import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.types.SelectionState;

public class EventMapper {
	
	
	public static  List<EventDto> mapEventsToEventsDto(List<Event> events){
		List<EventDto> eventsDto = new ArrayList<EventDto>();
		if(events != null) {
			for(Event event: events) {
				eventsDto.add(mapEventToEventDto(event));
			}
		}
		return eventsDto;
	}
	
	
	public static  EventDto mapEventToEventDto(Event event){
		EventDto eventDto = new EventDto();
		eventDto.setEventId(event.getId());
		eventDto.setEventName(event.getName());
		eventDto.setLive(isLiveEvent(event));
		return eventDto;
	}
	
	public static  boolean isLiveEvent(Event event){
		for(Market market: event.getMarkets()) {
			for(Selection selection: market.getSelections()) {
				if(selection.getState() == SelectionState.OPENED) {
					return true;
				}
			}
		}
		return false;
	}
	
	
		

}
