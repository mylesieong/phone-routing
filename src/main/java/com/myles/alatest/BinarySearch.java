package com.myles.alatest;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class BinarySearch implements Search<String, Double>{

    private List<Map.Entry<String, Double>> list;

    private void debugEcho(int ceiling, int index, int floor){
        System.out.println("[DEBUG] Ceiling-" + ceiling 
                + "Index-" + index
                + "Floor-" + floor);
    }
    @Override
    public Double search(String token){
        int ceiling = this.list.size() - 1;
        int floor = 0;
        int index = (floor + ceiling)/2;

        
        /*
        int debug = 0;
        debugEcho(ceiling, index, floor);
        */
        

        while (ceiling != floor + 1){
            int c = comparePrefix(token, this.list.get(index).getKey());
            if ( c == 1 ){
                floor = index;
                index = (index + ceiling)/2;
            }else if ( c == -1 ){
                ceiling = index;
                index = (index + floor)/2;
            }else if ( c == 0 ){
                return this.list.get(index).getValue(); 
            }
            
            /*
            if (debug <20){
                debugEcho(ceiling, index, floor);
                debug++;
            }
            */
            
        }

        if (index == 0){
            return new Double(-1);
        }
        if (token.indexOf(this.list.get(index).getKey())==0){
            return this.list.get(index - 1).getValue(); 
        }else{
            return new Double(-1);
        }
    } 

    @Override
    public void index(Map<String, Double> map){
        this.list = new ArrayList<Map.Entry<String, Double>>();
        for ( Map.Entry<String, Double> t : map.entrySet() ){
            // Insert t into list by order
            if (list.isEmpty()){
                list.add(t);
            }else{

                String key = t.getKey();
                int i = 0;

                while (i<list.size()){

                    if (comparePrefix(key, list.get(i).getKey()) == -1){
                        break;
                    }

                    i++;
                } 

                list.add(i,t);
            }
        }

    }

    /**
     * Compare two string according to order of key that is sorted
     * from the lead digit of the string. 
     * e.g. 28 is 'bigger' than 123
     *      281 is 'bigger' than 28
     * @param String comparatee
     * @param String comparater
     * @return int return 1 when comparatee larger than comparater 
     *             return 0 when they are same 
     *             return -1 when reverse
     */
    public int comparePrefix(String comparatee, String comparater){
        double tee = Double.parseDouble(comparatee);
        while (tee >= 1){
            tee = tee/10;
        }

        double ter = Double.parseDouble(comparater);
        while (ter >= 1){
            ter = ter/10;
        }

        int result = 0;
        if (tee > ter){
            result = 1;
        }

        if (tee == ter){
            result = 0;
        }

        if (tee < ter){
            result = -1;
        }

        return result;
    }

} 
