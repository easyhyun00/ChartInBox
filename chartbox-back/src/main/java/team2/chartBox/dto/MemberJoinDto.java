package team2.chartBox.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberJoinDto {

    private String userEmail;
    private String userPassword;
    private String userName;
    private String userNickname;

    @Builder
    public MemberJoinDto(String userEmail, String userPassword, String userName, String userNickname) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userNickname = userNickname;
    }
}
