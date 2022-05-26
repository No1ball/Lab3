package com.example.springtest.controllers;

import com.example.springtest.entity.DevicesSqlDao;
import com.example.springtest.repos.ClientsRepo;
import com.example.springtest.repos.ContractsRepo;
import com.example.springtest.repos.DevicesRepo;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import com.itextpdf.html2pdf.HtmlConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class deviceController {
    @Autowired
    private DevicesRepo devicesRepo;
    public static final String FONT = "./src/main/resources/arialmt.ttf";
    @GetMapping("/newequpments")
    public ResponseEntity test(){
        return ResponseEntity.ok(devicesRepo.findAll());
    }

    @GetMapping("/equpments/topdf")
    public String getPDF()  {
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
        return "equpments";
    }

    @GetMapping("/equpments1")
    public ResponseEntity search(@RequestParam("name") String name){
        System.out.println(name);
        if(name.equals("undefined")){
            System.out.println("Oao");
        }else if(name.equals("")){
            System.out.println("Ew");
        }
        return  ResponseEntity.ok(devicesRepo.findAll());
    }

    @PostMapping("/add_device")
    public ResponseEntity addDevice(@RequestBody DevicesSqlDao devices){
        devices.setTotalSumm();
        return ResponseEntity.ok(devicesRepo.save(devices));
    }

    @DeleteMapping("/equpments/del/{id}")
    public String delDev(@PathVariable("id") int id){
        devicesRepo.deleteById(id);
        return "equpments";
    }

    @PutMapping("/equpments/ed/{id}")
    public ResponseEntity putDec(@PathVariable("id") int id, @RequestBody DevicesSqlDao devices){
        DevicesSqlDao device = devicesRepo.findById(id).orElseThrow();
        device.setId(devices.getId());
        device.setName(devices.getName());
        device.setPrice(devices.getPrice());
        device.setCountSale(devices.getCountSale());
        device.setTotalSumm();
        return ResponseEntity.ok(devicesRepo.save(device));
    }
}
