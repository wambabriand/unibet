package com.kindredgroup.unibetlivetest.repository;

import com.kindredgroup.unibetlivetest.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Lock(LockModeType.PESSIMISTIC_READ)
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "5000")})
    Optional<Customer> getCustomerByPseudo(String pseudo);

		
}
