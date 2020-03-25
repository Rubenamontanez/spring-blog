package com.codeup.springblog.Repository;

        import com.codeup.springblog.Models.User;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
        User findByUsername(String username);
}