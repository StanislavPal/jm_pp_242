package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;
import web.util.Logger;

@Controller
@RequestMapping("/admin/users")
public class UsersController {

    private UserService userService;
    private RoleService roleService;
    private Logger logger;

    @Autowired
    public UsersController(UserService userService, RoleService roleService, Logger logger) {
        this.roleService = roleService;
        this.userService = userService;
        this.logger = logger;
    }

    @GetMapping()
    public String index(Model model) {
        logger.info("index", User.class);
        model.addAttribute("users", userService.findAll());
        return "users/index";
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("roles", roleService.findAll() );
        model.addAttribute("user", new User().setRole( roleService.findOne("ROLE_USER") ) );
        return "users/new";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") long id,
                           Model model) {
        model.addAttribute("user", userService.getById(id));
        return "users/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id,
                       Model model) {
        User user = userService.getById(id);
        if (user == null) {
            return "redirect:/admin/users";
        }

        user.setPassword("");
        model.addAttribute("roles", roleService.findAll() );
        model.addAttribute("user", user );
        return "users/edit";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id,
                         Model model) {
        logger.info("delete user", id);
        userService.delete(id);
        return "redirect:/admin/users";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "roles_checkbox", required = false) String[] roles) {
        user.setRoles( roleService.findByNames(roles) );
        logger.info("post", user);
        userService.create(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "roles_checkbox", required = false) String[] roles) {
        user.setRoles( roleService.findByNames(roles) );
        logger.info("update", user);
        userService.update(user);
        return "redirect:/admin/users";
    }
}
