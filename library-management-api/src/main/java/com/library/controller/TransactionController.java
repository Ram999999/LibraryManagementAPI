package com.library.controller;

import com.library.entity.Transaction;
import com.library.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    
    private final TransactionService transactionService;
    
    @PostMapping("/issue")
    public ResponseEntity<Transaction> issueBook(
            @RequestParam Long bookId,
            @RequestParam Long memberId) {
        Transaction transaction = transactionService.issueBook(bookId, memberId);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}/return")
    public ResponseEntity<Transaction> returnBook(@PathVariable Long id) {
        Transaction transaction = transactionService.returnBook(id);
        return ResponseEntity.ok(transaction);
    }
    
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }
    
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Transaction>> getTransactionsByMember(@PathVariable Long memberId) {
        List<Transaction> transactions = transactionService.getTransactionsByMemberId(memberId);
        return ResponseEntity.ok(transactions);
    }
    
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Transaction>> getTransactionsByBook(@PathVariable Long bookId) {
        List<Transaction> transactions = transactionService.getTransactionsByBookId(bookId);
        return ResponseEntity.ok(transactions);
    }
    
    @GetMapping("/overdue")
    public ResponseEntity<List<Transaction>> getOverdueTransactions() {
        List<Transaction> transactions = transactionService.getOverdueTransactions();
        return ResponseEntity.ok(transactions);
    }
    
    @GetMapping("/member/{memberId}/active")
    public ResponseEntity<List<Transaction>> getActiveTransactionsByMember(@PathVariable Long memberId) {
        List<Transaction> transactions = transactionService.getActiveTransactionsByMember(memberId);
        return ResponseEntity.ok(transactions);
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Transaction>> getTransactionsByStatus(@PathVariable String status) {
        List<Transaction> transactions = transactionService.getTransactionsByStatus(status);
        return ResponseEntity.ok(transactions);
    }
}