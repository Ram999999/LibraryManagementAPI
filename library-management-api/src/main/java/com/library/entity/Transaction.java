package com.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    @NotNull(message = "Book is required")
    private Book book;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", nullable = false)
    @NotNull(message = "Member is required")
    private Member member;
    
    @NotNull(message = "Issue date is required")
    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;
    
    @NotNull(message = "Due date is required")
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;
    
    @Column(name = "return_date")
    private LocalDate returnDate;
    
    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^(ISSUED|RETURNED|OVERDUE)$", 
             message = "Status must be ISSUED, RETURNED, or OVERDUE")
    @Column(nullable = false, length = 20)
    private String status;
    
    @Min(value = 0, message = "Fine cannot be negative")
    @Column(nullable = false)
    private Double fine = 0.0;
    
    @PrePersist
    private void setDefaultValues() {
        if (issueDate == null) {
            issueDate = LocalDate.now();
        }
        if (dueDate == null) {
            dueDate = issueDate.plusDays(14); // Default 14 days borrowing period
        }
        if (status == null) {
            status = "ISSUED";
        }
    }
}