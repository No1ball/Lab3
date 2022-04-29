package com.example.springtest.models;

import java.util.ArrayList;

public class Contracts {
    private int id;
    private String CompName;
    private boolean relevance;
    private String fDate;
    private String lDate;

    private int price;
    private ArrayList<String> equipments;

    public boolean SetID(int new_id){ //prototype
        this.id = new_id;
        return true;
    }

    public boolean SetCompName(String new_CompName){ //prototype
        this.CompName = new_CompName;
        return true;
    }

    public boolean SetRelevance(boolean new_relevance){ //prototype
        this.relevance = new_relevance;
        return true;
    }

    public boolean SetFDate(String new_fDate){ //prototype
        this.fDate = new_fDate;
        return true;
    }

    public boolean SetLDate(String new_lDate){ //prototype
        this.lDate = new_lDate;
        return true;
    }

    public boolean SetPrice(int new_price){ //prototype
        this.price = new_price;
        return true;
    }

    public boolean SetEquipments(ArrayList <String> new_device){ //prototype
        this.equipments = new_device;
        return true;
    }

    public String GetCompName(){
        return this.CompName;
    }

    public int GetId(){
        return this.id;
    }

    public boolean GetRelevance(){
        return this.relevance;
    }

    public String GetLDate(){
        return this.lDate;
    }

    public String GetFDate(){
        return this.fDate;
    }

    public int GetPrice(){
        return this.price;
    }

    public ArrayList <String> GetEquipments(){
        return this.equipments;
    }
}
