package com.rapi.server.service;

import com.rapi.server.entity.Account;
import com.rapi.server.entity.Transaction;
import com.rapi.server.repository.AccountRepository;
import com.rapi.server.repository.BankRepository;
import com.rapi.server.repository.TransactionRepository;
import com.rapi.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BankRepository bankRepository;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> getAllByUserId(long userId) {
        return accountRepository.findAll().stream()
                .filter(item -> item.getUser().getId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public Account getById(long id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public Account save(Account account, long userId, long bankId) {
        account.setUser(userRepository.findById(userId).get());
        account.setBank(bankRepository.findById(bankId).get());

        if (accountRepository.findAll().stream()
                .filter(item -> item.getBank().getId() == account.getBank().getId())
                .filter(item -> item.getUser().getId() == account.getUser().getId())
                .collect(Collectors.toList()).size() > 0) {
            return null;
        } else {
            return accountRepository.saveAndFlush(account);
        }
    }

    @Override
    public void remove(long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void transfer(long fromId, long toId, long quantity) {
        Optional<Account> fromAccount = accountRepository.findById(fromId);
        Optional<Account> toAccount = accountRepository.findById(toId);
        if (quantity > 0
                && fromId!=toId
                && fromAccount.isPresent()
                && toAccount.isPresent()
                && fromAccount.get().getBalance() >= quantity) {
            fromAccount.get().setBalance(
                    fromAccount.get().getBalance() - quantity);
            toAccount.get().setBalance(
                    toAccount.get().getBalance() + quantity);


//            accountRepository.deleteById(fromId);
            accountRepository.saveAndFlush(fromAccount.get());
//            accountRepository.deleteById(toId);
            accountRepository.saveAndFlush(toAccount.get());
            transactionRepository.save(
                    new Transaction(fromAccount.get()
                            , toAccount.get()
                            , quantity
                            , new Date()
                            , fromAccount.get().getBank()));
        }
    }
}
