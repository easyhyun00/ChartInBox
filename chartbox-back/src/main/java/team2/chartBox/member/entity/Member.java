package team2.chartBox.member.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "member")
@Entity
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

    @Builder
    public Member(String userEmail, String userPassword, String userNickname) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
    }
}
