/**
 * @Since Feb 20, 2021
 * @Author Nayeem Biswas
 * @Project transaction-management
 * @Package com.example.transactionmanagement.repository
 */
package com.example.transactionmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transactionmanagement.model.entity.Balance;

/**
 * @author Nayeem
 *
 */

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long>{
	
//	public Double findBalanceByid(Long id);

}
