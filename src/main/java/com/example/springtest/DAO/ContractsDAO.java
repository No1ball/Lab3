package com.example.springtest.DAO;

import com.example.springtest.DAO.DevicesDAO;
import com.example.springtest.models.Contracts;
import com.example.springtest.models.Devices;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContractsDAO {
    private List<Contracts> contracts;
    private ArrayList<String> equipments;
    private ArrayList<String> equipments2;
    {
        contracts = new ArrayList<>();
        equipments = new ArrayList<>();
        equipments2 = new ArrayList<>();
        String one = new String("hz1");
        String two = new String("hz2");

        equipments.add(one);
        equipments.add(two);
        Contracts cont = new Contracts();
        cont.SetCompName("Roga i Kopita");
        cont.SetEquipments(equipments);
        cont.SetID(1);
        cont.SetRelevance(true);
        cont.SetPrice(7871);
        cont.SetFDate("11.04.2022");
        cont.SetLDate("01.05.2022");
        contracts.add(cont);
        String one2 = new String("hz1");
        String two2 = new String("hz2");

        equipments2.add(one2);
        equipments2.add(two2);
        Contracts cont2 = new Contracts();
        cont2.SetCompName("Roga i Kopita2");
        cont2.SetEquipments(equipments2);
        cont2.SetID(2);
        cont2.SetRelevance(true);
        cont2.SetPrice(781271);
        cont2.SetFDate("12.04.2022");
        cont2.SetLDate("12.05.2022");
        contracts.add(cont2);
        Contracts cont3 = new Contracts();
        cont3.SetCompName("Roga i Kopita3");
        cont3.SetEquipments(equipments2);
        cont3.SetID(3);
        cont3.SetRelevance(false);
        cont3.SetPrice(787211);
        cont3.SetFDate("01.04.2022");
        cont3.SetLDate("01.05.2022");
        contracts.add(cont3);

    }

    public List<Contracts> showContracts(){
        return contracts;
    }

    public Contracts showContract(int id){
        return contracts.stream().filter(contracts1 -> contracts1.GetId() == id).findAny().orElse(null);
    }
}
