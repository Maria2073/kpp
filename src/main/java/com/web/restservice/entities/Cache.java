package com.web.restservice.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class Cache {

    private final ConcurrentHashMap<Function, Integer> hashMap = new ConcurrentHashMap<>();

    private final Logger logger = LoggerFactory.getLogger(Function.class);

    volatile Integer counter = 0;

    public synchronized void putFunction(Function func) {

        if (!isContain(func)) {
            func = putInMap(func);
            counter++;
        }
        else{
            func = reinitialization(func);
        }
        System.out.println(hashMap.size());
        //System.out.println(hashMap.toString());
    }

    public synchronized boolean isContain(Function func) {
        return hashMap.containsKey(func);
    }

    public Function putInMap(Function func)
    {
        hashMap.put(func, counter);
        logger.info("New element in map");
        func.action();
        return func;
    }

    public Function reinitialization(Function func)
    {
        logger.info("There is already such element");
        for(Function functionToFind: hashMap.keySet())
        {
            if (functionToFind.equals(func))
            {
                func.setResultAdd(functionToFind.getResultAdd());
                func.setResultMul(functionToFind.getResultMul());
            }
        }
        return func;
    }

}
