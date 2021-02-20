/**
 * @Since Feb 18, 2021
 * @Author Nayeem Biswas
 * @Project transaction-management
 * @Package com.example.transactionmanagement.model
 */
package com.example.transactionmanagement.model.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.transactionmanagement.common.message.CustomMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Nayeem
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cheque_info")
public class ChequeInfo {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = CustomMessage.NOT_NULL)
	@Size(max = 100, message = CustomMessage.MAX_SIZE)
	@Column(name = "pay_to")
	private String payTo;
	
	@NotNull(message = CustomMessage.NOT_NULL)
	@Size(max = 250, message = CustomMessage.MAX_SIZE)
	@Column(name = "sum_of_ammount")
	private String sumOfAmount;
	
	@NotNull(message = CustomMessage.NOT_NULL)
	@Digits(fraction = 2, integer = 20)
	@Column(name = "taka")
	private Double taka;
	
	@NotNull(message = CustomMessage.NOT_NULL)
	@Size(max = 100, message = CustomMessage.MAX_SIZE)
	@Column(name = "account_no")
	private String accountNo;
	
	@Column(name = "day")
	private String day;
	
	@Column(name = "month")
	private String month;
	
	@Column(name = "year")
	private String year;
	
}
























