package team2.chartBox.member.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team2.chartBox.SessionConst;
import team2.chartBox.member.entity.Member;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
public class MyPageController {

    // 마이 페이지 - session 정보로 회원 가져오기? userNo 으로 가져오기?
    @GetMapping("/my-page/{userNo}")
    public Member myPage(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember) {
        if (loginMember != null)
            return loginMember;
        return null;
    }

    ///////////////////////////////////////////////////////////

    // 마이 페이지 수정 페이지
    @GetMapping("/my-page/edit")
    public Member myPageEditPage(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember) {
        if (loginMember != null)
            return loginMember;
        return null;
    }

    // 마이 페이지 수정 제출
    @PostMapping("/my-page/edit")
    public String myPageEditSubmit(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                                   @RequestBody Member member) {

        // 비밀번호랑, 닉네임만 수정가능

        return "수정";
    }

    // 회원 탈퇴
    @PostMapping("/my-page/withdrawal")
    public String myPageWithdrawal(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember) {

        // DB 에서 회원 정보 삭제, front 에서 알림창으로 정말로 탈퇴하겠습니까? 버튼 누르면 삭제됨
        return "탈퇴 완료";
    }
}
