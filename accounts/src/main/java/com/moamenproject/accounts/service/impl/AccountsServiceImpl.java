package com.moamenproject.accounts.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.moamenproject.accounts.constants.AccountsConstants;
import com.moamenproject.accounts.dto.CustomerDto;
import com.moamenproject.accounts.entity.Accounts;
import com.moamenproject.accounts.entity.Customer;
import com.moamenproject.accounts.mapper.CustomerMapper;
import com.moamenproject.accounts.repository.AccountsRepository;
import com.moamenproject.accounts.repository.CustomerRepository;
import com.moamenproject.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService{

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
    
        return newAccount;
    }

}
