package com.library.service;

import com.library.entity.Book;
import com.library.entity.Member;
import com.library.entity.Transaction;
import com.library.exception.BadRequestException;
import com.library.exception.BookNotAvailableException;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionService {
    
    private final TransactionRepository transactionRepository;
    private final BookService bookService;
    private final MemberService memberService;
    
    private static final double FINE_PER_DAY = 5.0;
    
    public Transaction issueBook(Long bookId, Long memberId) {
        Book book = bookService.getBookById(bookId);
        Member member = memberService.getMemberById(memberId);
        
        if (book.getAvailableCopies() <= 0) {
            throw new BookNotAvailableException("Book is not available for issuing");
        }
        
        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setMember(member);
        transaction.setIssueDate(LocalDate.now());
        transaction.setDueDate(LocalDate.now().plusDays(14));
        transaction.setStatus("ISSUED");
        transaction.setFine(0.0);
        
        bookService.decreaseAvailableCopies(bookId);
        
        return transactionRepository.save(transaction);
    }
    
    public Transaction returnBook(Long transactionId) {
        Transaction transaction = getTransactionById(transactionId);
        
        if (!"ISSUED".equals(transaction.getStatus()) && !"OVERDUE".equals(transaction.getStatus())) {
            throw new BadRequestException("Book has already been returned");
        }
        
        transaction.setReturnDate(LocalDate.now());
        transaction.setStatus("RETURNED");
        
        // Calculate fine if overdue
        if (transaction.getReturnDate().isAfter(transaction.getDueDate())) {
            long daysOverdue = ChronoUnit.DAYS.between(transaction.getDueDate(), transaction.getReturnDate());
            double fine = daysOverdue * FINE_PER_DAY;
            transaction.setFine(fine);
        }
        
        bookService.increaseAvailableCopies(transaction.getBook().getId());
        
        return transactionRepository.save(transaction);
    }
    
    @Transactional(readOnly = true)
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
    }
    
    @Transactional(readOnly = true)
    public List<Transaction> getTransactionsByMemberId(Long memberId) {
        return transactionRepository.findByMemberId(memberId);
    }
    
    @Transactional(readOnly = true)
    public List<Transaction> getTransactionsByBookId(Long bookId) {
        return transactionRepository.findByBookId(bookId);
    }
    
    @Transactional(readOnly = true)
    public List<Transaction> getOverdueTransactions() {
        List<Transaction> overdueTransactions = transactionRepository.findOverdueTransactions(LocalDate.now());
        
        // Update status to OVERDUE
        overdueTransactions.forEach(transaction -> {
            if ("ISSUED".equals(transaction.getStatus())) {
                transaction.setStatus("OVERDUE");
            }
        });
        
        return overdueTransactions;
    }
    
    @Transactional(readOnly = true)
    public List<Transaction> getActiveTransactionsByMember(Long memberId) {
        return transactionRepository.findActiveTransactionsByMember(memberId);
    }
    
    @Transactional(readOnly = true)
    public List<Transaction> getTransactionsByStatus(String status) {
        return transactionRepository.findByStatus(status);
    }
}