package jp.co.canon.boilerplate.spring_boilerplate.controller;

import jp.co.canon.boilerplate.spring_boilerplate.entity.User;
import jp.co.canon.boilerplate.spring_boilerplate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // View를 리턴
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping({"", "/"})
    public String index() {
        // mustache : 기본폴더 src/main/resources
        // view resolver : prefix(templates), suffix(.mustache)
        return "index"; // src/main/resources/templates/index.mustache
    }

    @GetMapping("/user")
    public @ResponseBody
    String user() {
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody
    String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody
    String manager() {
        return "manager";
    }

    // spring security 기본 화면으로 이동되므로 SecurityConfig에 비활성화 되도록 별도 설정
    // login 페이지 표시
    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    // 회원가입 페이지 표시
    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    // 회원가입 요청
    @PostMapping("/join")
    public String join(User users) {
        String rawPassword = users.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        users.setPassword(encPassword);
        users.setRoles("ROLE_USER");
        userRepository.save(users);    // 회원가입 완료
        return "redirect:/loginForm";
    }

    @Secured("ROLE_ADMIN")  // 복수 불가, 간단히 메소드 하나에만 권한을 주고 싶을 때 사용
    @GetMapping("/info")
    public @ResponseBody String info() {
        return "info";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // data()가 실행되기 직전에 실행됨, 복수 가능
    @GetMapping("/data")
    public @ResponseBody String data() {
        return "data";
    }
}
