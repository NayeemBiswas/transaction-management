/**
 * @Since Feb 18, 2021
 * @Author Nayeem Biswas
 * @Project transaction-management
 * @Package com.example.transactionmanagement.controller
 */
package com.example.transactionmanagement.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.transactionmanagement.common.message.BaseResponse;
import com.example.transactionmanagement.common.message.CustomMessage;
import com.example.transactionmanagement.model.dto.ChequeDto;
import com.example.transactionmanagement.model.entity.Balance;
import com.example.transactionmanagement.service.ChequeInfoService;

/**
 * @author Nayeem
 *
 */

@Controller
@RequestMapping("cheque")
public class ChequeInfoController {

	@Autowired
	private ChequeInfoService service;
	
	Date date = new Date();
	
	SimpleDateFormat day = new SimpleDateFormat("dd");
	SimpleDateFormat month = new SimpleDateFormat("MM");
	SimpleDateFormat year = new SimpleDateFormat("yyyy");
	
	
//	int days = Integer.parseInt(day.format(date));
//	int months = Integer.parseInt(month.format(date));
//	int years = Integer.parseInt(year.format(date));

	@RequestMapping("/")
	public String chequeForm(Model model) {
		List<ChequeDto> chequeDtos = service.findAllCheque();
		model.addAttribute("cheques", chequeDtos);
		
		int dayWizeCheque = 0;
		int monthWizeCheque =0;
		int yearWizeCheque =0;

		
		for(ChequeDto dto: chequeDtos)
		{
			
			if(dto.getYear().equals(year.format(date)))
			{
				yearWizeCheque++;
				if(dto.getMonth().toString().equals(month.format(date)))
				{
					monthWizeCheque++;
					if(dto.getDay().toString().equals(day.format(date)))
					{
						dayWizeCheque++;
					}
				}
			}
		}
		

		
//		Long dayWizeCheque = service.dayWizeCheque(days);
//		Long monthWizeCheque = service.dayWizeCheque(months);
//		Long yearWizeCheque = service.dayWizeCheque(years);
		
		model.addAttribute("dayWizeCheque", dayWizeCheque);
		model.addAttribute("monthWizeCheque", monthWizeCheque);
		model.addAttribute("yearWizeCheque", yearWizeCheque);
		model.addAttribute("balance", getBalance());
		
		return "inputField";
	}
	
	public Double getBalance()
	{
		return service.getBalance((long) 1).getBalance();
	}

	@RequestMapping("/i")
	public String index() {
		return "index";
	}

	@PostMapping("/add-cheque")
	public String addCheque(@ModelAttribute("cheque") ChequeDto chequeDto, Model model) {
		
		chequeDto.setDay(day.format(date));
		chequeDto.setMonth(month.format(date));
		chequeDto.setYear(year.format(date));
		
		BaseResponse response = service.createOrUpdateCheque(chequeDto);
		

			Balance balance = new Balance();
			
			balance.setId((long) 1);
			balance.setLastUpdate(date);
			balance.setBalance(service.getBalance((long) 1).getBalance() - chequeDto.getTaka());
			service.saveBalance(balance);
		
		
		model.addAttribute("info", chequeDto);

		return "index";
	}


	@GetMapping("/info/{id}")
	public String findChequeById(@PathVariable("id") Long id, Model model) {

		ChequeDto chequeDto = service.findChequeById(id);

		model.addAttribute("info", chequeDto);

		return "index";
	}

}
