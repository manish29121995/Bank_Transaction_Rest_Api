package com.mannu.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mannu.entity.BankDetails;
import com.mannu.service.BankDetailsService;

@RestController
public class BankRestController {

	@Autowired
	private BankDetailsService bankService;
	
	@PostMapping("/save/customer")
  public ResponseEntity<BankDetails> addCustomer(@RequestBody BankDetails bankDetails) {
	  BankDetails saveCustomer = bankService.saveCustomer(bankDetails);
	  return new ResponseEntity<BankDetails>(saveCustomer, HttpStatus.CREATED);
	  
  }
	
@GetMapping("/get/{accNum}")	
public ResponseEntity<BankDetails> getDataByAccNum(@PathVariable Long accNum) {
	BankDetails details = bankService.getCustomerByAccountNumber(accNum);
	return new ResponseEntity<BankDetails>(details, HttpStatus.OK);
}

@GetMapping("/get/all")
 public ResponseEntity<List<BankDetails>> getAllDetails(){
	 List<BankDetails> allCustomer = bankService.getAllCustomer();
	 return new ResponseEntity<List<BankDetails>>(allCustomer,HttpStatus.OK );
 }

 @GetMapping("/withDraw/{accNum}/{balance}")
 public ResponseEntity<BankDetails> withDrawMoney(@PathVariable Long accNum, @PathVariable Double balance){
	BankDetails withdrawAmount = bankService.withdrawAmount(accNum, balance);
	return new ResponseEntity<>(withdrawAmount, HttpStatus.OK);
	
}
 
    @GetMapping("/deposite/{accNum}/{balance}") 
    public ResponseEntity<BankDetails> depositeAmount(@PathVariable Long accNum, @PathVariable Double balance){
    	BankDetails amount = bankService.depositeAmount(accNum, balance);
    	return new ResponseEntity<BankDetails>(amount, HttpStatus.OK);
    	
    }
 
  @DeleteMapping("/delete/{accNum}")
   public ResponseEntity<String> deleteCustomer(@PathVariable Long accNum){
	   String deleteAccount = bankService.deleteAccount(accNum);
	   return new ResponseEntity<String>(deleteAccount, HttpStatus.OK);   
   }

  @PostMapping("/update/{accNum}")
  public ResponseEntity<BankDetails> updateAccount(@PathVariable Long accNum,@RequestBody BankDetails bankDetails){
	  BankDetails updateBankDetails = bankService.updateBankDetails(accNum, bankDetails);
	  return new ResponseEntity<BankDetails>(updateBankDetails, HttpStatus.OK);
	  
  }
}
