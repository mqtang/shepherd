package guru.bootstrap.shepherd.service.user;

import guru.bootstrap.shepherd.mapper.UserMapper;
import guru.bootstrap.shepherd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tangcheng
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

}
// 2020/9/9 21:35
