package com.web.restservice.controllers;

import com.web.restservice.entities.Cache;
import com.web.restservice.entities.Function;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;



@Controller
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private Cache cache;

    @GetMapping("/home")
    public String home(@RequestParam(name = "firstNumber",  defaultValue = "0") int num1,
                       @RequestParam(name = "secondNumber", defaultValue = "0") int num2,
                       @RequestParam(name = "action", defaultValue = "") String action,
                       Model model)
    {
        Function functions = new Function(num1, num2, action);
        cache.putFunction(functions);

        model.addAttribute("title", "Functions");
        model.addAttribute("ADD", functions.getResultAdd());
        model.addAttribute("MUL", functions.getResultMul());
        model.addAttribute("number1", functions.getFirstNumber());
        model.addAttribute("number2", functions.getSecondNumber());
        return "home";
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleException() {
        logger.info("WE ARE IN handleException");
        return "/error/400.html";
    }

    @PostMapping("/home")
    public ResponseEntity<?> bulkParams(@RequestBody List<Function> bodyList) {
        List<Function> functionList = new LinkedList<>();
        bodyList.forEach((curr)-> {
            try {
                Function functions = new Function(curr.getFirstNumber(), curr.getSecondNumber(), curr.getAction());
                cache.putFunction(functions);
                functionList.add(functions);
            } catch (Exception e) {
                logger.error("Error in LambdaFunction");
            }
        });
        logger.info("Success in  LambdaFunction");
        logger.info("before max");

        int maxMul;
        int minAdd;
        int averageFirst;
        if(!functionList.stream().mapToInt(Function::getResultMul).max().isEmpty())
        {
            maxMul = functionList.stream().mapToInt(Function::getResultMul).max().getAsInt();
            minAdd = functionList.stream().mapToInt(Function::getResultAdd).min().getAsInt();
            averageFirst = functionList.stream().mapToInt(Function::getFirstNumber).sum() / functionList.size();
        }
        else
        {
            maxMul = minAdd = averageFirst = 0;
        }
        String forOutput = functionList +
                            "\nMaxMul = " + maxMul + "\nMinAdd = " + minAdd +
                           "\nAverage first number = " + averageFirst + "\nSize = " + functionList.size();

        return new ResponseEntity<>(forOutput, HttpStatus.OK);
    }
}