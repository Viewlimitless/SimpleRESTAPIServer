package com.rapi.server.service;

import com.rapi.server.entity.Bank;

import java.util.List;

public interface BankService {
    List<Bank> getAll();

    Bank getById(long id);

    Bank save(Bank bank);

    void remove(long id);
}
