/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-7-30
 */
package com.fang.bbks.common.security;

import java.util.List;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.fang.bbks.common.utils.Collections3;
import com.fang.bbks.modules.sys.entity.User;
import com.fang.bbks.modules.sys.service.UserService;

/**
 * @author Lee
 */
public class SystemRealm extends AuthorizingRealm {

//	@Autowired
//	UserService userService;

	/**
	 * 当用户进行访问链接时的授权方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		/*
		 * if (principals == null) {
            throw new AuthorizationException("Principal对象不能为空");
        }

        User user = (User) principals.fromRealm(getName()).iterator().next();
		 * */
		
		Principal principal = (Principal) getAvailablePrincipal(principals);

		User user = null;//userService.getByUserName(principal.getName());
		// 获取用户响应的permission
		List<String> permissions = Collections3.extractToList(user.getRoleList(), "permission");
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		info.addStringPermissions(permissions);

		return info;
	}

	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		System.out.println("------!");
		
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String username = usernamePasswordToken.getUsername();
        
		if (username == null) {
            throw new AccountException("用户名不能为空");
        }
        User user = null;//userService.getByUserName(username);
        if (user == null) {
            throw new UnknownAccountException("用户不存在");
        }

        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
	}

}
