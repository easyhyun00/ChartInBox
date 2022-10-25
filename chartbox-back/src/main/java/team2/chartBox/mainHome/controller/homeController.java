package team2.chartBox.mainHome.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import team2.chartBox.SessionConst;
import team2.chartBox.mainHome.dto.MainPageMemberDto;
import team2.chartBox.member.entity.Member;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
public class homeController {

    @GetMapping("/")
    public ResponseEntity<MainPageMemberDto> home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember) {
        if (loginMember == null) {
            return null;
        }
        MainPageMemberDto memberDto =
                new MainPageMemberDto(loginMember.getNo(),loginMember.getUserNickname());

        return new ResponseEntity<>(memberDto, HttpStatus.OK); // no, userNickname 정보 넘김 -> no로 마이페이지
    }
}
