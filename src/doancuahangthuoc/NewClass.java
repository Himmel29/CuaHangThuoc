/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doancuahangthuoc;

import MyCustom.MyFileChooser;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.MessageFormat;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttribute;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Element;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class NewClass {
    public static JTable tb;
    public static void main(String[] args) {
        
        JFrame frame=new JFrame();
        frame.setBounds(100, 100, 200, 200);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane scroll=new JScrollPane();
        scroll.setBounds(10, 10, 180, 150);
        JTable tb=new JTable();
        tb.setBounds(0, 0, 180, 150);
        String str1[]={"ID","Tên","Tuổi","Giới Tính"};
        String str2[]={"1","NGUYEN VAN A","19","Nam"};
        DefaultTableModel model=new DefaultTableModel(str1, 0);
        model.addRow(str2); tb.setModel(model);
        scroll.add(tb);
        JButton bt_print=new JButton("Print");
        bt_print.setBounds(90, 170, 50, 20);
        bt_print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printPDF();
            }
        } );
        frame.add(scroll);
        frame.add(bt_print);
        frame.setVisible(true);
        frame.pack();
       
        
    }
    public static void print(JTable tb){
        MessageFormat header= new MessageFormat("In bảng");
        MessageFormat footer=new MessageFormat("");
        try {
            PrintRequestAttributeSet set= new HashPrintRequestAttributeSet();
            set.add(OrientationRequested.LANDSCAPE);
            tb.print(JTable.PrintMode.NORMAL, header, footer, true, set, true);
            JOptionPane.showMessageDialog(null, "In thành công");
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "In không thành công");
        }
    }
    public static void printPDF(){
        String path="";
        try {
            JFileChooser chooser = new MyFileChooser("export/");
            chooser.setDialogTitle("Lưu vào");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
            chooser.setFileFilter(fnef);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                // Láº¥y Ä‘Æ°á»�ng dáº«n vá»«a chá»�n + tÃªn tá»‡p
                path = chooser.getSelectedFile().getPath();
                if (!path.contains(".pdf")) {
                    path += ".pdf";
                }
            }
            Document pdfDoc=new Document();
            PdfWriter.getInstance(pdfDoc, new FileOutputStream(path));
            pdfDoc.open();

            pdfDoc.add(new Paragraph("1"));
            pdfDoc.add(new Paragraph("2"));

            PdfPTable table=new PdfPTable(2);
            PdfPCell cell= new PdfPCell(new Paragraph("3"));
            cell.setColspan(8);
            cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.BLUE);

            table.addCell(cell);
            table.addCell("ID");
            table.addCell("Name");

            table.addCell("01");
            table.addCell("Skeeyz");

            pdfDoc.add(table);
            pdfDoc.close();
            JOptionPane.showMessageDialog(null, "In thành công.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "In không thành công.");
        }
    }
    public static void exportExcel(){
        String path="";
        JFileChooser chooser = new MyFileChooser("export/");
            chooser.setDialogTitle("Lưu vào");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
            chooser.setFileFilter(fnef);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                // Láº¥y Ä‘Æ°á»�ng dáº«n vá»«a chá»�n + tÃªn tá»‡p
                path = chooser.getSelectedFile().getPath();
                if (!path.contains(".xlsx")) {
                    path += ".xlsx";
                }
            }
        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheet=workbook.createSheet("Sheet1");
        XSSFRow row=null;
        Cell cell=null;
        row=sheet.createRow(3);
        cell=row.createCell(0, CellType.STRING);
        cell.setCellValue("Lần thứ");
        for(int i=1;i<10;i++){
            cell=row.createCell(i, CellType.STRING);
            cell.setCellValue(i);
        }
        
        File f_out=new File(path);
        try {
            FileOutputStream fis= new FileOutputStream(f_out);
            workbook.write(fis);
            fis.close();
        } catch (Exception e) {
        }
    }
}
