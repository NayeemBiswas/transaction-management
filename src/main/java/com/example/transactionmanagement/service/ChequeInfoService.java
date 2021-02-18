/**
 * @Since Feb 18, 2021
 * @Author Nayeem Biswas
 * @Project transaction-management
 * @Package com.example.transactionmanagement.service
 */
package com.example.transactionmanagement.service;

import com.example.transactionmanagement.common.message.BaseResponse;
import com.example.transactionmanagement.model.dto.ChequeDto;

/**
 * @author Nayeem
 *
 */
public interface ChequeInfoService {
	
	public BaseResponse createOrUpdateCheque(ChequeDto chequeDto);
	
	public ChequeDto findChequeById(Long id);
	
	

}
