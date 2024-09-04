package com.mannu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class BankDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountNumber;
	private String accHolderName;
	private String gender;
	private Double accBalance;
	private String bankName;
	private String accType;
	
	
}
