/**
 * @Since Feb 18, 2021
 * @Author Nayeem Biswas
 * @Project transaction-management
 * @Package com.example.transactionmanagement.controller
 */
package com.example.transactionmanagement.controller;

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
	
	@RequestMapping("/")
	public String chequeForm()
	{
		return "inputField";
	}
	
    @PostMapping("/add-cheque")
    public String addCheque(@ModelAttribute("cheque") ChequeDto chequeDto, Model model) {
    	
        BaseResponse response = service.createOrUpdateCheque(chequeDto);
        
        if(response.getCode() == 201)
        {
        	model.addAttribute("addBookSuccess", true);
        	return "forward:/cheque/info/"+chequeDto.getId();
        }
        else {
            return CustomMessage.SAVE_FAILED_MESSAGE;
		}
    }
    
    @GetMapping("/info/{id}")
    public String findChequeById(@PathVariable("id") Long id, Model model) {
    	
       ChequeDto chequeDto = service.findChequeById(id);
       
       model.addAttribute("info", chequeDto);
       
       return "index";
    }

}
