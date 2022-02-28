package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    // Autowired 어노테이션으로 인해 스프링 컨테이너에 등록된 memberService 객체를 불러온다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // HTML 만 뿌려주는 껍데기
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // <form> 태그에 의해 <input> 태그의 name(Key)값이 전달된다.
    // 이 값은 어디로 가는가 ? 바로 create() 메서드의 인자 값인 MemberForm 의 String name 값에 매칭된다.
    // private String name 이기 때문에 setter 를 통해 자동으로 들어간 값을 우리는 getter 로 꺼내쓰면 된다.
    // 이와 같이 매칭시키기 위해서 MemberForm VO 의 <input> 태그의 key 값을 name 으로 이름을 맞춰놓은 것이다.
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
