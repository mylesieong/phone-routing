package com.myles.alatest;

import java.util.Map;

public class BruteSearch implements Search<String, Double>{

    @Override
    public Double search(String token){
        return 1.5;
    } 

    @Override
    public void index(Map<String, Double> map){
    }
} 
