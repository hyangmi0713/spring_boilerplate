package jp.co.canon.boilerplate.spring_boilerplate.repository;

import jp.co.canon.boilerplate.spring_boilerplate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
}
