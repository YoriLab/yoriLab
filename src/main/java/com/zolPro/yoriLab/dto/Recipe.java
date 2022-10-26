package com.zolPro.yoriLab.dto;

public class Recipe {

    int seq;
    String recipeNameName;

    public int getSeq(){

        return this.seq;

    }

    public void setSeq(int seq){

        this.seq = seq;
    }
    public String getRecipeName(){
        return this.recipeNameName;

    }
    public void setRecipeName(String recipeNameName){

        this.recipeNameName = recipeNameName;
    }

}

