package team2.chartBox;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import team2.chartBox.model.Member;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
// @SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class ChartBoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChartBoxApplication.class, args);
	}

	@GetMapping("/") // HttpSession 사용
	public String home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember) {
		if (loginMember != null)
			return loginMember.getUserNickname();
		return "비회원";
	}
}
