package com.example.springtest.models;

import java.util.ArrayList;

public class Contracts {
    private String id;
    private String CompName;
    private boolean relevance;
    private int fDate;
    private int lDate;
    private int price;
    private ArrayList<Devices> equipments;

    public boolean SetID(String new_id){ //prototype
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

    public boolean SetFDate(int new_fDate){ //prototype
        this.fDate = new_fDate;
        return true;
    }

    public boolean SetLDate(int new_lDate){ //prototype
        this.lDate = new_lDate;
        return true;
    }

    public boolean SetPrice(int new_price){ //prototype
        this.price = new_price;
        return true;
    }

    public boolean SetEquipments(ArrayList <Devices> new_device){ //prototype
        this.equipments = new_device;
        return true;
    }

    public String GetCompName(){
        return this.CompName;
    }

    public String GetId(){
        return this.id;
    }

    public boolean GetRelevance(){
        return this.relevance;
    }

    public int GetLDate(){
        return this.lDate;
    }

    public int GetFDate(){
        return this.fDate;
    }

    public int GetPrice(){
        return this.price;
    }

    public ArrayList <Devices> GetEquipments(){
        return this.equipments;
    }
}
