package com.web.restservice.entities;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Objects;

public class Function {
    private int firstNumber;
    private int secondNumber;
    private String action;
    private int resultAdd = 0;
    private int resultMul = 0;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Function()
    {
        firstNumber = 0;
        secondNumber = 0;
        action = "";
    }
    public Function(int n1, int n2)
    {
        firstNumber = n1;
        secondNumber = n2;
        action = "";
    }
    public Function(int n1, int n2, String act)
    {
        firstNumber = n1;
        secondNumber = n2;
        action = act;
    }

    public void action()
    {
        switch (action)
        {
            case "addition":
                resultAdd = this.add();
                logger.info("ADDITION");
                break;
            case "multiplication":
                resultMul = this.mul();
                logger.info("MULTIPLICATION");
                break;
            case "":
                resultAdd = this.add();
                resultMul = this.mul();
                logger.info("ADDITION AND MULTIPLICATION");
                break;
            default:
                firstNumber = secondNumber = resultAdd = resultMul = 0;
                logger.info("BAD ACTION INPUT");
                break;
        }
    }
    public int add() { return firstNumber + secondNumber; }
    public int mul()
    {
        return firstNumber * secondNumber;
    }

    public int getResultAdd() {
        return resultAdd;
    }
    public int getResultMul() {
        return resultMul;
    }

    public int getFirstNumber()  { return firstNumber; }
    public int getSecondNumber() { return secondNumber; }
    public String getAction()    { return action; }

    public void setResultAdd(int resultAdd) {
        this.resultAdd = resultAdd;
    }
    public void setResultMul(int resultMul) {
        this.resultMul = resultMul;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Function function = (Function) o;
        return firstNumber == function.firstNumber && secondNumber == function.secondNumber  && action.equals(function.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstNumber, secondNumber, action);
    }

    @Override
    public String toString() {
        return  getClass().getName() + "{" +
                "number1 = " + firstNumber +
                ", number2 = " + secondNumber +
                ", action = " + action + "} " +
                resultAdd + " " + resultMul + "\n";
    }
}
