package jp.co.canon.boilerplate.spring_boilerplate.service;

import jp.co.canon.boilerplate.spring_boilerplate.dto.UserDTO;
import jp.co.canon.boilerplate.spring_boilerplate.entity.User;
import jp.co.canon.boilerplate.spring_boilerplate.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserInfoById(Long id){
        User user = userRepository.findById(id).orElseGet(User::new);
        return user.toDto();
    }

    @Transactional
    public void createUser(UserDTO userDTO){
        userRepository.save(new User(userDTO));
    }

    @Transactional
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
