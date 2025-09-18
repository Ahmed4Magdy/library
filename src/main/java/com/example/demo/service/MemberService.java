package com.example.demo.service;

import com.example.demo.entity.Member;
import com.example.demo.repo.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {


    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    public Member updateMember(Long id, Member member) {

        Optional<Member> existmemeber = memberRepository.findById(id);

        if (existmemeber.isPresent()) {

            Member existing = existmemeber.get();
            existing.setFull_name(member.getFull_name());
            existing.setEmail(member.getEmail());
            existing.setPhone(member.getPhone());
            existing.setAddress(member.getAddress());
            return memberRepository.save(existing);
        }
        throw new RuntimeException("memeber no found id " + id);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}


