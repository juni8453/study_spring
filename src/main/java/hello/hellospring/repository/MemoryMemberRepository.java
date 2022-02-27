package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private final Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // 회원 저장
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    // 회원 ID 찾기
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    // 회원 닉네임 찾기
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    // 모든 회원 찾기
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // store 내부의 모든 내역 삭제
    @Override
    public void clearStore() {
        store.clear();
    }
}
