package com.moamenproject.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.moamenproject.accounts.constants.AccountsConstants;
import com.moamenproject.accounts.dto.AccountsDto;
import com.moamenproject.accounts.dto.CustomerDto;
import com.moamenproject.accounts.entity.Accounts;
import com.moamenproject.accounts.entity.Customer;
import com.moamenproject.accounts.exception.CustomerAlreadyExistsException;
import com.moamenproject.accounts.exception.ResourceNotFoundException;
import com.moamenproject.accounts.mapper.AccountsMapper;
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
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber" + customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
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
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");
    
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
            ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
            ()-> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();

        if(accountsDto != null){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                ()-> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerID = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerID).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "CustomerID", customerID.toString())
            );
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        

        return isUpdated;

    }

    @Override
    public void deleteAccount(String mobileNumber) {
        
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
            ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber.toString())
        );
        Long customerID = customer.getCustomerId();
        accountsRepository.deleteByCustomerId(customerID);
        customerRepository.deleteById(customerID);

        
    }

}
