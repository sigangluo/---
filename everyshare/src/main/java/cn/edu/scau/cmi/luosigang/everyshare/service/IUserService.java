package cn.edu.scau.cmi.luosigang.everyshare.service;

import cn.edu.scau.cmi.luosigang.everyshare.entity.User;
import cn.edu.scau.cmi.luosigang.everyshare.response.ResponseStatus;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author luosigang
 * @since 2022-08-14
 */
public interface IUserService extends IService<User> {

    ResponseStatus register(String username, String password, String role);

    ResponseStatus logoff(int id);

    User getLoginUser();
}
