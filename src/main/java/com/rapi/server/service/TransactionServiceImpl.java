package com.rapi.server.service;

import com.rapi.server.entity.Transaction;
import com.rapi.server.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getAllByUserId(long userId) {
        return transactionRepository.findAll().stream()
                .filter(item -> item.getFrom().getUser().getId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getAllByUserIdInBankId(long userId, long bankId) {
        return transactionRepository.findAll().stream()
                .filter(item -> item.getFrom().getUser().getId() == userId)
                .filter(item -> item.getFrom().getBank().getId() == bankId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getAllFromAccountToAccount(long fromId, long toId, int comparatorType) {
        Comparator<Date> comparator = new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                return comparatorType == 1 ? Long.compare(o1.getTime(), o2.getTime()) : Long.compare(o2.getTime(), o1.getTime());
            }
        };

        return transactionRepository.findAll().stream()
                .filter(item -> item.getFrom().getId() == fromId)
                .filter(item -> item.getTo().getId() == toId)
                .sorted((item1, item2) -> comparator.compare(item1.getDate(), item2.getDate()))
                .collect(Collectors.toList());

    }

    @Override
    public Transaction getById(long id) {
        return transactionRepository.findById(id).get();
    }

}
