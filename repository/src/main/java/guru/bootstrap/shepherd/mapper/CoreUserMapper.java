package guru.bootstrap.shepherd.mapper;

import guru.bootstrap.shepherd.po.CoreUserPO;
import org.springframework.stereotype.Repository;

@Repository
public interface CoreUserMapper {
    int insertUserCore(CoreUserPO coreUserPO);
}
