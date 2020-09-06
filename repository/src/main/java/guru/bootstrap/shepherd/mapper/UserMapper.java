package guru.bootstrap.shepherd.mapper;

import guru.bootstrap.shepherd.po.UserPO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    UserPO selectOneById(Long userId);
}
