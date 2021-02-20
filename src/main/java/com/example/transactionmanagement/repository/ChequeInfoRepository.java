/**
 * @Since Feb 18, 2021
 * @Author Nayeem Biswas
 * @Project transaction-management
 * @Package com.example.transactionmanagement.repository
 */
package com.example.transactionmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transactionmanagement.model.entity.ChequeInfo;


/**
 * @author Nayeem
 *
 */

@Repository
public interface ChequeInfoRepository extends JpaRepository<ChequeInfo, Long> {

//	public Long countByDay(int day);
	
//	public Long countByMonth(int month);
//	
//	public Long countByYear(int year);
}
