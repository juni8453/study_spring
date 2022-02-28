package hello.hellospring.controller;

public class MemberForm {
    // createMemberForm.html 의 input 태그 내 name:name 에 매칭되서 값이 저장될 것.
    private String name;

    public MemberForm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }
}
