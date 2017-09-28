/**
 *rohan magare, 001231457, magare.r@husky.neu.edu
 *ritesh gupta, 001280361, gupta.rite@husky.neu.edu
 *pratiksha shetty, 00121643697, shetty.pr@husky.neu.edu
 *yogita jain, 001643815, jain.yo@husky.neu.edu
 **/
package com.csye6225.demo.controllers;

import com.csye6225.demo.dao.UserDao;
import com.csye6225.demo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class HomeController {


    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) throws Exception {

        model.addAttribute("user", new User());
        return "index";
    }

    @RequestMapping(value = "/register.htm", method = RequestMethod.GET)
    protected ModelAndView registerUser() throws Exception {

        return new ModelAndView("user-register", "user", new User());

    }

    @RequestMapping(value = "/register.htm", method = RequestMethod.POST)
    protected ModelAndView registerNewUser(HttpServletRequest request, @ModelAttribute("user") User user,
                                           BindingResult result) throws Exception {

        try {

            if (userDao.findUserByEmailId(user.getEmailId()) == null) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(hashedPassword);
                userDao.save(user);
                session.invalidate();
                return new ModelAndView("registration");
            } else {
                return new ModelAndView("registration-failed");
            }


        } catch (IllegalStateException e) {
            System.out.println("*** IllegalStateException: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("*** Exception: " + e.getMessage());
        }
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/login.htm", method = RequestMethod.POST)
    protected ModelAndView login(HttpServletRequest request, @ModelAttribute("user") User user,
                                 BindingResult result, Model model) throws Exception {

        try {
            User user1 = userDao.findUserByEmailId(user.getEmailId());
            if (user1 != null) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                boolean flag = passwordEncoder.matches(user.getPassword(), user1.getPassword());
                if (flag) {
                    model.addAttribute("standardDate", new Date());
                    session.setAttribute("userSession", user1);
                    return new ModelAndView("login-successful");
                } else {
                    return new ModelAndView("login-failed");
                }
            } else {
                return new ModelAndView("login-failed");
            }
        } catch (IllegalStateException e) {
            System.out.println("*** IllegalStateException: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("*** Exception: " + e.getMessage());
        }
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
    public String logout(Model model) throws Exception {

        session.invalidate();
        model.addAttribute("user", new User());
        return "index";
    }
}
