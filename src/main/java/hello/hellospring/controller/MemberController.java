package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    
    private final MemberService memberService;

    // Autowired 어노테이션으로 인해 스프링 컨테이너에 등록된 memberService 객체를 불러온다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
