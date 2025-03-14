package study.data_jpa.controller;


import study.data_jpa.entity.Member;

public record MemberDto(Long id, String name, int age) {
    // 기존에 사용하던 정적 팩토리 메서드
    public static MemberDto toDto(Long id, String name, int age) {
        return new MemberDto(id, name, age);
    }

    // 람다식 활용하기 위한
    public MemberDto(Member member) {
        this(member.getId(), member.getUsername(), member.getAge());
    }
}
