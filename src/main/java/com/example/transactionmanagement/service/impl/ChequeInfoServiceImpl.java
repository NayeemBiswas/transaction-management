/**
 * @Since Feb 18, 2021
 * @Author Nayeem Biswas
 * @Project transaction-management
 * @Package com.example.transactionmanagement.service.impl
 */
package com.example.transactionmanagement.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.example.transactionmanagement.common.exception.CustomDataIntegrityViolationException;
import com.example.transactionmanagement.common.exception.RecordNotFoundException;
import com.example.transactionmanagement.common.message.BaseResponse;
import com.example.transactionmanagement.common.message.CustomMessage;
import com.example.transactionmanagement.model.dto.ChequeDto;
import com.example.transactionmanagement.model.entity.ChequeInfo;
import com.example.transactionmanagement.repository.ChequeInfoRepository;
import com.example.transactionmanagement.service.ChequeInfoService;

/**
 * @author Nayeem
 *
 */
public class ChequeInfoServiceImpl implements ChequeInfoService {

	@Autowired
	private ChequeInfoRepository repo;

	@Override
	public BaseResponse createOrUpdateCheque(ChequeDto chequeDto) {

		ChequeInfo chequeInfo = new ChequeInfo();

		BeanUtils.copyProperties(chequeDto, chequeInfo);
		chequeInfo.setCreatedDate(new Date());
		if (chequeInfo.getId() == null) {
			try {
				repo.save(chequeInfo);
				return new BaseResponse(CustomMessage.SAVE_SUCCESS_MESSAGE, HttpStatus.ACCEPTED.value());
			} catch (CustomDataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.SAVE_FAILED_MESSAGE);
			}
		} else {
			try {
				repo.save(chequeInfo);
				return new BaseResponse(CustomMessage.UPDATE_SUCCESS_MESSAGE, HttpStatus.ACCEPTED.value());
			} catch (CustomDataIntegrityViolationException e) {
				throw new CustomDataIntegrityViolationException(CustomMessage.UPDATE_FAILED_MESSAGE);
			}
		}

	}

	@Override
	public ChequeDto findChequeById(Long id) {
		ChequeInfo chequeInfo = repo.findById(id).orElseThrow(() -> new RecordNotFoundException(CustomMessage.NO_RECORD_FOUND));
		ChequeDto chequeDto = new ChequeDto();
		
		BeanUtils.copyProperties(chequeInfo, chequeDto);
		
		return chequeDto;
	}

}
