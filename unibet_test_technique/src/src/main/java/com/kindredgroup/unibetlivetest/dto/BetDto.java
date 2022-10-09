package com.kindredgroup.unibetlivetest.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BetDto {

	private String selectionId;
	private Long costumerId;
	private BigDecimal cote;
	private BigDecimal mise;
}
