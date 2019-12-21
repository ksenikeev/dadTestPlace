package ru.kpfu.icmit.association.controller.gui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String index(HttpServletRequest request, @ModelAttribute("model") ModelMap model) {
        String application_path = request.getContextPath();
        model.addAttribute("application_path", application_path);
        return "/index";
    }
}