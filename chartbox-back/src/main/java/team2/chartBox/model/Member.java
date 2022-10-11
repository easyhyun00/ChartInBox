package team2.chartBox.model;

import lombok.*;
// import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Table(name = "member")
@Entity
// @Data
@Getter @Setter
@AllArgsConstructor
// @NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(name = "USEREMAIL")
    private String userEmail;

    @Column(name = "USERPASSWORD")
    private String userPassword;

    @Column(name = "USERNICKNAME")
    private String userNickname;

    // 생성자
    public Member() {
    }

    // @Builder
    public Member(String userEmail, String userPassword, String userNickname) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
    }

//    public static Member createMember(MemberJoinDto memberJoinDto) {
//        Member member = Member.builder()
//                .userId(memberJoinDto.getUserId())
//                .userPassword(memberJoinDto.getUserPassword())
//                .userName(memberJoinDto.getUserName())
//                .userBirth(memberJoinDto.getUserBirth())
//                .userGender(memberJoinDto.getUserGender())
//                .userNickname(memberJoinDto.getUserNickname())
//                .userTos(memberJoinDto.getUserTos())
//                .build();
//        return member;
//    }
}
