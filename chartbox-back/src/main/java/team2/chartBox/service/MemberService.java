package team2.chartBox.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.chartBox.model.Member;
import team2.chartBox.repository.MemberRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class MemberService {

    private MemberRepository memberRepository;

    // 전체 List 찾기
    public List<Member> getMemberList() {
        return memberRepository.findAll();
    }

    // Member 객체를 DB에 저장
    public Member saveMember(Member member) {
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

    public boolean duplicateCheckNickname(String userNickname) {
        Member findMember = memberRepository.findByUserNickname(userNickname);
        if(findMember == null) {
            return false; // 닉네임이 존재하지 않음 = 닉네임 사용가능
        }
        return true; // 닉네임이 존재함 = 닉네임 사용 불가능
    }

    // 회원가입
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

//    // 회원가입
//    public boolean joinMember(Member member) {
//        if (duplicateCheckMember(member.getUserEmail()) == null) {
//            // DB에 email이 없음 = 회원가입 가능
//            saveMember(member);
//            return true;
//        }
//        // 이미 DB에 중복된 email 있음 = 회원가입 불가능
//        return false;
//    }

    // 로그인
    public Member loginMember(String userEmail, String userPassword) {
        Member findMember = duplicateCheckMember(userEmail);
        if(findMember == null) {
            // DB에 email이 없음 = 로그인 불가능
            return null;
        }
        // email이 존재함
        // DB에 저장된 비밀번호와 입력한 비밀번호가 같으면
        if (findMember.getUserPassword().equals(userPassword)) {
            return findMember;
        }
        return null;
    }
}
