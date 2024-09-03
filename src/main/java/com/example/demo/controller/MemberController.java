package com.example.demo.controller;

import com.example.demo.dto.MemberRequest;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // 컨트롤러는 컨트롤러라고 명시
@RequestMapping("/members")
public class MemberController{

    @Autowired
    private MemberService memberService;

    @GetMapping
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerMember(@RequestParam String memberId, @RequestParam String pwd, @RequestParam String name){
        memberService.save(MemberRequest.builder().memberId(memberId).password(pwd).name(name).build());
        return "home";
    }
}