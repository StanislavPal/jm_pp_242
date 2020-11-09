package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.service.RoleService;

@Controller
@RequestMapping("/admin/roles")
public class RolesController {

    private RoleService roleService;

    @Autowired
    public RolesController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping()
    public String index(Model model) {
        System.out.println("==========get role index============");
        model.addAttribute("roles", roleService.findAll());
        return "roles/index";
    }

    @GetMapping("/new")
    public String add() {
        return "roles/new";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") long id,
                           Model model) {
        model.addAttribute("role", roleService.getById(id) );
        return "roles/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id,
                       Model model) {
        model.addAttribute("role", roleService.getById(id) );
        return "roles/edit";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id,
                         Model model) {
        roleService.delete(id);
        return "redirect:/admin/roles";
    }

    @PostMapping()
    public String create(@RequestParam(value = "name") String name) {
        Role role = new Role();
        role.setName(name);
        roleService.create(role);
        System.out.println("-----post-role-create-------: " + role);
        return "redirect:/admin/roles";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("role") Role role) {
        roleService.update(role);
        return "redirect:/admin/roles";
    }
}
