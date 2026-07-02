 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package aplikasialumni202557201008;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Acer
 */
public class panelKelas extends javax.swing.JPanel {

    /**
     * Creates new form panelDashboard
     */
    public panelKelas() { //tempat konstruktor
        initComponents();
        reset(); //mengosongkan semua input form
        load_tabel_kelas(); //menampilkan data kelas ke dalam tblKelas
        comboJurusan();//mengisi combo box jurusan dari database
        comboWali();//mengisi combo box wali kelas dari database
    }
    void reset(){//method untuk mengosongkan semua form yang diinputkan
        tKodeKelas.setText(null);//kosongkan field kode kelas
        tKodeKelas.setEditable(true); //aktifkan kembali input kode kelas agar bisa diubah
        tNamaKelas.setText(null);//kosongkan field nama kelas
        cTingkatan.setSelectedItem(null);//kosongkan pilihan pada combo box tingkatan 
        cJurusan.setSelectedItem(null);//kosongkan pilihan pada combo box Jurusan
        cWali.setSelectedItem(null);//kosongkan pilihan pada combo box Wali Kelas
    }
    void load_tabel_kelas(){//method untuk menampilkan semua data kelas ke dalam tabel
        //buat mdl tabel baru untuk menyimpan data yanng akan ditampilkan
        DefaultTableModel mdl = new DefaultTableModel();
        //tambahkan nama - nama kkolom ke mdl tabel
        mdl.addColumn("Kode Kelas");
        mdl.addColumn("Nama Kelas");
        mdl.addColumn("Tingkatan");
        mdl.addColumn("Jurusan");
        mdl.addColumn("Wali Kelas");
        //Buat Query SQL untuk mengambil data kelas beserta jurusan dan wali kelas
        String sql = "SELECT k.id_kelas,k.nama_kelas,k.tingkatan,j.nama_jur,g.nama_guru "
                +"FROM kelas k "
                +"LEFT JOIN jurusan j ON k.kode_jur=j.kode_jur "
                +"LEFT JOIN guru g ON k.nip_wali_kelas=g.nip";
        
        try {
            ////membuka koneksi ke database menggunakan method konek()
            Connection conn = koneksi.konek();
            //siapkan perintah SQL 
            Statement st = conn.createStatement();
            //jalankan Query dan ambil hasilnya 
            ResultSet rs = st.executeQuery(sql);
         
            while (rs.next()) { //baca setiap baris data dari hasil Query
                //AMBIL data dari setiap kolom
                String kodeKelas = rs.getString("id_kelas");
                String namaKelas = rs.getString("nama_kelas");
                String tingkatan = rs.getString("tingkatan");
                String jurusan = rs.getString("nama_jur");
                String waliKelas = rs.getString("nama_guru");
                //masukkan data ke dalam satu baris array
                Object[] baris = {kodeKelas, namaKelas, tingkatan, jurusan, waliKelas};
                //tambahkan baris data ke mdl tabel
                mdl.addRow(baris);
            }
        } catch (SQLException sQLException) {
            //tampilkan pesan  jika terjadi kesalahan saat mengambil data
            JOptionPane.showMessageDialog(null,"Gagal mengambil data!");
        }
        tblKelas.setModel(mdl);//tampilkan data yang sudah dimasukkan kem dalam tabel di GUI
    }
    void comboJurusan(){//method untuk mengisi combo box jurusan dari database
        try {
        String sql = "SELECT * FROM jurusan";//Perintah / Query untuk mengambil semua data dari tabel jurusan

            Connection conn = koneksi.konek();////membuka koneksi ke database menggunakan method konek()

            Statement statement = conn.createStatement();//siapkan perintah SQL

            ResultSet resultSet = statement.executeQuery(sql);//jalankan Query dan ambil hasilnya 

            while (resultSet.next()) { //tambahkan setiap nama jurusan ke dalam combo box
                cJurusan.addItem(resultSet.getString("nama_jur"));
            }
        } catch (SQLException sQLException) {
            //kosongkan (tidak ada penangganan kesalahan)
        }
        cJurusan.setSelectedItem(null);//kosongkan pilihan default combo box
    }
    void comboWali(){//method untuk mengisi combo box wali kelas dari database
        try{
        String sql = "SELECT * FROM guru";//Perintah / Query untuk mengambil semua data dari tabel guru

            Connection conn = koneksi.konek();////membuka koneksi ke database menggunakan method konek()

            Statement statement = conn.createStatement();//siapkan perintah SQL

            ResultSet resultSet = statement.executeQuery(sql);//jalankan Query dan ambil hasilnya 

            while (resultSet.next()) { //tambahkan setiap nama guru ke dalam combo box
                cWali.addItem(resultSet.getString("nama_guru"));
            }
        } catch (SQLException sQLException) {
         //kosongkan (tidak ada penangganan kesalahan   
        }
        cWali.setSelectedItem(null);
    }
    String KodeJurusan(String NamaJurusan) { ///method untuk mengambil kode jurusan berdasarkan nama jurusan
        try{
        String sql = "SELECT * FROM jurusan WHERE nama_jur=?";//Query dengan parameter untuk mencari jurusan berdasarkan nama

            Connection conn = koneksi.konek();//membuka koneksi ke database menggunakan method konek()

            PreparedStatement ps = conn.prepareStatement(sql);//siapkan prepared statement
            
            ps.setString(1,NamaJurusan);//isi parameter query dengan nama jurusan
            
            ResultSet resultSet = ps.executeQuery();//jalankan query dan ambil hasilnya

            while (resultSet.next()) { //jika data ditemukan , kembalikan kode jurusann 
                return resultSet.getString("kode_jur");
            }
        } catch (Exception e) {
            ///jika error , kembalikan string kosong
            return "";
            
        }
        return""; //jika tidak ditemukan maka kembalikan string kosong
    }
    String NIP(String NamaGuru){ //method untuk mengambil nip berdasarkan nama guru
       try{
        String sql = "SELECT * FROM guru WHERE nama_guru=?";///Query ddengan parameter untuk mencari guru berdasarkan nama guru

            Connection conn = koneksi.konek();//membuka koneksi ke database menggunakan method konek()

            PreparedStatement ps = conn.prepareStatement(sql);//siapkan prepared statement
            
            ps.setString(1,NamaGuru);//isi parameter query dengan nama guru
            
            ResultSet resultSet = ps.executeQuery();//jalankan query dan ambil hasilnya

            while (resultSet.next()) {//jika data ditemukan , kembalikan nip guru
                return resultSet.getString("nip");
            }
        } catch (Exception e) {
            ///jika error , kembalikan string kosong
            return "";
            
        }
        return"";//jika tidak ditemukan maka kembalikan string kosong
    } 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pHeaderKelas = new javax.swing.JPanel();
        lblDataKelas = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        pContentKelas = new javax.swing.JPanel();
        pInputan = new javax.swing.JPanel();
        lblKodekelas = new javax.swing.JLabel();
        tKodeKelas = new javax.swing.JTextField();
        lblNamakelas = new javax.swing.JLabel();
        tNamaKelas = new javax.swing.JTextField();
        lblTingkatan = new javax.swing.JLabel();
        cTingkatan = new javax.swing.JComboBox<>();
        cJurusan = new javax.swing.JComboBox<>();
        cWali = new javax.swing.JComboBox<>();
        lblJurusan = new javax.swing.JLabel();
        lblWalikelas = new javax.swing.JLabel();
        pTampilan = new javax.swing.JPanel();
        pTombolKelas = new javax.swing.JPanel();
        btnTambah = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        pTabelKelas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKelas = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 20));
        setMinimumSize(new java.awt.Dimension(782, 622));
        setLayout(new java.awt.BorderLayout());

        pHeaderKelas.setBackground(new java.awt.Color(51, 153, 255));
        pHeaderKelas.setMinimumSize(new java.awt.Dimension(100, 40));
        pHeaderKelas.setPreferredSize(new java.awt.Dimension(100, 40));
        pHeaderKelas.setLayout(new java.awt.BorderLayout());

        lblDataKelas.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 16)); // NOI18N
        lblDataKelas.setForeground(new java.awt.Color(255, 255, 255));
        lblDataKelas.setText("Data Kelas");
        lblDataKelas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));
        pHeaderKelas.add(lblDataKelas, java.awt.BorderLayout.LINE_START);

        lblClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasialumni202557201008/img/close 22.png"))); // NOI18N
        lblClose.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        pHeaderKelas.add(lblClose, java.awt.BorderLayout.LINE_END);

        add(pHeaderKelas, java.awt.BorderLayout.PAGE_START);

        pContentKelas.setLayout(new java.awt.BorderLayout());

        pInputan.setMinimumSize(new java.awt.Dimension(250, 371));
        pInputan.setPreferredSize(new java.awt.Dimension(250, 371));

        lblKodekelas.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        lblKodekelas.setText("Kode Kelas");

        lblNamakelas.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        lblNamakelas.setText("Nama Kelas");

        lblTingkatan.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        lblTingkatan.setText("Tingkatan");

        cTingkatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "10", "11", "12" }));

        lblJurusan.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        lblJurusan.setText("Jurusan");

        lblWalikelas.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        lblWalikelas.setText("Wali Kelas");

        javax.swing.GroupLayout pInputanLayout = new javax.swing.GroupLayout(pInputan);
        pInputan.setLayout(pInputanLayout);
        pInputanLayout.setHorizontalGroup(
            pInputanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInputanLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pInputanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWalikelas)
                    .addComponent(lblJurusan)
                    .addGroup(pInputanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblTingkatan)
                        .addComponent(lblNamakelas)
                        .addComponent(lblKodekelas)
                        .addComponent(tKodeKelas)
                        .addComponent(tNamaKelas, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                        .addComponent(cTingkatan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cJurusan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cWali, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        pInputanLayout.setVerticalGroup(
            pInputanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInputanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblKodekelas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tKodeKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNamakelas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tNamaKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTingkatan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cTingkatan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblJurusan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblWalikelas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cWali, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(222, Short.MAX_VALUE))
        );

        pContentKelas.add(pInputan, java.awt.BorderLayout.LINE_START);

        pTampilan.setLayout(new java.awt.BorderLayout());

        btnTambah.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        btnTambah.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasialumni202557201008/img/plus white.png"))); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTambah.setMinimumSize(new java.awt.Dimension(72, 23));
        btnTambah.setPreferredSize(new java.awt.Dimension(72, 23));
        btnTambah.addActionListener(this::btnTambahActionPerformed);

        btnUbah.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Yellow"));
        btnUbah.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        btnUbah.setForeground(new java.awt.Color(255, 255, 255));
        btnUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasialumni202557201008/img/edit white.png"))); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnUbah.setMinimumSize(new java.awt.Dimension(72, 23));
        btnUbah.setPreferredSize(new java.awt.Dimension(72, 23));
        btnUbah.addActionListener(this::btnUbahActionPerformed);

        btnHapus.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        btnHapus.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasialumni202557201008/img/hapus white.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHapus.setMinimumSize(new java.awt.Dimension(72, 23));
        btnHapus.setPreferredSize(new java.awt.Dimension(72, 23));
        btnHapus.addActionListener(this::btnHapusActionPerformed);

        btnReset.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        btnReset.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasialumni202557201008/img/reset white.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReset.setMinimumSize(new java.awt.Dimension(72, 23));
        btnReset.setPreferredSize(new java.awt.Dimension(72, 23));
        btnReset.addActionListener(this::btnResetActionPerformed);

        javax.swing.GroupLayout pTombolKelasLayout = new javax.swing.GroupLayout(pTombolKelas);
        pTombolKelas.setLayout(pTombolKelasLayout);
        pTombolKelasLayout.setHorizontalGroup(
            pTombolKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTombolKelasLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        pTombolKelasLayout.setVerticalGroup(
            pTombolKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTombolKelasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pTombolKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pTampilan.add(pTombolKelas, java.awt.BorderLayout.PAGE_END);

        pTabelKelas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242), 20));
        pTabelKelas.setLayout(new java.awt.CardLayout());

        tblKelas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKelasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKelas);

        pTabelKelas.add(jScrollPane1, "card2");

        pTampilan.add(pTabelKelas, java.awt.BorderLayout.CENTER);

        pContentKelas.add(pTampilan, java.awt.BorderLayout.CENTER);

        add(pContentKelas, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        //ambil input dari field kode kelas
        String KodeKelas =tKodeKelas.getText();
        //ambil input dari field Nama kelas
        String NamaKelas =tNamaKelas.getText();
        //ambil nilai yang dipilih dari combo box tingkatan
        String Tingkatan =cTingkatan.getSelectedItem().toString();
        //ambil nama jurusan yang dipilih dari combo box lalu ubah ke kodejurusan emnggunakan method kode jurusan()
        String Jurusan =KodeJurusan(cJurusan.getSelectedItem().toString());
         //ambil nama wali kelas dari combo box lalu ubah ke NIP menggunakan method NIP
        String WaliKelas =NIP(cWali.getSelectedItem().toString());
          
          try { 
              //Query SQL untuk menyimpan data ke tabel kelas
            String sql = "INSERT INTO kelas(id_kelas, nama_kelas,tingkatan,kode_jur,nip_wali_kelas)VALUES(?,?,?,?,?)";
            
            Connection conn = koneksi.konek();//membuka koneksi ke database
            
            PreparedStatement statement = conn.prepareStatement(sql);//siapkan statement SQL dengan parameter 
            //Masukkan data ke parameter query (urutan sesuai dengan tanda tanya di query)
            statement.setString(1, KodeKelas);
            statement.setString(2, NamaKelas);
            statement.setString(3, Tingkatan);
            statement.setString(4, Jurusan);
            statement.setString(5, WaliKelas);
            
            statement.execute();//jalankan query untuk menyimpan data ke database
            
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");//tampilkan pesan bahwa data berhasil disimpan
        } catch (SQLException e) {
            //jika terjadi error saat menyimpan , tampilkan pesan gagal
            JOptionPane.showMessageDialog(null,"Data gagal disimpan!");
        }
          
       load_tabel_kelas();//tampilkan kembali data kelas terbaru di tabel
          
       reset();//kosongkan semua input di form
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        //ambil input dari field kode kelas
        String KodeKelas =tKodeKelas.getText();
        //ambil input dari field Nama Kelas
        String NamaKelas =tNamaKelas.getText();
        //ambil nilai yang dipilih dari combo box tingkatan
        String Tingkatan =cTingkatan.getSelectedItem().toString();
        //ambil nama jurusan yang dipilih dari combo box lalu ubah ke kodejurusan emnggunakan method kode jurusan()
         String Jurusan =KodeJurusan(cJurusan.getSelectedItem().toString());
         //ambil nama wali kelas dari combo box lalu ubah ke NIP menggunakan method NIP
          String WaliKelas =NIP(cWali.getSelectedItem().toString());
          
          try {
            String sql = "UPDATE kelas SET nama_kelas=?, tingkatan=?, kode_jur=?, nip_wali_kelas=?"
                    +"WHERE id_kelas=?";//Query SQL untuk mengubah data kelas berdasarkan id_kelas
            
            Connection conn = koneksi.konek();//membuka koneksi ke database
            
            PreparedStatement statement = conn.prepareStatement(sql);//siapkan statement SQL dengan parameter 
            //Masukkan data ke parameter query (urutan sesuai dengan tanda tanya di query)
            statement.setString(1, NamaKelas);
            statement.setString(2, Tingkatan);
            statement.setString(3, Jurusan);
            statement.setString(4, WaliKelas);
            statement.setString(5, KodeKelas);
            
            statement.execute();//jalankan query untuk melakukan UPDATE data ke database
            
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");//tampilkan pesan bahwa data berhasil diubah
        } catch (SQLException e) {
            //jika terjadi kesalahan saat UPDATE , tampilkan pesan gagal
            JOptionPane.showMessageDialog(null,"Data gagal diubah!");
        }
          
       load_tabel_kelas();//tampilkan kembali data kelas terbaru di tabel
          
       reset();//kosongkan semua input di form
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        String KodeKelas =tKodeKelas.getText();//ambil nilai kode kelas dari inputan field tKodeKelas
        
          try {
            String sql = "DELETE FROM kelas WHERE id_kelas=?";//buat perintah SQL untuk menghapus data dari tabel 'Kelas' berdasarkan id_kelas
            
            Connection conn = koneksi.konek();//membuka koneksi ke database
            
            PreparedStatement statement = conn.prepareStatement(sql);//siapkan pernyataan SQL untuk mendukung parameter
            
            statement.setString(1, KodeKelas);//isi nilai parameter pertama (id_kelas) dengan kode kelas yang di iniput
            
            statement.execute();//jalankan perintah untuk mengambil data dari database
            
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");//tampilkan pesan bahwa data berhasil di hapus
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal dihapus!");//tampilkan pesan jika terjadi kesalahan saat menghapus data
        }
          
       load_tabel_kelas();//tampilkan kembali data kelas terbaru di tabel
          
       reset();//kosongkan semua input di form
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        reset();//mengosongkan semua input form
    }//GEN-LAST:event_btnResetActionPerformed

    private void tblKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKelasMouseClicked
        // TODO add your handling code here:
        //ambil baris yang di klik oleh pengguna  pada tabel kelas 
        int barisYangDipilih = tblKelas.rowAtPoint(evt.getPoint());
        //ambil nilai dari kolom ke 0 (kode kelas) pada baris yang dipilih 
        String KodeKelas = tblKelas.getValueAt(barisYangDipilih,0).toString();
        //ambil nilai dari kolom ke 1 (nama kelas) pada baris yang dipilih
        String NamaKelas = tblKelas.getValueAt(barisYangDipilih,1).toString();
        //ambil nilai dari kolom ke 2 (tingkatan) pada baris yang dipilih
        String Tingkatan = tblKelas.getValueAt(barisYangDipilih,2).toString();
        //ambil nilai dari kolom ke 3 (jurusan) pada baris yang dipilih
        String Jurusan = tblKelas.getValueAt(barisYangDipilih,3).toString();
        
        String WaliKelas;//buat variabel untuk menyimpan nama wali kelas
        
        if(tblKelas.getValueAt(barisYangDipilih, 4) !=null){ //APAKAH KOLOM KE 4 (wali kelas) berisi data atau tidak
            WaliKelas =tblKelas.getValueAt(barisYangDipilih, 4).toString();//jika ada data , ambil dan ubah menjadi string
  
        }else {
            WaliKelas=null;//jika kosong (null)set nilai wali kelas juga null
        }
        tKodeKelas.setText(KodeKelas);//tampilkan kodekelas kedalam input text (tidak bisa di edit)
        tKodeKelas.setEditable(false);//kode kelas tidak bisa di edit 
        
        tNamaKelas.setText(NamaKelas);//tampilkan nama kelas ke text field 
        
        cTingkatan.setSelectedItem(Tingkatan);//tampilkan tingkatan ke combo box tingkatan
        
        cJurusan.setSelectedItem(Jurusan);//tampilkan jurusan ke combo box jurusan
        
        cWali.setSelectedItem(WaliKelas);//tampilkan wali ke combo box wali kelas
    }//GEN-LAST:event_tblKelasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cJurusan;
    private javax.swing.JComboBox<String> cTingkatan;
    private javax.swing.JComboBox<String> cWali;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblDataKelas;
    private javax.swing.JLabel lblJurusan;
    private javax.swing.JLabel lblKodekelas;
    private javax.swing.JLabel lblNamakelas;
    private javax.swing.JLabel lblTingkatan;
    private javax.swing.JLabel lblWalikelas;
    private javax.swing.JPanel pContentKelas;
    private javax.swing.JPanel pHeaderKelas;
    private javax.swing.JPanel pInputan;
    private javax.swing.JPanel pTabelKelas;
    private javax.swing.JPanel pTampilan;
    private javax.swing.JPanel pTombolKelas;
    private javax.swing.JTextField tKodeKelas;
    private javax.swing.JTextField tNamaKelas;
    private javax.swing.JTable tblKelas;
    // End of variables declaration//GEN-END:variables
}
