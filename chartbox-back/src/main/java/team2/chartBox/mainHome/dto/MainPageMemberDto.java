package team2.chartBox.mainHome.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainPageMemberDto {
    private Integer no;
    private String UserNickname;

    public MainPageMemberDto() {
    }

    public MainPageMemberDto(Integer no, String userNickname) {
        this.no = no;
        this.UserNickname = userNickname;
    }
}
