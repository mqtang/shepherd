package guru.bootstrap.shepherd.service;

import guru.bootstrap.shepherd.po.CoreUserPO;
import guru.bootstrap.shepherd.service.exception.UserException;
import guru.bootstrap.shepherd.service.user.UserServiceDTO;

public interface UserService {
    CoreUserPO register(UserServiceDTO serviceDTO) throws UserException;

    void checkRegister(UserServiceDTO serviceDTO) throws UserException;

    UserServiceDTO login(UserServiceDTO serviceDTO);

}
