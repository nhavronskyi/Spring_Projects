package com.example.yearpercentages.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class BotLambda implements RequestHandler<String,String> {
    @Override
    public String handleRequest(String s, Context context) {
        return s.toUpperCase();
    }
}
