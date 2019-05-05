package com.rapi.server.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@JsonAutoDetect
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "account_number", nullable = false, length = 50)
    private long accountNumber;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "balance", nullable = false)
    private long balance;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @JsonIgnore
    @OneToMany(mappedBy = "from", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactionsFrom;

    @JsonIgnore
    @OneToMany(mappedBy = "to", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactionsTo;


    public Account() {
    }

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public long getAccountNumber() {return accountNumber;}

    public void setAccountNumber(long accountNumber) {this.accountNumber = accountNumber;}

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public long getBalance() {return balance;}

    public void setBalance(long balance) {this.balance = balance;}

    public Bank getBank() {return bank;}

    public void setBank(Bank bank) {this.bank=bank;}

    public List<Transaction> getTransactionsFrom() {
        return transactionsFrom;
    }

    public void setTransactionsFrom(List<Transaction> transactionsFrom) {
        this.transactionsFrom = transactionsFrom;
    }

    public List<Transaction> getTransactionsTo() {
        return transactionsTo;
    }

    public void setTransactionsTo(List<Transaction> transactionsTo) {
        this.transactionsTo = transactionsTo;
    }
}