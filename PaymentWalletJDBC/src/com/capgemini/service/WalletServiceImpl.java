package com.capgemini.service;

//import java.math.BigDecimal;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Map.Entry;

import com.capgemini.beans.Customer;
//import com.capgemini.beans.Wallet;
//import com.capgemini.exception.DuplicatePhoneNo;
//import com.capgemini.exception.PhoneNoDoesNotExist;
import com.capgemini.repository.WalletRepo;
import com.capgemini.repository.WalletRepoImpl;


public class WalletServiceImpl implements WalletService {
	
	WalletRepo walletRepo = new WalletRepoImpl();
	

	@Override
	public Customer createAccount(String name, String mobileNumber, long amount)
	{
		
		Customer customer=new Customer(name, mobileNumber, amount);
				
//		if(walletRepo.findOne(mobileNumber)!=null)
//		{
//			System.out.println("Already present");
//		}
		
		walletRepo.save(customer);
		return customer;
	}

	@Override
	public Customer showBalance(String mobileNumber)
	{
		if(walletRepo.findOne(mobileNumber)!=null)
			return walletRepo.findOne(mobileNumber);
		else
			return null;
	}

	@Override
	public Customer fundTransfer(String sourceMobileNumber, String targetMobileNumber, long amount) {
		if(walletRepo.findOne(sourceMobileNumber)==null)
		{
			return null;
		}
		if(walletRepo.findOne(targetMobileNumber)==null)
		{
			return null;
		}
		
		this.depositAmount(targetMobileNumber, amount);
		this.withdrawAmount(sourceMobileNumber, amount);
		return walletRepo.findOne(sourceMobileNumber);
	}

	@Override
	public Customer depositAmount(String mobileNumber, long amount) {

		if(walletRepo.findOne(mobileNumber)==null)
		{
			return null;
		}
		Customer customer=walletRepo.findOne(mobileNumber);
		customer.setWallet(customer.getWallet()+amount);
		return customer;
	}

	@Override
	public Customer withdrawAmount(String mobileNumber, long amount) {
		
		if(walletRepo.findOne(mobileNumber)==null)
		{
			return null;
		}
		Customer customer=walletRepo.findOne(mobileNumber);
		customer.setWallet(customer.getWallet()-amount);
		return customer;
	}

}
