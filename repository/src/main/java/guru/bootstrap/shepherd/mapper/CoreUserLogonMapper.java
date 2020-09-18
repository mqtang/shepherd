package guru.bootstrap.shepherd.mapper;

import guru.bootstrap.shepherd.po.CoreUserLogonPO;
import org.springframework.stereotype.Repository;

@Repository
public interface CoreUserLogonMapper {
    int insertUserCoreLogon(CoreUserLogonPO logonPO);

    int countByAuthTypeAndId(CoreUserLogonPO logonPO);

    CoreUserLogonPO selectByAuthTypeAndId(CoreUserLogonPO logonPO);
}
