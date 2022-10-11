package team2.chartBox;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import team2.chartBox.model.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@SpringBootApplication
public class ChartBoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChartBoxApplication.class, args);
	}

	@GetMapping("/") // HttpSession 사용
	public String home(@SessionAttribute(name = "loginMember", required = false) Member loginMember) {
		if (loginMember != null)
			return loginMember.getUserNickname();
		return "비회원";
	}
}
