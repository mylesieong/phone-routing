package com.myles.alatest;

import java.util.List;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.nio.charset.Charset;

public class PhoneOperatorTable{

    private List<PrefixCost> costTable;

    public PhoneOperatorTable(){
        this.costTable = new ArrayList<PrefixCost>();
    }
    
    public void loadTable(String path){
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(path), Charset.defaultCharset());
            String line;
            line = reader.readLine();
            while (line != null){
                this.costTable.add(new PrefixCost(line));
                line = reader.readLine();
            } 
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public double search(String token){
        double result = -1;
        int length = 0;

        for (PrefixCost t : costTable){
            String prefix = t.getPrefix();
            int i = token.indexOf(prefix);
            int l = prefix.length();
            if ( i == 0 && l > length ){
                result = t.getCost();
                length = l; 
            }
        }

        return result;
    }

    private void debug(String s){
        System.out.println("[DEBUG]\t" + s);
    }

    private class PrefixCost{

        private String prefix;
        private double cost;

        /*
         * Parse String in format "String<spaces/tabs>Double"
         * int prefix and cost
         */
        public PrefixCost(String s){
            PhoneOperatorTable.this.debug("Parsing string:" + s);
            String[] tokenized = s.split("\t");
            setPrefix(tokenized[0]);
            setCost(Double.valueOf(tokenized[1]));
            PhoneOperatorTable.this.debug("Parsing result:" + this);
        }

        public String getPrefix(){
            return this.prefix;
        }

        public double getCost(){
            return this.cost;
        }

        public void setPrefix(String p){
            this.prefix = p;
        }

        public void setCost(double c){
            this.cost = c;
        }

        @Override 
        public String toString(){
            String result = "[INFO]"  
                + "{prefix: " + this.prefix 
                + ", cost: " + this.cost
                + "}";
            return result;
        }

    } 
} 
