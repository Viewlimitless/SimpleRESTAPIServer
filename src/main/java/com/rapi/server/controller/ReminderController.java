package com.rapi.server.controller;

import com.rapi.server.entity.Account;
import com.rapi.server.entity.Bank;
import com.rapi.server.entity.Transaction;
import com.rapi.server.entity.User;
import com.rapi.server.service.AccountService;
import com.rapi.server.service.BankService;
import com.rapi.server.service.TransactionService;
import com.rapi.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReminderController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable("id") long id) {
        return userService.getById(id);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteUser(@PathVariable long id) {
        userService.remove(id);
    }


    @Autowired
    private BankService bankService;

    @RequestMapping(value = "/banks", method = RequestMethod.GET)
    @ResponseBody
    public List<Bank> getAllBanks() {
        return bankService.getAll();
    }

    @RequestMapping(value = "/banks/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Bank getBank(@PathVariable("id") long id) {
        return bankService.getById(id);
    }

    @RequestMapping(value = "/banks", method = RequestMethod.POST)
    @ResponseBody
    public Bank saveBank(@RequestBody Bank bank) {
        return bankService.save(bank);
    }

    @RequestMapping(value = "/banks/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteBank(@PathVariable long id) {bankService.remove(id);}


    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getAllAccounts() {return accountService.getAll();}

    @RequestMapping(value = "/accounts/byuser/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getAllAccountsByUserId(@PathVariable long userId) {
        return accountService.getAllByUserId(userId);}

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Account getAccount(@PathVariable long id) {return accountService.getById(id);}

    @RequestMapping(value = "/accounts/userid/{userId}/bankid/{bankId}", method = RequestMethod.POST)
    @ResponseBody
    public Account saveAccount(@RequestBody Account account, @PathVariable long userId, @PathVariable long bankId) {
        return accountService.save(account,userId,bankId);}

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteAccount(@PathVariable long id) {accountService.remove(id);}

    @RequestMapping(value = "/accounts/{fromId}/to/{toId}/quantity/{quantity}", method = RequestMethod.POST)
    @ResponseBody
    public void accountTransferToAccount(@PathVariable long fromId,@PathVariable long toId,@PathVariable long quantity) {
        accountService.transfer(fromId,toId,quantity);}


    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    @ResponseBody
    public List<Transaction> getAllTransactions() {return transactionService.getAll();}

    @RequestMapping(value = "/transactions/byuser/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Transaction> getAllTransactionsByUserId(@PathVariable long id) {
        return transactionService.getAllByUserId(id);}

    @RequestMapping(value = "/transactions/byuser/{userId}/inbank/{bankId}", method = RequestMethod.GET)
    @ResponseBody

    public List<Transaction> getAllTransactionsByUserInBank(@PathVariable long userId,@PathVariable long bankId) {
        return transactionService.getAllByUserIdInBankId(userId,bankId);}

    @RequestMapping(value = "/transactions/byuser/{fromId}/touser/{toId}/{sortType}", method = RequestMethod.GET)
    @ResponseBody
    public List<Transaction> getAllTransactionsFromAccountToAccount(@PathVariable long fromId, @PathVariable long toId, @PathVariable int sortType) {
        return transactionService.getAllFromAccountToAccount(fromId,toId,sortType);}

    @RequestMapping(value = "/transactions/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Transaction getTransaction(@PathVariable("id") long id) {return transactionService.getById(id);}

}
