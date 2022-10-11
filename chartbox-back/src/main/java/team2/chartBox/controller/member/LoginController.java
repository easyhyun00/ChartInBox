package team2.chartBox.controller.member;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team2.chartBox.model.Member;
import team2.chartBox.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @PostMapping("/login") // 로그인 제출, 비밀번호 암호화 기능 없음
    public boolean loginSubmit(@RequestBody Member member,
                               HttpServletRequest request, HttpServletResponse response) throws IOException {

        Member loginMember = memberService.loginMember(member.getUserEmail(), member.getUserPassword());
        String redirect_url = "http://localhost:3000";

        if (loginMember == null) {
            log.info("로그인 실패");
            return false;
        }
        log.info("로그인 성공");

        // 세션 생성
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", loginMember);

        return true; // response.sendRedirect(redirect_url);
    }

    @PostMapping("/logout") // 로그아웃
    public boolean logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String redirect_url = "http://localhost:3000";

        //세션 삭제
        HttpSession session = request.getSession(false);
        if (session != null) {
            log.info("세션 삭제");
            session.invalidate();
        }
        return true; // response.sendRedirect(redirect_url);
    }

    /*
    비밀번호 찾기 메일 인증 구현하기! -> 임시 비밀번호 발급 도전해보기!
    */
}
