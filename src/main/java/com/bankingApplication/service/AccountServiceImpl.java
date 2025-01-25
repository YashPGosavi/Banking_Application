package com.bankingApplication.service;

import com.bankingApplication.dto.AccountDto;
import com.bankingApplication.dto.AccountMapping;
import com.bankingApplication.entity.Account;
import com.bankingApplication.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        super();
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
       Account account = AccountMapping.mapToAccount(accountDto);
       Account saveAccount = accountRepository.save(account);
       return  AccountMapping.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Id does not exist"));
        return AccountMapping.mapToAccountDto(account);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Id do not exist"));

        double totalAmount = account.getBalance()-amount;
        if(totalAmount<0){
            throw new RuntimeException("Insufficient amount");
        }

        account.setBalance(totalAmount);
        Account saveAccount = accountRepository.save(account);
        return AccountMapping.mapToAccountDto(saveAccount);
    }


    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Id does not exist"));

        double totalAmount = account.getBalance()+amount;
        account.setBalance(totalAmount);

        Account saveAccount = accountRepository.save(account);
        return AccountMapping.mapToAccountDto(saveAccount);
    }


    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream().map(AccountMapping::mapToAccountDto).collect(Collectors.toList());
    }

    @Override
    public void deleteAccountById(Long id) {
        Account account =accountRepository.findById(id).orElseThrow(() ->new RuntimeException("Id does not exists"));
        accountRepository.deleteById(id);
    }


}
