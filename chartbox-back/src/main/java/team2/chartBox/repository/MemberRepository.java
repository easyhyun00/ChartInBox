package team2.chartBox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team2.chartBox.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUserEmail(String userEmail);
    Member findByUserNickname(String userNickname);
}
