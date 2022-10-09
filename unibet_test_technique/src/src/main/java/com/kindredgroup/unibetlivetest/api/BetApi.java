package com.kindredgroup.unibetlivetest.api;


import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.LockTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kindredgroup.unibetlivetest.dto.BetDto;
import com.kindredgroup.unibetlivetest.exception.SpecificException;
import com.kindredgroup.unibetlivetest.service.BetSetvice;

@RestController
@RequestMapping(Urls.BASE_PATH)
@CrossOrigin(origins = "*")
public class BetApi {
	
	@Autowired
	private BetSetvice betSetvice;
	
	@PostMapping(Urls.ADD_BET)
	public String  bet(@RequestBody BetDto data, HttpServletResponse response){
		try {
			betSetvice.bet(data);		
		}
		catch(LockTimeoutException e) {
			throw new SpecificException(604, "Un même utilisatur ne peut prendre deux paris en même temps");
		}
		catch(TransactionSystemException e) {
			throw new SpecificException(604, "Un même utilisatur ne peut prendre deux paris en même temps");
		}
		catch(SpecificException e) {
			response.setStatus(e.getCode());
			return e.getMessage();
		}
		return "OK";
	}
	

}
