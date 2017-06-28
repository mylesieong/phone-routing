package com.myles.alatest;

import java.util.Map;

public class AlphabeticSearch implements Search<String, Double>{

    @Override
    public Double search(String token){
        return 1.0;
    } 

    @Override
    public void index(Map<String, Double> map){
    }
} 


