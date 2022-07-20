package jp.co.canon.boilerplate.spring_boilerplate.config.auth;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인 진행
// 로그인이 진행 완료되면 스프링 시큐리티 session을 만들어 줌
// 세션 공간은 똑같은데 시큐리티 자신만의 공간을 갖고 있음
// 이 구분은 key값으로 구분하여 이 key값은 "Security ContextHolder"
// 이 키값이 session 저장

// 이 session에 들어가는 정보는 Authentication 타입 객체로 정해져있음
// Authentication 안에는 User 정보가 있어야 됨
// User 오브젝트 타입도 UserDetails 타입 객체로 클래스가 정해져 있음

// Security Session => Authenticaion 객체만 들어갈 수 있음
// Authenticaion => UserDetails 타입으로 정해져 있음

// 따라서 꺼내 쓸때는 Security Session => Authenticaion => UserDetails(PrincipalDetails)로 꺼내면 User에 접근 가능

import jp.co.canon.boilerplate.spring_boilerplate.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class PrincipalDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private User user;
    private Map<String, Object> attributes;

    // 일반 시큐리티 로그인시 사용
    public PrincipalDetails(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 오래 되었는지 확인
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 유무 확인
    @Override
    public boolean isEnabled() {
        // 우리 사이트에 1년동안 회원이 로그인을 안하면 휴먼 계정으로 하기로 함
        // user마다 logindate를 저장하여 1년이 넘었는지 계산
        // 현재시간 - logindate 가 1년을 초과하면 return false;
        return true;
    }

    // 해당 user의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // user권한이 string인데 리턴 타입 Collection<GrantedAuthority>에 맞춰서 변환하여 리턴
        Collection<GrantedAuthority> collet = new ArrayList<GrantedAuthority>();
        collet.add(()->{ return user.getRoles();});
        return collet;
    }
}