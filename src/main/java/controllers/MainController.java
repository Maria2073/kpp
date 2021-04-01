package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    int resultAdd = 0;
    int resultMul = 0;
    @GetMapping("/home")
    public String home(@RequestParam(name = "firstNumber",  defaultValue = "0") int num1,
                       @RequestParam(name = "secondNumber", defaultValue = "0") int num2,
                       @RequestParam(name = "action", required = false) String action,
                       Model model)
    {
        switch (action)
        {
            case "addition":
                resultAdd = num1 + num2;
                break;
            case "multiplication":
                resultMul = num1 * num2;
                break;
            default:
                resultAdd = resultMul = 0;
                break;
        }
        model.addAttribute("title", "Functions");
        model.addAttribute("ADD", resultAdd);
        model.addAttribute("MUL", resultMul);
        model.addAttribute("number1", num1);
        model.addAttribute("number2", num2);
        return "home";
    }
}