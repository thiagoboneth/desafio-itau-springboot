package desafio.itau.springboot.service;

import desafio.itau.springboot.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class TransactionService {

    private final Queue<Transaction> transactions = new ConcurrentLinkedDeque<>();


    public void addTransaction(final Transaction transaction) {
        transactions.add(transaction);
    }

    public void clearTransactions() {
        transactions.clear();
    }

    public DoubleSummaryStatistics getStatistics() {
        OffsetDateTime now = OffsetDateTime.now();

        return transactions.stream()
                // comentado para não precisar esperar
                //.filter(t -> t.getDataHora().isAfter(now.minusSeconds(60)))
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();
    }
}
