package com.myles.alatest;

import java.util.HashMap;
import java.io.BufferedReader;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.Charset;

public class PhoneOperatorMap extends HashMap<String, Double>{

    private String operationName;

    public void setName(String name){
        this.operationName = name;
    }

    public String getName(){
        return this.operationName;
    }

    public void load(String path){
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(path), Charset.defaultCharset());
            String line;
            while ((line = reader.readLine()) != null){
                String[] tokenized = line.split("\t");
                String key = tokenized[0]; 
                Double value = Double.valueOf(tokenized[1]);
                put(key, value);
            } 
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
