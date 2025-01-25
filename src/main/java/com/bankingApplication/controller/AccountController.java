package com.bankingApplication.controller;

import com.bankingApplication.dto.AccountDto;
import com.bankingApplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PutMapping("/withdraw/{id}")
    public ResponseEntity<AccountDto> withdrawMoney(@PathVariable Long id, @RequestBody Map<String,Double> request){
        return ResponseEntity.ok(accountService.withdraw(id,request.get("amount")));
    }

    @PutMapping("/deposit/{id}")
    public ResponseEntity<AccountDto> depositMoney(@PathVariable Long id,@RequestBody Map<String,Double>request){
        return ResponseEntity.ok(accountService.deposit(id,request.get("amount")));
    }

    @GetMapping("/allAccounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
//        List<AccountDto> listOfAccounts = accountService.getAllAccounts();
//        return new ResponseEntity<>(listOfAccounts,HttpStatus.FOUND);
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccountById(id);
        return ResponseEntity.ok("Account deleted successfully");
    }
}
