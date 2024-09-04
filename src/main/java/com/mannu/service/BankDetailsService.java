package com.mannu.service;

import java.util.List;

import com.mannu.entity.BankDetails;

public interface BankDetailsService {

	public BankDetails saveCustomer(BankDetails bankDetails);
	
	public BankDetails getCustomerByAccountNumber(Long accountNumber);
	
	public List<BankDetails> getAllCustomer();
	
    public BankDetails depositeAmount(Long accountNum , Double amount);
    
    public BankDetails withdrawAmount(Long accountNum , Double amount);
    
    public String deleteAccount(Long accountNum);
    
    public BankDetails updateBankDetails(Long accountNumber , BankDetails bankDetails);
	
}
