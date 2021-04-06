package com.web.restservice;

public class Function {
    private int firstNumber;
    private int secondNumber;
    private String action;

    public Function()
    {
        firstNumber = 0;
        secondNumber = 0;
        action = "";
    }

    public Function(int n1, int n2, String act)
    {
        firstNumber = n1;
        secondNumber = n2;
        action = act;
    }
    public int add(int num1, int num2)
    {
        return num1 + num2;
    }
    public int mul(int num1, int num2)
    {
        return num1 * num2;
    }
    
}
