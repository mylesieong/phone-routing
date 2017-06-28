package com.myles.alatest;

import java.util.Map;

public class BruteSearch implements Search<String, Double>{

    private Map<String, Double> map;

    @Override
    public Double search(String token){
        double result = -1;
        int length = 0;

        for ( Map.Entry<String, Double> t : this.map.entrySet() ){
            String prefix = t.getKey();
            int i = token.indexOf(prefix);
            int l = prefix.length();
            if ( i == 0 && l > length ){
                result = t.getValue().doubleValue();
                length = l; 
            }
        }

        return new Double(result);
    } 

    @Override
    public void index(Map<String, Double> map){
        this.map = map;
    }
} 
