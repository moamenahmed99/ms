package com.moamenproject.accounts.service;

import com.moamenproject.accounts.dto.CustomerDto;

public interface IAccountsService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    void deleteAccount(String mobileNumber);

}
