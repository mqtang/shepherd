package guru.bootstrap.shepherd.jpa;

import guru.bootstrap.shepherd.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserPO, Long> {

}
