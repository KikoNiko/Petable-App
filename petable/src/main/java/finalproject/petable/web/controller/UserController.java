package finalproject.petable.web.controller;

import finalproject.petable.model.dto.users.BaseUserDisplayInfoDTO;
import finalproject.petable.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String viewAdminPage(Model model) {
        List<BaseUserDisplayInfoDTO> allUsers = userService.getAllUsersInfo();
        model.addAttribute("allUsers", allUsers);
        return "admin-page";
    }

}
