package com.kindredgroup.unibetlivetest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecificException  extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private int code;
	private String message;
	
}
