package hello.hellospring.service;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository repository;

    // 각 테스트 실행 전
    @BeforeEach
    void beforeEach() {
        repository = new MemoryMemberRepository();
        memberService = new MemberService(repository);
    }

    // 각 테스트 실행 후
    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @Test
    void 회원가입() {
        Member member = new Member();
        member.setName("spring");
        Long memberId = memberService.join(member);
        Member findMember = memberService.findOne(memberId).get();

        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        // 일부러 같은 이름의 member 를 하나 더 만들어준다.
        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        // member1.getName = "spring" 인 상태에서 같은 이름의 member2 를 join 하면 설정 해놓은 예외가 발생해야 한다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));


        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}