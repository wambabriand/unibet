package com.kindredgroup.unibetlivetest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

	private Long eventId;
	private String eventName;
	private boolean isLive;
}
