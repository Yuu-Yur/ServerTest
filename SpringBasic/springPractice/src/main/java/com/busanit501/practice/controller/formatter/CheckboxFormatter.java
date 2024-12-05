package com.busanit501.practice.controller.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CheckboxFormatter implements Formatter<Boolean> {

    @Override
    public Boolean parse(String text, Locale locale) throws ParseException {
        // text 가 정상값이 아니면 false
        if (text == null || text.equals("")) return false;
        // text 가 on 이면 true 반환 아니면 false
        boolean checkFinished = text.equals("on");
        return checkFinished;
    }

    @Override
    public String print(Boolean object, Locale locale) {
        // object  true, false 를 String 으로 
        return object.toString();
    }
}
