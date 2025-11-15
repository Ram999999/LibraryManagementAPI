package com.library.repository;

import com.library.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    List<Transaction> findByMemberId(Long memberId);
    
    List<Transaction> findByBookId(Long bookId);
    
    List<Transaction> findByStatus(String status);
    
    @Query("SELECT t FROM Transaction t WHERE t.status = 'ISSUED' AND t.dueDate < :currentDate")
    List<Transaction> findOverdueTransactions(LocalDate currentDate);
    
    @Query("SELECT t FROM Transaction t WHERE t.member.id = :memberId AND t.status = 'ISSUED'")
    List<Transaction> findActiveTransactionsByMember(Long memberId);
    
    @Query("SELECT t FROM Transaction t WHERE t.book.id = :bookId AND t.status = 'ISSUED'")
    List<Transaction> findActiveTransactionsByBook(Long bookId);
}