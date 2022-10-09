package com.kindredgroup.unibetlivetest.types;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ExceptionType {

    CUSTOMER_NOT_FOUND(HttpStatus.NOT_FOUND),

	ERROR_SERVER(HttpStatus.INTERNAL_SERVER_ERROR),
	
	CONFLIT_PARIS_EN_COURS(HttpStatus.CONFLICT),

	EVENTS_NOT_FOUND(HttpStatus.NOT_FOUND),

	NO_SELECTIONS(HttpStatus.NO_CONTENT),
	
	NO_LIVE_EVENTS(HttpStatus.NO_CONTENT);
	
    @Getter
    final HttpStatus status;

    ExceptionType(HttpStatus status) {
        this.status = status;
    }

}
