package com.rapi.server.service;

import com.rapi.server.entity.Account;
import com.rapi.server.entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAll();

    List<Transaction> getAllByUserId(long userId);

    List<Transaction> getAllByUserIdInBankId(long userId, long bankId);

    List<Transaction> getAllFromAccountToAccount(long fromId, long toId, int comparatorType );

    Transaction getById(long id);


}
