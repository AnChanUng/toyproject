package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Builder
public class MemberRequest {

    private String memberId;
    private String password;
    private String name;

}
