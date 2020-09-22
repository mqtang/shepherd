package guru.bootstrap.shepherd.service.user;

import guru.bootstrap.shepherd.mapper.CoreUserLogonMapper;
import guru.bootstrap.shepherd.mapper.CoreUserMapper;
import guru.bootstrap.shepherd.po.CoreUserLogonPO;
import guru.bootstrap.shepherd.po.CoreUserPO;
import guru.bootstrap.shepherd.service.UserService;
import guru.bootstrap.shepherd.service.exception.UserException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author tangcheng
 */
@Service
public class UserServiceImpl implements UserService {

    private final CoreUserMapper coreUserMapper;
    private final CoreUserLogonMapper logonMapper;

    private final Object regLock = new Object();

    public UserServiceImpl(CoreUserMapper coreUserMapper, CoreUserLogonMapper logonMapper) {
        this.coreUserMapper = coreUserMapper;
        this.logonMapper = logonMapper;
    }

    @Transactional
    @Override
    public CoreUserPO register(UserServiceDTO serviceDTO) throws UserException {
        CoreUserPO userPO = new CoreUserPO();
        userPO.setMemberId(serviceDTO.getUsername());
        userPO.setRegisterIp(serviceDTO.getRegisterIp());
        userPO.setRegisterType(serviceDTO.getRegisterType());
        CoreUserLogonPO logonPO
                = new CoreUserLogonPO(serviceDTO.getRegisterType(), serviceDTO.getUsername(), serviceDTO.getPassword());
        logonPO.setVerified(0);
        logonPO.setLastLogonIp(serviceDTO.getRegisterIp());
        logonPO.setLastLogonTime(new Date());
        synchronized (regLock) {
            int count = logonMapper.countByAuthTypeAndId(logonPO);
            if (count != 0) {
                throw new UserException(UserStatusEnum.MEMBER_ID_EXISTS.getDescEn());
            }
            coreUserMapper.insertUserCore(userPO);
            logonPO.setUserId(userPO.getUserId());
            logonMapper.insertUserCoreLogon(logonPO);
        }
        return userPO;
    }

    @Override
    public void checkRegister(UserServiceDTO registerDTO) throws UserException {
        CoreUserLogonPO logonPO = new CoreUserLogonPO();
        logonPO.setAuthType(0);
        logonPO.setAuthIdentity(registerDTO.getUsername());
        int count = logonMapper.countByAuthTypeAndId(logonPO);
        if (count != 0) {
            throw new UserException(UserStatusEnum.MEMBER_ID_EXISTS.getDescEn());
        }
    }

    @Override
    public UserServiceDTO login(UserServiceDTO serviceDTO) {
        CoreUserLogonPO logonPO
                = new CoreUserLogonPO(serviceDTO.getRegisterType(), serviceDTO.getUsername());
        logonPO = logonMapper.selectByAuthTypeAndId(logonPO);
        serviceDTO.setUserId(logonPO.getUserId());
        serviceDTO.setPassword(logonPO.getAuthKey());
        serviceDTO.setLastLogonIp(logonPO.getLastLogonIp());
        serviceDTO.setLastLogonTime(logonPO.getLastLogonTime());
        return serviceDTO;
    }
}
// 2020/9/9 21:35
