//package team2.chartBox.config;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//// import team2.chartBox.MyPasswordEncoder;
//import team2.chartBox.service.MemberService;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class SecurityConfigTest {
//
//    @Autowired
//    private MemberService memberService;
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Test
//    void password() {
//        CharSequence password = "jh12345!!";
//
//        // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//        String encodePw = bCryptPasswordEncoder.encode(password);
//        System.out.println(encodePw);
//
//        String encodedPassword = bCryptPasswordEncoder.encode(password);
//
//        boolean matches = bCryptPasswordEncoder.matches(password, encodedPassword);
//
//        System.out.println("matches = " + matches);
//
//        System.out.println("password = " + password);
//        System.out.println("encodedPassword = " + encodedPassword);
//    }
//}