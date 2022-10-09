package com.kindredgroup.unibetlivetest.mapper;

import java.util.Date;

import com.kindredgroup.unibetlivetest.dto.BetDto;
import com.kindredgroup.unibetlivetest.entity.Bet;
import com.kindredgroup.unibetlivetest.entity.Customer;
import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.types.BetState;

public class BetMapper {

	
	public static Bet mapBetDtoToBet(BetDto betDto, Customer customer, Selection selection) {
		
		Bet bet = new Bet();
		bet.setSelection(selection);
		bet.setCustomer(customer);
		bet.setMise(betDto.getMise());
		bet.setDate( new Date());
		return bet;
	}
	
	
	
}
