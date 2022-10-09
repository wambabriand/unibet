package com.kindredgroup.unibetlivetest.dto;

import java.math.BigDecimal;

import com.kindredgroup.unibetlivetest.types.SelectionResult;
import com.kindredgroup.unibetlivetest.types.SelectionState;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectionDto {
	
	private Long id;
	private Long marketId;
	private String name;
	private SelectionState state;
	private BigDecimal currentOdd;
	private SelectionResult result;

}
