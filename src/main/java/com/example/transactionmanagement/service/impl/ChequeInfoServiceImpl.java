/**
 * @Since Feb 18, 2021
 * @Author Nayeem Biswas
 * @Project transaction-management
 * @Package com.example.transactionmanagement.service.impl
 */
package com.example.transactionmanagement.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.transactionmanagement.common.exception.CustomDataIntegrityViolationException;
import com.example.transactionmanagement.common.exception.RecordNotFoundException;
import com.example.transactionmanagement.common.message.BaseResponse;
import com.example.transactionmanagement.common.message.CustomMessage;
import com.example.transactionmanagement.model.dto.ChequeDto;
import com.example.transactionmanagement.model.entity.Balance;
import com.example.transactionmanagement.model.entity.ChequeInfo;
import com.example.transactionmanagement.repository.BalanceRepository;
import com.example.transactionmanagement.repository.ChequeInfoRepository;
import com.example.transactionmanagement.service.ChequeInfoService;

/**
 * @author Nayeem
 *
 */
@Service
public class ChequeInfoServiceImpl implements ChequeInfoService {

	@Autowired
	private ChequeInfoRepository repo;
	
	@Autowired
	private BalanceRepository balanceRepo;

	@Override
	public BaseResponse createOrUpdateCheque(ChequeDto chequeDto) {

		ChequeInfo chequeInfo = new ChequeInfo();
		
		chequeInfo.setDay(chequeDto.getDay());

		BeanUtils.copyProperties(chequeDto, chequeInfo);
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
		
		return copyEntityToDto(chequeInfo);
	}

	@Override
	public List<ChequeDto> findAllCheque() {
		List<ChequeInfo> chequeInfos = repo.findAll();
		
		return chequeInfos.stream().map(cheque -> copyEntityToDto(cheque)).collect(Collectors.toList());
	}

//	@Override
//	public Long dayWizeCheque(int day) {
//		
//		return repo.countByDay(day);
//	}
//
//	@Override
//	public Long monthWizeCheque(int month) {
//		// TODO Auto-generated method stub
//		return repo.countByMonth(month);
//	}
//
//	@Override
//	public Long yearWizeCheque(int year) {
//		// TODO Auto-generated method stub
//		return repo.countByYear(year);
//	}
	
	ChequeDto copyEntityToDto(ChequeInfo chequeInfo)
	{
		ChequeDto chequeDto = new ChequeDto();
		
		BeanUtils.copyProperties(chequeInfo, chequeDto);
		
		return chequeDto;
		
	}

	@Override
	public Balance getBalance(Long id) {
		
		return balanceRepo.findById(id).orElseThrow(() -> 
		new RecordNotFoundException(CustomMessage.NO_RECORD_FOUND));
	}

	
	@Override
	public void saveBalance(Balance balance) {
		
		 balanceRepo.save(balance);
	}

}
