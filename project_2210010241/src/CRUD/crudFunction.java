/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author USER
 */
public class crudFunction extends Koneksi {
    
    private DefaultTableModel Modelnya;
    private TableColumn Kolomnya;
    
    public crudFunction(){}
    
    public String getFieldTabel(String[] FieldTabelnya){
        String hasilnya = "";
        int deteksiIndexAkhir = FieldTabelnya.length - 1;
        
        try {
            for (int i = 0; i < FieldTabelnya.length; i++) {
                
                if (i == deteksiIndexAkhir) {
                    hasilnya = hasilnya + FieldTabelnya[i];
                } else {
                   hasilnya = hasilnya + FieldTabelnya[i] + ",";   
                }                
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return hasilnya;
    }
    
    public String getIsiTabel(String[] IsiTabelnya){
        String hasilnya = "";
        int DeteksiIndex = IsiTabelnya.length -1;
        try {
            for (int i = 0; i < IsiTabelnya.length; i++){
                if (i == DeteksiIndex){
                    hasilnya = hasilnya + "'" + IsiTabelnya[i] +"'";
                } else {
                    hasilnya = hasilnya + "'" + IsiTabelnya[i] +"',";
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return  "("+hasilnya+")";
    }
    
    public void SimpanDinamis(String NamaTabel, String[] Fieldnya, String[] Isinya){
        try {
            String SQLSave = "INSERT INTO " +NamaTabel+ " (" +getFieldTabel(Fieldnya)+ ") VALUES " +getIsiTabel(Isinya);
            Statement perintah = getKoneksiDB().createStatement();
            perintah.executeUpdate(SQLSave);
            perintah.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
            // Menangani exception duplikasi primary key atau unique key
            JOptionPane.showMessageDialog(null, "Data dengan primary key atau unique key yang sama sudah ada. Tidak dapat menyimpan data duplikat.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public String getFieldValueEdit(String[] Field, String[] value){
        String hasil = "";
        int deteksi = Field.length-1;
        try {
            for (int i = 0; i < Field.length; i++) {
                if (i==deteksi){
                    hasil = hasil +Field[i]+" ='"+value[i]+"'";
                }else{
                   hasil = hasil +Field[i]+" ='"+value[i]+"',";  
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return hasil;
    }
    
    public void UbahDinamis(String NamaTabel, String PrimaryKey, String IsiPrimary, String[] Field, String[] Value) {
        try {
            // Query untuk mengecek apakah data dengan primary key ini ada
            String SQLCheck = "SELECT COUNT(*) FROM " + NamaTabel + " WHERE " + PrimaryKey + "='" + IsiPrimary + "'";
            Statement perintah = getKoneksiDB().createStatement();
            ResultSet rs = perintah.executeQuery(SQLCheck);

            if (rs.next() && rs.getInt(1) > 0) {
                // Jika data ditemukan, lakukan update
                String SQLUbah = "UPDATE " + NamaTabel + " SET " + getFieldValueEdit(Field, Value) + " WHERE " + PrimaryKey + "='" + IsiPrimary + "'";
                perintah.executeUpdate(SQLUbah);
                perintah.close();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            } else {
                // Jika data tidak ditemukan, tampilkan pesan
                JOptionPane.showMessageDialog(null, "Data dengan " + PrimaryKey + " = " + IsiPrimary + " tidak ditemukan. Tidak ada data yang diubah.");
            }

            rs.close();
            perintah.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    
    public void HapusDinamis(String NamaTabel, String PK, String isi) {
        try {
            // Query untuk mengecek apakah data dengan primary key ini ada
            String SQLCheck = "SELECT COUNT(*) FROM " + NamaTabel + " WHERE " + PK + "='" + isi + "'";
            Statement perintah = getKoneksiDB().createStatement();
            ResultSet rs = perintah.executeQuery(SQLCheck);

            if (rs.next() && rs.getInt(1) > 0) {
                // Jika data ditemukan, lakukan penghapusan
                String SQLDelete = "DELETE FROM " + NamaTabel + " WHERE " + PK + "='" + isi + "'";
                perintah.executeUpdate(SQLDelete);
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            } else {
                // Jika data tidak ditemukan, tampilkan pesan
                JOptionPane.showMessageDialog(null, "Data dengan " + PK + " = " + isi + " tidak ditemukan. Tidak ada data yang dihapus.");
            }

            rs.close();
            perintah.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void settingJudulTabel(JTable Tabelnya, String[] JudulKolom){
        try {
            Modelnya = new DefaultTableModel();
            Tabelnya.setModel(Modelnya);
            for (int i = 0; i < JudulKolom.length; i++) {
                Modelnya.addColumn(JudulKolom[i]);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void settingLebarKolom(JTable Tabelnya,int[] Kolom){
        try {
            Tabelnya.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            for (int i = 0; i < Kolom.length; i++) {
                Kolomnya =Tabelnya.getColumnModel().getColumn(i);
                Kolomnya.setPreferredWidth(Kolom[i]);   
            }

            } catch (Exception e) {
              System.out.println(e.toString());
        }
    }
    
    public Object[][] isiTabel(String SQL, int jumlah){
        Object[][] data =null;
            try {
                Statement perintah = getKoneksiDB().createStatement();
                ResultSet dataset = perintah.executeQuery(SQL);
                dataset.last();
                int baris = dataset.getRow();
                dataset.beforeFirst();
                int j =0;

                data = new Object[baris][jumlah];

                while (dataset.next()){
                       for (int i = 0; i < jumlah; i++) {
                           data[j][i]=dataset.getString(i+1);
                       }
                       j++;
                }
            } catch (Exception e) {
            }
      
        return data;
    }
    
    public void tampilTabel(JTable Tabelnya, String SQL, String[] Judul){
        try {
            Tabelnya.setModel(new DefaultTableModel(isiTabel(SQL,Judul.length), Judul));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void cetakLaporan(String namaFile) {
        try (Connection conn = getKoneksiDB()) {
            // Pastikan file .jasper berada di folder yang sesuai
            String file = System.getProperty("user.dir") + "/src/laporan/" + namaFile;

            // Mengisi laporan dengan koneksi database
            JasperPrint print = JasperFillManager.fillReport(file, null, conn);

            // Menampilkan laporan dengan JasperViewer
            JasperViewer.viewReport(print, false);
        } catch (SQLException e) {
            System.err.println("Gagal menghubungkan ke database: " + e.getMessage());
            e.printStackTrace();
        } catch (JRException e) {
            System.err.println("Gagal mencetak laporan: " + e.getMessage());
            e.printStackTrace();
        }
    }   
    
}
