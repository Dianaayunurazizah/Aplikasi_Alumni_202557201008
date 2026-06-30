/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasialumni202557201008;
import java.sql.Connection; //untuk menghubungkan antar java dengan mysql
import java.sql.DriverManager; //untuk mencari driver yang sesuai dan membuka koneksi java ke database, isinya adalah method public static connection konek()
import java.sql.SQLException; //untuk menangkap error yang terjadi saat java berinteraksi dengan mysql

/**
 *
 * @author Acer
 */


public class koneksi {
//mendeklarasikan variabel koneksi sebagai static agar bisa diakses dari mana saja di class  
private static Connection mysqlconfiq;
//method static untuk membuka koneksi di database MySQL
public static Connection konek(){
    
    try {
        //JDBC (Java Database Connectivity) library bawaan java untuk menghubungkan java ke database
        //URL / alamat unik / link koneksi ke database biasanya menggunakan jdbc:mysql://[host]:[port]/[nama_database]
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
