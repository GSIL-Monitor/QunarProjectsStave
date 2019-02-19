package com.qunar.fintech.plat.admin.system.controller;

import com.qunar.fintech.plat.admin.support.security.AccessLog;
import com.qunar.fintech.plat.admin.support.security.ShiroUtils;
import com.qunar.fintech.plat.admin.support.util.UserMd5Utils;
import com.qunar.fintech.plat.admin.support.web.R;
import com.qunar.fintech.plat.admin.system.dao.entity.MenuDO;
import com.qunar.fintech.plat.admin.system.dao.entity.Tree;
import com.qunar.fintech.plat.admin.system.dao.entity.UserDO;
import com.qunar.fintech.plat.admin.system.dao.mapper.UserDao;
import com.qunar.fintech.plat.admin.system.service.MenuService;
import com.qunar.fintech.util.security.EncodeUtils;
import com.qunar.security.QSSO;
import com.qunar.security.qsso.model.QUser;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController extends BaseController {

    @GetMapping({"/", ""})
    String welcome(Model model) {
        return "redirect:/index";
    }

    @AccessLog("请求访问主页")
    @RequestMapping({"/index"})
    String index(Model model, HttpServletRequest request,HttpSession httpSession) {
        String redirect = processQssoLogin(request,httpSession);
        if (StringUtils.isNotBlank(redirect)) {
            return redirect;
        }
        if (getUser() == null) {
            return "redirect:/login";
        }

        List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
        logger.info("" + menus.size());
        logger.info("" + menus);
        model.addAttribute("menus", menus);
        model.addAttribute("name", getUser().getName());
        logger.info(getUser().getName());
        return "index_v1";
    }

    @GetMapping("/login")
    String login() {
        if (getUser() != null) {
            return "redirect:/index";
        } else {
            return "login";
        }
    }

    @AccessLog("登录")
    @PostMapping("/login")
    @ResponseBody
    R ajaxLogin(String username, String password) {
        password = UserMd5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        logger.error("Token = ", token);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return R.ok();
        } catch (AuthenticationException e) {
            return R.error("用户或密码错误");
        }
    }

    @GetMapping("/logout")
    String logout() {
        ShiroUtils.logout();
        return "redirect:/login";
    }

    @GetMapping("/main")
    String main() {
        return "main";
    }

    @GetMapping("/403")
    String error403() {
        return "403";
    }

    private String processQssoLogin(HttpServletRequest request, HttpSession httpSession) {
        String token = request.getParameter("token");
        if (StringUtils.isBlank(token)) {
            return null;
        }
        QUser qUser = QSSO.getQUser(token);
        if (qUser == null || StringUtils.isBlank(qUser.getUserId())) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("username", qUser.getUserId());
        List<UserDO> users = userMapper.list(map);
        if (CollectionUtils.isEmpty(users) || users.size() != 1) {
            logger.info("cannot find user or user with size is not 1");
            return "redirect:/login?msg=" + EncodeUtils.urlDecode(qUser.getUserId() + "用户不存在");
        }
        try {
            // 将用户id保存到session中
            httpSession.setAttribute("userId", qUser.getUserId());
            SecurityUtils.getSubject().login(new UsernamePasswordToken(qUser.getUserId(), users.get(0).getPassword()));
        } catch (AuthenticationException e) {
            logger.error(e.getMessage(), e);
            return "redirect:/login?msg=" + EncodeUtils.urlDecode(qUser.getUserId() + "登录错误");
        }
        return null;
    }

    @Autowired
    MenuService menuService;

    @Autowired
    UserDao userMapper;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

}
