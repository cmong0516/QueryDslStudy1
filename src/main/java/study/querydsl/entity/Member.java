package study.querydsl.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 기본 생성자 막고 싶은데 JPA 스펙상 protected 로 열어둠
@ToString(of = {"id", "username", "age"})
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private int age;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    // 양방향 연관관계 편의 메서드.
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
