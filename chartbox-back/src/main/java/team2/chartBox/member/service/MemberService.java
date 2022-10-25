package team2.chartBox.member.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import team2.chartBox.member.entity.Member;
import team2.chartBox.member.repository.MemberRepository;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    private final JavaMailSender mailSender;

    // 전체 List 찾기
    public List<Member> getMemberList() {
        return memberRepository.findAll();
    }

    // Member 객체를 DB에 저장
    public Member saveMember(Member member) {

        // 비밀번호 암호화 하여 저장
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encodedPassword = encoder.encode(member.getUserPassword());
//        member.setUserPassword(encodedPassword);

        memberRepository.save(member);
        return member;
    }

    // 존재하는 userEmail 인지 확인
    public Member duplicateCheckMember(String userEmail) {
        Member findMember = memberRepository.findByUserEmail(userEmail);
        if(findMember == null) {
            // 존재하지 않음 = 회원가입 가능, 로그인 불가능
            return null;
        }
        // 존재함 = 회원가입 불가능, 로그인 가능
        return findMember;
    }

    // 존재하는 userNickname 인지 확인
    public boolean duplicateCheckNickname(String userNickname) {
        Member findMember = memberRepository.findByUserNickname(userNickname);
        if(findMember == null) {
            return false; // 닉네임이 존재하지 않음 = 닉네임 사용가능
        }
        return true; // 닉네임이 존재함 = 닉네임 사용 불가능
    }

    /*
        회원가입
     */
    public String joinMember(Member member) {
        if (duplicateCheckMember(member.getUserEmail()) == null) {
            // DB에 email이 없음 = 회원가입 가능
            if(!duplicateCheckNickname(member.getUserNickname())) {
                // 사용중인 닉네임 아님
                saveMember(member);
                return "success";
            }
            return "nickname";

        }
        // 이미 DB에 중복된 email 있음 = 회원가입 불가능
        return "email";
    }

    /*
        로그인
     */
    public Member loginMember(String userEmail, String userPassword) {

        // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Member findMember = duplicateCheckMember(userEmail);

        if(findMember == null) {
            // DB에 email이 없음 = 로그인 불가능
            return null;
        }
        // email이 존재함
        // DB에 저장된 비밀번호와 입력한 비밀번호가 같으면
        // if (encoder.matches(findMember.getUserPassword(),userPassword)) {
        if(findMember.getUserPassword().equals(userPassword)) {
            return findMember;
        }
        return null;
    }

    /*
        임시 비밀번호 생성
     */
    public String getTmpPassword() {
        char[] charSet = new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String password = "";

        /* 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 조합 */
        int idx = 0;
        for(int i = 0; i < 10; i++){
            idx = (int) (charSet.length * Math.random());
            password += charSet[idx];
        }

        log.info("생성된 임시 비밀번호 = {}" ,password);

        return password;
    }

    /*
        비밀번호 업데이트
     */
    public boolean updatePassword(String tmpPassword, String memberEmail) {

        // memberEmail 회원 존재하는지 찾음
        Member member = memberRepository.findByUserEmail(memberEmail);

        if (member == null)
            return false;

        // 존재하면 임시 비밀번호로 업데이트, DB에 저장
        member.setUserPassword(tmpPassword);
        memberRepository.save(member);

        return true;
    }

    /*
        메일 전송
     */
    public void mailSend(String tmpPassword, String UserEmail) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(UserEmail); // 받는 사람
        message.setSubject("Chart In Box 임시 비밀번호 안내 이메일 입니다."); // 제목
        message.setText("회원님의 임시 비밀번호는 " + tmpPassword + " 입니다." +
                "\n" + "로그인 후 비밀번호 변경해 주세요");
        message.setFrom("chartinbox1234@gmail.com");
        message.setReplyTo("chartinbox1234@gmail.com");

        mailSender.send(message);

        log.info("메일 전송 완료");
    }
}
