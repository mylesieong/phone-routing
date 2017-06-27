package com.myles.alatest;

public abstract class SearchAgent{

    protected Table table;

    public abstract int search();

    public abstract void index();

    public void setTable(Table t){
        this.table = t;
    }

    public Table getTable(){
        return this.table;
    }

}
