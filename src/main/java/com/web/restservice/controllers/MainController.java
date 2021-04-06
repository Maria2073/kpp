package com.web.restservice.controllers;

import com.web.restservice.Function;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    int resultAdd = 0;
    int resultMul = 0;
    @GetMapping("/home")
    public String home(@RequestParam(name = "firstNumber",  defaultValue = "0") int num1,
                       @RequestParam(name = "secondNumber", defaultValue = "0") int num2,
                       @RequestParam(name = "action", defaultValue = "") String action,
                       Model model)
    {

        Function functions = new Function(num1, num2, action);
        switch (action)
        {
            case "addition":
                resultAdd = functions.add(num1, num2);
                logger.info("ADDITION");
                break;
            case "multiplication":
                resultMul = functions.mul(num1, num2);
                logger.info("MULTIPLICATION");
                break;
            default:
                resultAdd = resultMul = 0;
                num1 = num2 = 0;
                logger.info("DEFAULT HAPPENED");
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