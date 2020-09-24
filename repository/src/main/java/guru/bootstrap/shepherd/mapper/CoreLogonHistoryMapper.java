package guru.bootstrap.shepherd.mapper;

import guru.bootstrap.shepherd.po.CoreLogonHistoryPO;
import org.springframework.stereotype.Repository;

@Repository
public interface CoreLogonHistoryMapper {
    int insertCoreLogon(CoreLogonHistoryPO logonHistory);
}
