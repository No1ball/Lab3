package com.example.springtest.service.deviceService.impl;

import com.example.springtest.entity.DevicesSqlDao;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService<DevicesSqlDao>{
    @Autowired
    private DevicesRepo devicesRepo;
    public static final String FONT = "./src/main/resources/arialmt.ttf";
    @Override
    public void getPDF(){
        Document document = new Document();
        try{
            BaseFont bf=BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font=new Font(bf,14,Font.NORMAL);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\asus\\Desktop\\Отчеты\\lab7\\AddTableExample.pdf"));
            document.open();
            PdfPTable table = new PdfPTable(4); // 3 columns.
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

            float[] columnWidths = {1f, 1f, 1f, 1f};
            table.setWidths(columnWidths);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Name"));

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


            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);


            Iterable<DevicesSqlDao> dataset = devicesRepo.findAll();

            //Set Column widths
            for (DevicesSqlDao record : dataset) {
                table.addCell(new Paragraph(record.getName(),font));
                table.addCell(String.valueOf(record.getPrice()));
                table.addCell(String.valueOf(record.getCountSale()));
                table.addCell(String.valueOf(record.getTotalSumm()));
            }
            document.add(table);

            document.close();
            writer.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public List<DevicesSqlDao> search(String name){
        return devicesRepo.findByNameIgnoreCaseOrderByName(name);
    }
    @Override
    public DevicesSqlDao addDevice(DevicesSqlDao devices){
        devices.setTotalSumm();
        return devicesRepo.save(devices);
    }
    @Override
    public void delDev(int id){
        devicesRepo.deleteById(id);
    }
    @Override
    public DevicesSqlDao putDec(int id, DevicesSqlDao devices){
        DevicesSqlDao device = devicesRepo.findById(id).orElseThrow();
        device.setId(devices.getId());
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
}
