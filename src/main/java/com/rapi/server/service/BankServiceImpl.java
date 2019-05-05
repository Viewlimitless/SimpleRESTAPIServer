package com.rapi.server.service;

import com.rapi.server.entity.Bank;
import com.rapi.server.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;

    @Override
    public List<Bank> getAll() {
        return bankRepository.findAll();
    }

    @Override
    public Bank getById(long id) {
        return bankRepository.findById(id).get();
    }

    @Override
    public Bank save(Bank bank) {
        return bankRepository.saveAndFlush(bank);
    }

    @Override
    public void remove(long id) {
        bankRepository.deleteById(id);
    }
}
