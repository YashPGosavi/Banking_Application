package com.bankingApplication.service;

import com.bankingApplication.dto.AccountDto;
import com.bankingApplication.entity.Account;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    AccountDto withdraw(Long id,double amount);
    AccountDto deposit(Long id,double amount);
    List<AccountDto> getAllAccounts();
    void deleteAccountById(Long id);
}
