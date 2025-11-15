package com.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(nullable = false, length = 100)
    private String name;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    @Column(nullable = false, length = 10)
    private String phone;
    
    @NotNull(message = "Membership date is required")
    @Column(name = "membership_date", nullable = false)
    private LocalDate membershipDate;
    
    @NotBlank(message = "Membership type is required")
    @Pattern(regexp = "^(STANDARD|PREMIUM|STUDENT)$", 
             message = "Membership type must be STANDARD, PREMIUM, or STUDENT")
    @Column(name = "membership_type", nullable = false, length = 20)
    private String membershipType;
    
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Transaction> transactions = new ArrayList<>();
    
    @PrePersist
    private void setDefaultMembershipDate() {
        if (membershipDate == null) {
            membershipDate = LocalDate.now();
        }
    }
}