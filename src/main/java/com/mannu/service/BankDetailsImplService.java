package com.mannu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mannu.entity.BankDetails;
import com.mannu.repo.Bank_Detail_Repository;

@Service
public class BankDetailsImplService implements BankDetailsService {

	@Autowired
	private Bank_Detail_Repository repository;
	
	
	@Override
	public BankDetails saveCustomer(BankDetails bankDetails) {
		System.out.println(bankDetails);
		return  repository.save(bankDetails);
		
	}

	@Override
	public BankDetails getCustomerByAccountNumber(Long accountNumber) {
		BankDetails bankDetails = repository.findById(accountNumber).get();
		System.out.println(bankDetails.getAccountNumber());	
		return bankDetails;
	}

	@Override
	public List<BankDetails> getAllCustomer() {
		List<BankDetails> findAll = repository.findAll();
		return findAll;
	}

	@Override
	public BankDetails depositeAmount(Long accountNum, Double accountBalance) {
		 BankDetails bankDetails = repository.findById(accountNum).get();
      Double totalAmount = bankDetails.getAccBalance() + accountBalance;
         bankDetails.setAccBalance(totalAmount);
    return   repository.save(bankDetails);
		
	}

	@Override
	public BankDetails withdrawAmount(Long accountNum, Double accountBalance) {
		Optional<BankDetails> accNum = repository.findById(accountNum);
		if(accNum.isEmpty()) {
			throw new RuntimeException("account details is not available for that number ; " + accNum);
		}
			BankDetails details = accNum.get();
			if(accountBalance < details.getAccBalance()) {
		Double actualBalance= details.getAccBalance()-accountBalance;
		details.setAccBalance(actualBalance);
	     return	repository.save(details);
			}
			else  
				throw new RuntimeException("balance is less");
			
	}

	@Override
	public String deleteAccount(Long accountNum) {
		if(repository.existsById(accountNum)) {
			repository.deleteById(accountNum);
			return "account has deleted";
		}else
		return "No Details found for this account number";
	}

	@Override
	public BankDetails updateBankDetails(Long accountNumber, BankDetails bankDetails) {
		Optional<BankDetails> accNum = repository.findById(accountNumber);
		if(accNum.isPresent()) {
			BankDetails details = accNum.get();
	
		details.setAccHolderName(bankDetails.getAccHolderName());
		details.setAccBalance(bankDetails.getAccBalance());
		details.setAccType(bankDetails.getAccType());
		details.setBankName(bankDetails.getBankName());
		details.setGender(bankDetails.getGender());
		 
	return	repository.save(details);
		} // If entity does not exist, you might want to throw an exception or return null
        throw new RuntimeException("BankDetails not found with accountNumber: " + accountNumber);
		
	}

}
