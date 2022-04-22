package com.example.springtest.models;

public class Devices {
    private String Name;
    private int Price;
    private int countSale = 0;
    private int totalSumm = 0;

    public boolean SetName(String new_name){ //prototype
        this.Name = new_name;
        return true;
    }

    public boolean SetPrice(int new_price){ //prototype
        this.Price = new_price;
        return true;
    }

    public boolean SetCountSale(int new_cntSale){ //prototype
        this.countSale = new_cntSale;
        return true;
    }

    public boolean SetTotalSumm(int new_totalsumm){ //prototype
        this.totalSumm = new_totalsumm;
        return true;
    }

    public String GetName(){
        return this.Name;
    }

    public int GetPrice(){
        return this.Price;
    }

    public int GetCountSale(){
        return this.countSale;
    }

    public int GetTotalSumm(){
        return this.totalSumm;
    }
}
