package main.service;

import main.model.Transaction;
import main.model.User;
import main.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService
{
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository)
    {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactionsByUser(User user)
    {
        return transactionRepository.findByOwner(user);
    }
}
