package com.bankingApplication.dto;

import com.bankingApplication.entity.Account;

public class AccountMapping {

    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(accountDto.accountHolderName,accountDto.balance);
        return account;
    }

    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance());
        return accountDto;
    }
}
