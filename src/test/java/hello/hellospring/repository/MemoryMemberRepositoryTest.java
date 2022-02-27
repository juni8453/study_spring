package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 각 테스트 종료 마다 실행
    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    // 회원 가입 테스트
    @Test
    @DisplayName("name 을 넣으면 member 가 저장되어야 한다.")
    void saveTest() {
        Member member = new Member();
        member.setName("Tany");
        repository.save(member);

        assertThat(member).isEqualTo(repository.findById(member.getId()).get());
    }

    // 닉네임 찾기 테스트
    @Test
    void findByNameTest() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Member result2 = repository.findByName("spring2").get();

        // result 와 member1 은 같은 객체인가 ?
        assertThat(result).isEqualTo(member1);

        // result2 와 member2 는 같은 객체인가 ?
        assertThat(result2).isEqualTo(member2);
    }

    // 모든 유저 찾기 테스트
    @Test
    void findAllTest() {
        Member member = new Member();
        member.setName("tany");
        repository.save(member);

        Member member2 = new Member();
        member2.setName("tany2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

    // ID 찾기 테스트
    @Test
    void findByIdTest() {

    }
}