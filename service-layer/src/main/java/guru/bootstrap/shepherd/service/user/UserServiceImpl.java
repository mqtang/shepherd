package guru.bootstrap.shepherd.service.user;

import guru.bootstrap.shepherd.mapper.CoreLogonHistoryMapper;
import guru.bootstrap.shepherd.mapper.CoreUserLogonMapper;
import guru.bootstrap.shepherd.mapper.CoreUserMapper;
import guru.bootstrap.shepherd.po.CoreLogonHistoryPO;
import guru.bootstrap.shepherd.po.CoreUserLogonPO;
import guru.bootstrap.shepherd.po.CoreUserPO;
import guru.bootstrap.shepherd.service.UserService;
import guru.bootstrap.shepherd.service.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author tangcheng
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CoreUserMapper coreUserMapper;
    @Autowired
    private CoreUserLogonMapper userLogonMapper;
    @Autowired
    private CoreLogonHistoryMapper logonHistoryMapper;

    private final Object regLock = new Object();

    @Transactional
    @Override
    public CoreUserPO register(UserServiceDTO serviceDTO) throws UserException {
        CoreUserPO userPO = new CoreUserPO();
        userPO.setMemberId(serviceDTO.getUsername());
        userPO.setRegisterIp(serviceDTO.getAuthIp());
        userPO.setRegisterType(serviceDTO.getAuthType());
        CoreUserLogonPO logonPO
                = new CoreUserLogonPO(serviceDTO.getAuthType(), serviceDTO.getUsername(), serviceDTO.getPassword());
        logonPO.setVerified(0);
        logonPO.setLastLogonIp(serviceDTO.getAuthIp());
        logonPO.setLastLogonTime(new Date());
        synchronized (regLock) {
            int count = userLogonMapper.countByAuthTypeAndId(logonPO);
            if (count != 0) {
                throw new UserException(UserStatusEnum.MEMBER_ID_EXISTS.getDescEn());
            }
            coreUserMapper.insertUserCore(userPO);
            logonPO.setUserId(userPO.getUserId());
            userLogonMapper.insertUserCoreLogon(logonPO);
        }
        return userPO;
    }

    @Override
    public void checkRegister(UserServiceDTO registerDTO) throws UserException {
        CoreUserLogonPO logonPO = new CoreUserLogonPO();
        logonPO.setAuthType(0);
        logonPO.setAuthIdentity(registerDTO.getUsername());
        int count = userLogonMapper.countByAuthTypeAndId(logonPO);
        if (count != 0) {
            throw new UserException(UserStatusEnum.MEMBER_ID_EXISTS.getDescEn());
        }
    }

    @Override
    public UserServiceDTO login(UserServiceDTO serviceDTO) throws UserException {
        CoreUserLogonPO logonPO
                = new CoreUserLogonPO(serviceDTO.getAuthType(), serviceDTO.getUsername());
        logonPO = userLogonMapper.selectByAuthTypeAndId(logonPO);
        if (logonPO == null) {
            throw new UserException(UserStatusEnum.MEMBER_NOT_EXISTS.getDescEn());
        }
        serviceDTO.setUserId(logonPO.getUserId());
        serviceDTO.setPassword(logonPO.getAuthKey());
        serviceDTO.setLastLogonIp(logonPO.getLastLogonIp());
        serviceDTO.setLastLogonTime(logonPO.getLastLogonTime());
        CoreLogonHistoryPO historyPO = new CoreLogonHistoryPO();
        historyPO.setUserId(logonPO.getUserId());
        historyPO.setAuthType(serviceDTO.getAuthType());
        historyPO.setLogonIp(serviceDTO.getLogonIp());
        historyPO.setUserAgent(serviceDTO.getUserAgent());
        historyPO.setLogonTime(serviceDTO.getLogonTime());
        logonHistoryMapper.insertCoreLogon(historyPO);
        return serviceDTO;
    }
}
// 2020/9/9 21:35
