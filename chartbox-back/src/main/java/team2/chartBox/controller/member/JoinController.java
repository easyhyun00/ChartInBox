package team2.chartBox.controller.member;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team2.chartBox.model.Member;
import team2.chartBox.service.MemberService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
public class JoinController {

    private final MemberService memberService;

    @PostMapping("/join") // 회원 가입 제출, 비밀번호 암호화 기능 없음
    public boolean joinSubmit(@RequestBody Member member, HttpServletResponse response) throws IOException {

        String redirect_url="http://localhost:3000";

        String s = memberService.joinMember(member);

        if(s.equals("success")) {
            log.info("회원가입 성공 = {}", member.getUserEmail());
            return true; // response.sendRedirect(redirect_url);
        }
        log.info("회원가입 실패 - 존재하는 {}",s);
        return false;
    }
}
