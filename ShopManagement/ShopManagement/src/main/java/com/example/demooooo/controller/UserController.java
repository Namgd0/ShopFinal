package com.example.demooooo.controller;import com.example.demooooo.customer.dto.CusShopDto;import com.example.demooooo.customer.service.CusShopService;import com.example.demooooo.dto.UserDto;import com.example.demooooo.model.User;import com.example.demooooo.service.UserService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.validation.Errors;import org.springframework.web.bind.annotation.*;import org.springframework.web.servlet.mvc.support.RedirectAttributes;import javax.validation.Valid;import java.util.List;@Controllerpublic class UserController {    @Autowired    UserService userService;    @Autowired    CusShopService cusShopService;    @RequestMapping(value = "/")    public String login(Model model) {        model.addAttribute("user", new User());        return "login";    }    @RequestMapping(value = "/login")    public String loginn(@ModelAttribute("user") UserDto user, Model model, RedirectAttributes ra) {        List<UserDto> listU = userService.getAllUser();        for (UserDto u : listU) {            if (user.getUsername().equals(u.getUsername()) && user.getPassword().equals(u.getPassword()))            {                model.addAttribute("user", u);                model.addAttribute("success", "Khoái khoái, chảy nước miếng :p");                ra.addAttribute("userid", u.getId());                return "redirect:/guest";            }        }        model.addAttribute("loginfail", "Đăng nhập không thành công");        return "login";    }    @GetMapping("/admin")    public String admin(@RequestParam("id") Integer id, Model model,RedirectAttributes redirectAttributes){       UserDto userDto = userService.getUserById(id).get();        if (userDto.getRoles().equals("ADMIN")){            model.addAttribute("user", userDto);//            redirectAttributes.addAttribute("id", userDto.getId());            return "admin";        } else{            model.addAttribute("user", userDto);            return "403";        }    }    @GetMapping("guest")    public String guest(@RequestParam Integer userid, Model model, RedirectAttributes ra){        List<CusShopDto> shop = cusShopService.getShopByNameASC();        List<CusShopDto> shopl = cusShopService.findAllBySelled();        model.addAttribute("cusShop", shop);        model.addAttribute("cusShops", shopl);        UserDto userDto = userService.getUserById(userid).get();        model.addAttribute("user", userDto);        return "guest";    }    @GetMapping("/register")    public String register(Model model) {        model.addAttribute("user", new User());        return "register";    }    @PostMapping("/register")    public String doregister(@Valid @ModelAttribute("user") UserDto user, Errors errors, RedirectAttributes redirectAttributes, Model model) {        boolean check = false;        List<UserDto> listU = userService.getAllUser();        if (errors.hasErrors()){            return "register";        }        for (UserDto u :                listU) {            if (user.getUsername().equals(u.getUsername())) {                    check = true;            }        }        if (check) {            redirectAttributes.addFlashAttribute("errorMessage", "Tên người dùng đã tồn tại...");            return "redirect:/register";        } else {            redirectAttributes.addFlashAttribute("message", "Đăng ký thành công. ");            userService.saveUser(user);        }        return "redirect:/";    }}