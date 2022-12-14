package team2.chartBox.myPage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.chartBox.SessionConst;
import team2.chartBox.freeBoard.entity.FreeBoard;
import team2.chartBox.freeBoard.repository.FreeBoardRepository;
import team2.chartBox.member.entity.Member;
import team2.chartBox.myPage.dto.*;
import team2.chartBox.myPage.service.MyPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class MyPageController {

    @Autowired
    private MyPageService myPageService;
    private FreeBoardRepository freeBoardRepository;

    /*
        [마이페이지 화면]
        우선 닉네임, 이메일, 작성한 게시물
        추후에 댓글, 스크랩한 영화
     */
    @GetMapping("/my-page")
    public ResponseEntity<MyPageResponse> myPage(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member) {
        MyPageResponse myPageResponse = new MyPageResponse();

        myPageResponse.setUserEmail(member.getUserEmail());
        myPageResponse.setUserNickname(member.getUserNickname());
        myPageResponse.setFreeBoard(myPageService.getFreeBoardList(member.getUserNickname()));

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(myPageResponse,headers, HttpStatus.OK);
    }

    /*
        [정보 수정 화면]
        세션을 통해 닉네임 전송
     */
    @GetMapping("/my-page/edit")
    public ResponseEntity myPageEdit(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member) {
        log.info("이메일 = {}" , member.getUserEmail());

        return ResponseEntity.ok().body(member.getUserEmail());
    }

    /*
        [닉네임 변경]
        변경할 닉네임이 DB에 있는지, 있으면 이미 있는 닉네임입니다.
        없으면 새 닉네임 DB 업데이트
     */
    @PostMapping("/my-page/edit/nickname")
    public ResponseEntity myPageNicknameEdit(@RequestBody EditNicknameResponse editNicknameRes) {
        String msg = myPageService.editNickname(editNicknameRes);
        if (msg.equals("success"))
            return ResponseEntity.ok().body(true);
        return ResponseEntity.badRequest().body(false); // 이미 존재하는 닉네임
    }

    /*
        [비밀번호 변경]
        현재 비밀번호 맞는지 확인, 틀리면 잘못된 비밀번호입니다.
        맞으면 새 비밀번호 DB 업데이트
     */
    @PostMapping("/my-page/edit/password")
    public ResponseEntity myPagePasswordEdit(@RequestBody EditPasswordResponse editPasswordRes) {
        String msg = myPageService.editPassword(editPasswordRes);
        if (msg.equals("success"))
            return ResponseEntity.ok().body(true);
        return ResponseEntity.badRequest().body(false); // 비밀번호 잘못입력
    }

    /*
        [회원 탈퇴]
        DB에 해당 사용자 제거, 세션 제거
     */
    @PostMapping("/my-page/edit/withdraw")
    public ResponseEntity myPageMemberWithdraw(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        Member findMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        String msg = myPageService.withdrawMember(findMember);

        session.invalidate(); // 세션 삭제

        if (msg.equals("success"))
            return ResponseEntity.ok().body(true); // 성공

        return ResponseEntity.badRequest().body(false);
    }

    /*
        [내가 쓴 글 상세 보기]
        그런데 생각해보니깐 자유게시판만이네,,
     */
    @GetMapping("my-page/free-board")
    public List<FreeBoard> myPageMyFreeBoard(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member) {
        return myPageService.getFreeBoardList(member.getUserNickname());
    }

}
