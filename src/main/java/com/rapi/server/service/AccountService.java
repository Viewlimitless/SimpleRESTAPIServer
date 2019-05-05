package com.rapi.server.service;

import com.rapi.server.entity.Account;

import java.util.List;

public interface AccountService{
    List<Account> getAll();

    List<Account> getAllByUserId(long userId);

    Account getById(long id);

    Account save(Account account, long userId, long bankId);

    void remove(long id);

    void transfer(long fromid, long toid, long quantity);
}
