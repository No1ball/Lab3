package com.example.springtest.service.deviceService.impl;

import com.example.springtest.entity.ClientsSqlDao;
import com.example.springtest.entity.ContractsSqlDao;
import com.example.springtest.entity.DevicesSqlDao;
import com.example.springtest.repos.ContractsRepo;
import com.example.springtest.repos.DevicesRepo;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService{
    @Autowired
    private DevicesRepo devicesRepo;
    @Autowired
    private ContractsRepo contractsRepo;
    public static final String FONT = "./src/main/resources/arialmt.ttf";
    @Override
    public List<DevicesSqlDao> search(String name){
        return devicesRepo.findByNameContainingIgnoreCaseOrderByName(name);
    }
    @Override
    public Iterable<DevicesSqlDao> addDevice(DevicesSqlDao devices){
        devices.setTotalSumm();
        String[] array = devices.getTempStr().split(",");
        List <Integer> intsList = new ArrayList<Integer>(array.length);
        for (int i = 0; i < array.length; i++){
            intsList.add(i, Integer.parseInt(array[i]));
        }

        Iterable<ContractsSqlDao> cont = contractsRepo.findAllById(intsList);
        List<ContractsSqlDao> target = new ArrayList<>();
        cont.forEach(target::add);
        devices.setContract(target);
        for(int i = 0; i < target.size(); i++){
            ContractsSqlDao tem= target.get(i);
            tem.setOneEquip(devices);
            tem.setPrice();
        }
        List<DevicesSqlDao>dev = Arrays.asList(devices);
        return devicesRepo.saveAll(dev);
    }
    @Override
    public void delDev(int id){
        devicesRepo.deleteById(id);
    }
    @Override
    public DevicesSqlDao putDec(int id, DevicesSqlDao devices){
        DevicesSqlDao device = devicesRepo.findById(id).orElseThrow();
        device.setName(devices.getName());
        device.setPrice(devices.getPrice());
        device.setCountSale(devices.getCountSale());
        device.setTotalSumm();
        return devicesRepo.save(device);
    }
    @Override
    public List<DevicesSqlDao> getDevices(){
        return devicesRepo.findAll();
    }

    @Override
    public List<DevicesSqlDao> sortByPrice(String name){
        List<DevicesSqlDao> deviceList;
        if(name.equals("")){
            deviceList = devicesRepo.findAll();
        }else{
            deviceList = devicesRepo.findByNameContainingIgnoreCaseOrderByName(name);
        }
        return deviceList.stream().sorted(Comparator.comparingInt(DevicesSqlDao::getTotalSumm).reversed()).collect(Collectors.toList());
    }
    @Override
    public List<DevicesSqlDao> sortByCount(String name){
        List<DevicesSqlDao> deviceList;
        if(name.equals("")){
            deviceList = devicesRepo.findAll();
        }else{
            deviceList = devicesRepo.findByNameContainingIgnoreCaseOrderByName(name);
        }
        return deviceList.stream().sorted(Comparator.comparingInt(DevicesSqlDao::getCountSale).reversed()).collect(Collectors.toList());
    }
    @Override
    public void getPDF(int value, String name){
        Document document = new Document();
        try{
            BaseFont bf=BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font=new Font(bf,14,Font.NORMAL);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\asus\\Desktop\\Отчеты\\lab7\\AddTableExample.pdf"));
            document.open();
            PdfPTable table = new PdfPTable(5); // 5 columns.
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

            float[] columnWidths = {1f, 1f, 1f, 1f, 1f};
            table.setWidths(columnWidths);
            PdfPCell cell0 = new PdfPCell(new Paragraph("№ "));
            PdfPCell cell1 = new PdfPCell(new Paragraph("Name"));

            cell0.setPaddingLeft(10);
            cell0.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell0.setVerticalAlignment(Element.ALIGN_MIDDLE);

            cell1.setPaddingLeft(10);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell2 = new PdfPCell(new Paragraph("Price"));

            cell2.setPaddingLeft(10);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell3 = new PdfPCell(new Paragraph("CountSale"));

            cell3.setPaddingLeft(10);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell4 = new PdfPCell(new Paragraph("TotalSumm"));
            cell4.setPaddingLeft(10);
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

            table.addCell(cell0);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            List<DevicesSqlDao> dataset = devicesRepo.findAll();
            switch (value){
                case(1):
                    dataset = dataset.stream().sorted(Comparator.comparingInt(DevicesSqlDao::getCountSale).reversed()).collect(Collectors.toList());
                    break;
                case(2):
                    dataset = dataset.stream().sorted(Comparator.comparingInt(DevicesSqlDao::getTotalSumm).reversed()).collect(Collectors.toList());
                    break;
                case(3):
                    dataset = devicesRepo.findByNameContainingIgnoreCaseOrderByName(name);
                    break;
                case(4):
                    dataset = devicesRepo.findByNameContainingIgnoreCaseOrderByName(name);
                    dataset = dataset.stream().sorted(Comparator.comparingInt(DevicesSqlDao::getCountSale).reversed()).collect(Collectors.toList());
                    break;
                case(5):
                    dataset = devicesRepo.findByNameContainingIgnoreCaseOrderByName(name);
                    dataset = dataset.stream().sorted(Comparator.comparingInt(DevicesSqlDao::getTotalSumm).reversed()).collect(Collectors.toList());
                    break;
                default:
                    break;
            }
            //Set Column widths
            int i = 1;
            for (DevicesSqlDao record : dataset) {
                table.addCell(String.valueOf(i));
                table.addCell(new Paragraph(record.getName(),font));
                table.addCell(String.valueOf(record.getPrice()));
                table.addCell(String.valueOf(record.getCountSale()));
                table.addCell(String.valueOf(record.getTotalSumm()));
                i++;
            }
            document.add(table);

            document.close();
            writer.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
