package vn.hoidanit.laptopshop.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;

    }

    @RequestMapping("/")

    public String getHomePage(Model model) {
        List<User> arrUsers = this.userService.getAllUsersByEmail("segiroberto2004@gmail.com");
        System.out.println(arrUsers);
        model.addAttribute("eric", "test");
        model.addAttribute("dinhhung", "chạy được rồi");
        return "hello";

    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();

        model.addAttribute("user1", users);
        return "admin/user/table-user";
    }

    @RequestMapping("/admin/user/{stt}")
    public String getUserDetailPage(Model model, @PathVariable long stt) {
        System.out.println("CHECK PATH id:  " + stt);
        model.addAttribute("id", stt);
        return "/admin/user/show";
    }

    @RequestMapping("/admin/user/create") // get

    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "/admin/user/create";

    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User hoidanit) {

        this.userService.handleSaveUser(hoidanit);
        return "redirect:/admin/user";// redirect duong link chuyen nghiep

    }
}
// @RestController
// public class UserController {

// @GetMapping("")
// public String getHomePage() {
// return this.userService.handle();
// }

// }
