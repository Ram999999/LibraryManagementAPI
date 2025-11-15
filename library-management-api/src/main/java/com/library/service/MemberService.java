package com.library.service;

import com.library.entity.Member;
import com.library.exception.DuplicateResourceException;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    
    private final MemberRepository memberRepository;
    
    public Member createMember(Member member) {
        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new DuplicateResourceException("Member with email " + member.getEmail() + " already exists");
        }
        if (memberRepository.existsByPhone(member.getPhone())) {
            throw new DuplicateResourceException("Member with phone " + member.getPhone() + " already exists");
        }
        return memberRepository.save(member);
    }
    
    @Transactional(readOnly = true)
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
    }
    
    @Transactional(readOnly = true)
    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("Member not found with email: " + email));
    }
    
    public Member updateMember(Long id, Member memberDetails) {
        Member member = getMemberById(id);
        
        // Check if email is being changed and if the new email already exists
        if (!member.getEmail().equals(memberDetails.getEmail())) {
            if (memberRepository.existsByEmail(memberDetails.getEmail())) {
                throw new DuplicateResourceException("Member with email " + memberDetails.getEmail() + " already exists");
            }
        }
        
        // Check if phone is being changed and if the new phone already exists
        if (!member.getPhone().equals(memberDetails.getPhone())) {
            if (memberRepository.existsByPhone(memberDetails.getPhone())) {
                throw new DuplicateResourceException("Member with phone " + memberDetails.getPhone() + " already exists");
            }
        }
        
        member.setName(memberDetails.getName());
        member.setEmail(memberDetails.getEmail());
        member.setPhone(memberDetails.getPhone());
        member.setMembershipDate(memberDetails.getMembershipDate());
        member.setMembershipType(memberDetails.getMembershipType());
        
        return memberRepository.save(member);
    }
    
    public void deleteMember(Long id) {
        Member member = getMemberById(id);
        memberRepository.delete(member);
    }
    
    @Transactional(readOnly = true)
    public List<Member> searchMembersByName(String name) {
        return memberRepository.findByNameContainingIgnoreCase(name);
    }
    
    @Transactional(readOnly = true)
    public List<Member> getMembersByType(String membershipType) {
        return memberRepository.findByMembershipType(membershipType);
    }
}