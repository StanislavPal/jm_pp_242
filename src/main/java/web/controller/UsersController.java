package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UsersController(UserService userService, RoleService roleService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        System.out.println("==========get index============");
        model.addAttribute("users", userService.findAll());
        return "users/index";
    }

    @GetMapping("/new")
    public String add() {
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
        List<Role> roles = roleService.findAll();
        User user = userService.getById(id);

        HashMap<Role, Boolean> hashMap = new HashMap<>();
        for (Role role : roleService.findAll() ) {
            Boolean checked = false;
            for (Role roleOfUser : user.getRoles() ) {
                if (role.equals(roleOfUser)) {
                    checked = true;
                    break;
                }
            }
            hashMap.put(role, checked);
        }
        model.addAttribute("roles", hashMap );
        model.addAttribute("user", user );
        return "users/edit";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id,
                         Model model) {
        userService.delete(id);
        return "redirect:/users";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.create(user);
        System.out.println("-----post--------");
        System.out.println(user);
        return "redirect:/users";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "roles_checkbox", required = false) String[] rolesArr) {
        Set<Role> roles = new HashSet<>();
        if (rolesArr != null) {
            for (String roleName : rolesArr) {
                roles.add(roleService.findOne(roleName));
            }
        }
        user.setRoles(roles);
        userService.update(user);
        return "redirect:/users";
    }
}
