package ee.ttu.idk0071.organizedchaos.user;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findById(Long id);
}
