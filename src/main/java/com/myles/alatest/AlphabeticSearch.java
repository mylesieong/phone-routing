package com.myles.alatest;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class AlphabeticSearch implements Search<String, Double>{

    private AlphabeticNode root = new AlphabeticNode(" ");

    @Override
    public Double search(String token){
        //Travel from tree root, pop first number from string
        //And navigate to tree branch of that poped digit
        //If yes, perform the pop again until there is no branch
        //to navigate to. 
        //If no, check the the last pointing node, return the 
        //map entry value carried by the node if any, otherwise, 
        //return -1
        AlphabeticNode node = this.root;
        for (int i = 0; i<token.length(); i++){
            String pop = token.substring(i, i+1);
            boolean hasFurther = false;
            for (AlphabeticNode n: node.getNodes()){
                if (n.getMark().compareTo(pop) == 0){
                    node = n;
                    hasFurther = true;
                }
            }
            if (!hasFurther){
                return node.getEntry()==null ? -1:node.getEntry().getValue();
            }
        }
                
        // If the token is shorter that matchable prefix, return 
        // the last node that it stops
        return node.getEntry()==null ? -1:node.getEntry().getValue();
    } 

    @Override
    public void index(Map<String, Double> map){
        //Tree root is built as default. Iterate the map entries,
        //for entry k, pop the first digit from k.getKey(). 
        //If there is branch to navigate to, move to the branch and 
        //pop the next digit. 
        //If there is no branch to navigate to but the String still 
        //has char not poped, build a new node with the mark as the 
        //poped digit and navigate to it. 
        //If the String is poped up, assign the entry to the final 
        //traveled node.
        for (Map.Entry<String, Double> t: map.entrySet()){

            AlphabeticNode node = this.root;
            String key = t.getKey();

            for (int i = 0; i < key.length(); i++){

                String pop = key.substring(i, i+1);
                int nodeIndex = -1;

                for (int j = 0; j < node.getNodes().size(); j++){
                    if (node.getNodes().get(j).getMark().compareTo(pop) == 0){
                        nodeIndex = j;
                    }
                }

                if (nodeIndex != -1){   //indicate there is node with this mark
                    node = node.getNodes().get(nodeIndex);
                }else{
                    AlphabeticNode newNode = new AlphabeticNode(pop);
                    node.addNode(newNode);
                    node = newNode;
                }

            } 

            node.setEntry(t);
        }
    }

    public AlphabeticNode getRoot(){
        return this.root;
    }

    public class AlphabeticNode {
        private Map.Entry<String, Double> entry;
        private List<AlphabeticNode> nodes;
        private String mark;
        public AlphabeticNode(String m){
            this.nodes = new ArrayList<AlphabeticNode>();
            this.mark = m;
        }
        public void setEntry(Map.Entry<String, Double> e){
            this.entry = e;
        }
        public Map.Entry<String, Double> getEntry(){
            return this.entry;
        }
        public void addNode(AlphabeticNode n){
            this.nodes.add(n);
        }
        public List<AlphabeticNode> getNodes(){
            return this.nodes;
        }
        public void setMark(String s){
            this.mark = s;
        }
        public String getMark(){
            return this.mark;
        }
        @Override 
        public String toString(){
            StringBuilder sb = new StringBuilder(); 
            //output example: [6 entry:462x1.5 nodes:2]
            sb.append("[");
            sb.append(this.getMark());
            sb.append(" entry:");
            if (this.getEntry() != null){
                sb.append(this.getEntry().getKey());
                sb.append("x");
                sb.append(this.getEntry().getValue());
            }
            sb.append(" nodes:");
            for (AlphabeticNode n: this.getNodes()){
                sb.append(n.getMark());
                sb.append(" ");
            }
            sb.append("]");
            return sb.toString();
        }
    }

    @Override
    public void reset(){
        this.root = new AlphabeticNode(" ");
    }
} 


