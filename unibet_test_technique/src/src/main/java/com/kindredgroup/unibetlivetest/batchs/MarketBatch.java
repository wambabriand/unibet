package com.kindredgroup.unibetlivetest.batchs;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kindredgroup.unibetlivetest.entity.Bet;
import com.kindredgroup.unibetlivetest.entity.Customer;
import com.kindredgroup.unibetlivetest.repository.BetRepository;
import com.kindredgroup.unibetlivetest.repository.CustomerRepository;
import com.kindredgroup.unibetlivetest.types.BetState;
import com.kindredgroup.unibetlivetest.types.SelectionState;

@Component
@Log4j2
@RequiredArgsConstructor
public class MarketBatch {

	
	private static ReentrantLock lock = new ReentrantLock();

	final BetRepository betRepository; 
	final CustomerRepository customerRepository; 

    @Scheduled(cron = "${parameter.timer.cron.payer}" )
     void payerLesParisBatch() {
    	try {
    		log.info("i want to get the lock....");
    		
	    	boolean isLockAcquired = lock.tryLock();
	    	if(isLockAcquired) {
	    		log.info("i want to get the lock SUCCESS");
	    		 try {
	    			 computePaiement();
	    		 } finally {
	    	          lock.unlock();
	    		 }
	    	 }else {
	    		 log.info("i want to get the lock FAIL");
	    	 }	
    	}catch (Exception e) {
			log.error(e.getMessage());
		}
    }
	
	private void computePaiement() {

		List<Bet> bets= betRepository.findByBetStateAndBySelectionStateAndByEvaluation(BetState.WON, SelectionState.CLOSED, false);
		
		log.info("Number of bet to evaluate : " + bets.size());
		for(Bet bet: bets) {
			try {
				payerUnPari(bet);
				log.info(" ID bet: " + bet.getId() + " ID user: "+bet.getCustomer().getId() +" succes" );
			}catch (Exception e) {
				log.error(e.getMessage());
			}
		}			
	}
	
	@Transactional
	private void payerUnPari(Bet bet) {
		BigDecimal curruntWin = bet.getSelection().getCurrentOdd().multiply(bet.getMise());
		
		Optional<Customer> optionalCustomer = customerRepository.findById(bet.getCustomer().getId());
		Optional<Bet> optionalBet = betRepository.findById(bet.getId());
		Bet bet2 = optionalBet.get();
		Customer customer = optionalCustomer.get();
		
		customer.setBalance(curruntWin);
		bet2.setEvaluate(true);
		
		customerRepository.save(customer);
		betRepository.save(bet2);
	} 
	
}
