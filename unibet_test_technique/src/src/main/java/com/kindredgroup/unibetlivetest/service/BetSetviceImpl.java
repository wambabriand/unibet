package com.kindredgroup.unibetlivetest.service;

import java.math.BigDecimal;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kindredgroup.unibetlivetest.dto.BetDto;
import com.kindredgroup.unibetlivetest.entity.Bet;
import com.kindredgroup.unibetlivetest.entity.Customer;
import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.exception.CustomException;
import com.kindredgroup.unibetlivetest.exception.SpecificException;
import com.kindredgroup.unibetlivetest.mapper.BetMapper;
import com.kindredgroup.unibetlivetest.repository.BetRepository;
import com.kindredgroup.unibetlivetest.repository.CustomerRepository;
import com.kindredgroup.unibetlivetest.repository.SelectionRepository;
import com.kindredgroup.unibetlivetest.types.ExceptionType;
import com.kindredgroup.unibetlivetest.types.SelectionState;


@Service
@Transactional
public class BetSetviceImpl implements BetSetvice{
	
	@Autowired
	private SelectionRepository selectionRepository; 
	@Autowired
	private CustomerRepository customerRepository; 
	@Autowired
	private BetRepository betRepository;
	
	
	@Override
	public void bet(BetDto betDto) {
		
		 Long selectionId = Long.parseLong(betDto.getSelectionId());
		
		// verifier si la selection existe
		Optional<Selection> optionalSelection = selectionRepository.findById(selectionId);
		if(optionalSelection.isEmpty()) {
			throw new SpecificException(605, "Selection inexistante");
		}
		
		Selection selection = optionalSelection.get();
		if(selection.getState() == SelectionState.CLOSED) {
			throw new SpecificException(602, "Selection fermée");
		}
		if(selection.getCurrentOdd().compareTo(betDto.getCote()) != 0) {
			throw new SpecificException(601, "Changement de cote");
		}

		// vérifier l'utilisateur existe 
		Optional<Customer> optionalCustomer = customerRepository.getCustomerByPseudo("unibest");
		if(optionalCustomer.isEmpty()) {
			throw new CustomException("utilisateur inexistant", ExceptionType.CUSTOMER_NOT_FOUND);
		} 
		
		// vérifier si son solde est suffisant
		Customer customer = optionalCustomer.get();
		if(customer.getBalance().compareTo(betDto.getMise()) < 0) {
			throw new SpecificException(600, "Balance insuffisante");
		}
		
		// verifier si l'utilisateur a déjà cette selection
		long count = customer.getBets()
							.stream()
							.filter(b -> b.getSelection().getId() == selectionId )
							.count();
		if(count != 0) {
			throw new CustomException("Conflit, pari déjà en cours*", ExceptionType.CONFLIT_PARIS_EN_COURS);
		}

		// ajouter le  pari
		Bet bet = BetMapper.mapBetDtoToBet(betDto, customer, selection);
		betRepository.save(bet);
		
		// mettre à jour le solde du client
		BigDecimal balance =  customer.getBalance().subtract(bet.getMise());
		customer.setBalance(balance);
		customerRepository.save(customer);
		
	}

}
