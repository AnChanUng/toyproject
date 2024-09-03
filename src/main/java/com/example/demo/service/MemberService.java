package com.example.demo.service;

import com.example.demo.dto.MemberRequest;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public void save(MemberRequest memberRequest){
        
        if(isExist(memberRequest.getMemberId())){
            System.out.println("중복 아이디.");
            return;
        }
        Member member = Member.builder().memberId(memberRequest.getMemberId())
                .password(memberRequest.getPassword()).name(memberRequest.getName()).build();
        memberRepository.save(member);

    }
    
    private boolean isExist(String memberId) {
        return memberRepository.existsByMemberId(memberId);
    }
}

