/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasialumni202557201008;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Acer
 */


public class koneksi {
   
private static Connection mysqlconfiq;

public static Connection konek(){
    
    try {
        String url = "jdbc:mysql://localhost:3306/alumni_202557201008";
        //username database
        String user = "root";
        //password database
        String pass = "";
        //memebuat koneksi ke database dan menyimpannya di variabel mysqlconfig
        mysqlconfiq = DriverManager.getConnection(url, user, pass);
    } catch (SQLException sQLException) {
        //Menampilkan pesan error jika koneksi gagal
        System.err.println(sQLException.getMessage());
    }
    
    //Mengembalikan objek koneksi (bisa null jika gagal)
    return mysqlconfiq;
}
}
