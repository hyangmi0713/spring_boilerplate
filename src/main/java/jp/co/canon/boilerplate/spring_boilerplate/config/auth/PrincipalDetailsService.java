package jp.co.canon.boilerplate.spring_boilerplate.config.auth;

import jp.co.canon.boilerplate.spring_boilerplate.entity.User;
import jp.co.canon.boilerplate.spring_boilerplate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcessingUrl("/login");으로 해두었기 때문에
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어있는 loadUserByUsername 함수가 실행됨
@Service
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    // String username : loginForm.html의 user input type의 name와 일치해야 함
    // <input type="text" name="username" placeholder="name" />
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            return null;
        }else {
            // 리턴값 : 시큐리티 session 내부에 (Authentication 내부에 (UserDetails))
            return new PrincipalDetails(user);
        }

    }
}
