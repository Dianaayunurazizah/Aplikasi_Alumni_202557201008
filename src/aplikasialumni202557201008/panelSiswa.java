/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package aplikasialumni202557201008;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class panelSiswa extends javax.swing.JPanel {

    /**
     * Creates new form panelSiswa4
     */
    public panelSiswa() {
        initComponents();
        reset(); //memanggil method reset untuk mengosongkan semua input pada form 
        load_tabel_siswa(); //memanggil method untuk menampilkan data siswa ke dalam tabel
        comboKelas(); //memanggil method untuk mengisi combo box kelas dari database
    }
    void reset(){ //method untuk mengosongkan semua input pada form siswa
        tNIS.setText(null); //mengosongkan field nis 
        tNamaSiswa.setText(null);//mengosongkan field NamaSiswa
        cJenisKelamin.setSelectedItem(null);//mengosongkan pilihan pada combo boox jenis kelamin
        tTempatLahir.setText(null);//mengosongkan field TempatLahir
        tTanggal.setCalendar(null);//mengosongkan pilihan pada komponen kalender tanggal lahir
        tHP.setText(null);//mengosongkan field no hp
        cKelas.setSelectedItem(null);//mengosongkan pilihan pada combo box kelas
        tAlamat.setText(null);//mengosongkan field alamat
        tFotoPath.setText(null);//mengosongkan path file foto yang disimpan
        tFoto.setIcon(null);//mengahapuss icon pada label foto
        tFoto.setText("Foto");//mengatur teks label foto menjadi "Foto"
    }
    void comboKelas(){ //method untuk mengisi combo box kelas dari tabel 'Kelas' di database
        try {
            String sql = "SELECT* FROM kelas"; //Query SQL untuk mengambil data kelas

            Connection conn = koneksi.konek(); //membuka koneksi ke database

            Statement statement = conn.createStatement(); //membuat statement untuk menjalankan query

            ResultSet resultSet = statement.executeQuery(sql);///mennjalankan query dan menyimpan hasilnya

            while (resultSet.next()) { //mengambil data satu persatu dan menambahkan nya ke combo box
                cKelas.addItem(resultSet.getString("id_kelas"));
            }
        } catch (SQLException e) {
            //jika terjadi kesalahan , tampilkan pesan error
        }
        
        cKelas.setSelectedItem(null); //MENGOSONGKAN PILIHAN COMBO BOX setelah di isi
    }
    void load_tabel_siswa(){ //method untuk menampilkan data siswa ke dalam tabel 
        
        DefaultTableModel mdl = new DefaultTableModel();//membuat mdl tabel baru
        //menambahkan kolom kedalam mdl tabel
        mdl.addColumn("NIS");
        mdl.addColumn("Nama Siswa");
        mdl.addColumn("L/P");
        mdl.addColumn("Tempat Lahir");
        mdl.addColumn("Tgl Lahir");
        mdl.addColumn("Kelas");
        mdl.addColumn("HP");
        
        String sql= "SELECT * FROM siswa"; //Perintah / Query SQL untuk mengambil semua data siswa
        
        try {
            Connection conn = koneksi.konek();//membuka koneksi ke database

            Statement st = conn.createStatement();//membuat statement untuk menjalankan query

            ResultSet rs = st.executeQuery(sql);//menjalankan query dan mengambil hasilnya

            while (rs.next()) { //melakukan iterasi untuk setiap baris hasil query
                //mengambil data dari setiap kolom
                String nis = rs.getString("nis");
                String namaSiswa = rs.getString("nama_siswa");
                String jenisKelamin = rs.getString("gender");
                String tempatLahir = rs.getString("tempat_lahir");
                String tglLahir = rs.getString("tgl_lahir");
                String kelas = rs.getString("id_kelas");
                String hp = rs.getString("no_hp");
                //menyimpan data dalam array 
                Object[] baris = {nis, namaSiswa, jenisKelamin, tempatLahir, tglLahir, kelas, hp};
                //menambahkan data sebagai baris baru di mdl tabel
                mdl.addRow(baris);
            }
        } catch (SQLException sQLException) {
            //menmpilkan pesan error jika terjadi kegagalan saat mengambil data
            JOptionPane.showMessageDialog(null,"Gagal Mengambil Data");
        }
        tblSiswa.setModel(mdl);//menampilkan model yang sudah di isi ke dalam tabel GUI
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pHeaderSiswa = new javax.swing.JPanel();
        lblDataSiswa = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        pContentSiswa = new javax.swing.JPanel();
        pTampilan = new javax.swing.JPanel();
        pTombol = new javax.swing.JPanel();
        btnTambah = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        pInputan = new javax.swing.JPanel();
        tFoto = new javax.swing.JLabel();
        lblNis = new javax.swing.JLabel();
        tNIS = new javax.swing.JTextField();
        lblNama = new javax.swing.JLabel();
        tNamaSiswa = new javax.swing.JTextField();
        lblJenisKelamin = new javax.swing.JLabel();
        cJenisKelamin = new javax.swing.JComboBox<>();
        lblTempatLahir = new javax.swing.JLabel();
        tTempatLahir = new javax.swing.JTextField();
        lblTanggalLahir = new javax.swing.JLabel();
        lblHp = new javax.swing.JLabel();
        tHP = new javax.swing.JTextField();
        lblKelas = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tAlamat = new javax.swing.JTextArea();
        tTanggal = new com.toedter.calendar.JDateChooser();
        cKelas = new javax.swing.JComboBox<>();
        pTable = new javax.swing.JPanel();
        pPath = new javax.swing.JPanel();
        tFotoPath = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSiswa = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 20));
        setMinimumSize(new java.awt.Dimension(1000, 861));
        setLayout(new java.awt.BorderLayout());

        pHeaderSiswa.setBackground(new java.awt.Color(51, 153, 255));
        pHeaderSiswa.setMinimumSize(new java.awt.Dimension(960, 40));
        pHeaderSiswa.setPreferredSize(new java.awt.Dimension(960, 40));
        pHeaderSiswa.setLayout(new java.awt.BorderLayout());

        lblDataSiswa.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 16)); // NOI18N
        lblDataSiswa.setForeground(new java.awt.Color(255, 255, 255));
        lblDataSiswa.setText("Data Siswa");
        lblDataSiswa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));
        pHeaderSiswa.add(lblDataSiswa, java.awt.BorderLayout.LINE_START);

        lblClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasialumni202557201008/img/close 22.png"))); // NOI18N
        lblClose.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        pHeaderSiswa.add(lblClose, java.awt.BorderLayout.LINE_END);

        add(pHeaderSiswa, java.awt.BorderLayout.PAGE_START);

        pContentSiswa.setLayout(new java.awt.BorderLayout());

        pTampilan.setMinimumSize(new java.awt.Dimension(1235, 388));
        pTampilan.setPreferredSize(new java.awt.Dimension(1235, 388));
        pTampilan.setLayout(new java.awt.BorderLayout());

        pTombol.setMinimumSize(new java.awt.Dimension(960, 50));
        pTombol.setPreferredSize(new java.awt.Dimension(960, 50));

        btnTambah.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        btnTambah.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 14)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasialumni202557201008/img/plus white.png"))); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.addActionListener(this::btnTambahActionPerformed);

        btnUbah.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Yellow"));
        btnUbah.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 14)); // NOI18N
        btnUbah.setForeground(new java.awt.Color(255, 255, 255));
        btnUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasialumni202557201008/img/edit white.png"))); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.addActionListener(this::btnUbahActionPerformed);

        btnHapus.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        btnHapus.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 14)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasialumni202557201008/img/hapus white.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(this::btnHapusActionPerformed);

        btnReset.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        btnReset.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 14)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasialumni202557201008/img/reset white.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(this::btnResetActionPerformed);

        javax.swing.GroupLayout pTombolLayout = new javax.swing.GroupLayout(pTombol);
        pTombol.setLayout(pTombolLayout);
        pTombolLayout.setHorizontalGroup(
            pTombolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTombolLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(742, Short.MAX_VALUE))
        );
        pTombolLayout.setVerticalGroup(
            pTombolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTombolLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pTombolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUbah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pTampilan.add(pTombol, java.awt.BorderLayout.PAGE_END);

        pInputan.setMinimumSize(new java.awt.Dimension(1235, 338));
        pInputan.setPreferredSize(new java.awt.Dimension(1235, 338));

        tFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tFoto.setText("foto");
        tFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        tFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tFotoMouseClicked(evt);
            }
        });

        lblNis.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        lblNis.setText("NIS");

        tNIS.setMinimumSize(new java.awt.Dimension(200, 34));
        tNIS.setPreferredSize(new java.awt.Dimension(200, 34));
        tNIS.addActionListener(this::tNISActionPerformed);

        lblNama.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        lblNama.setText("Nama");

        tNamaSiswa.setMinimumSize(new java.awt.Dimension(200, 34));
        tNamaSiswa.setPreferredSize(new java.awt.Dimension(200, 34));
        tNamaSiswa.addActionListener(this::tNamaSiswaActionPerformed);

        lblJenisKelamin.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        lblJenisKelamin.setText("Jenis Kelamin");

        cJenisKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-Laki", "Perempuan", "  " }));
        cJenisKelamin.setPreferredSize(new java.awt.Dimension(200, 34));

        lblTempatLahir.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        lblTempatLahir.setText("Tempat Lahir");

        tTempatLahir.setMinimumSize(new java.awt.Dimension(200, 34));
        tTempatLahir.setPreferredSize(new java.awt.Dimension(200, 34));
        tTempatLahir.addActionListener(this::tTempatLahirActionPerformed);

        lblTanggalLahir.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        lblTanggalLahir.setText("Tanggal Lahir");

        lblHp.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        lblHp.setText("HP");

        tHP.setMinimumSize(new java.awt.Dimension(200, 34));
        tHP.setPreferredSize(new java.awt.Dimension(200, 34));

        lblKelas.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        lblKelas.setText("Kelas");

        lblAlamat.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        lblAlamat.setText("Alamat");

        tAlamat.setColumns(20);
        tAlamat.setRows(5);
        jScrollPane2.setViewportView(tAlamat);

        tTanggal.setDateFormatString("yyyy-MM-dd\n");
        tTanggal.setMinimumSize(new java.awt.Dimension(200, 34));
        tTanggal.setPreferredSize(new java.awt.Dimension(200, 34));

        cKelas.setPreferredSize(new java.awt.Dimension(200, 34));

        javax.swing.GroupLayout pInputanLayout = new javax.swing.GroupLayout(pInputan);
        pInputan.setLayout(pInputanLayout);
        pInputanLayout.setHorizontalGroup(
            pInputanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInputanLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(tFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(pInputanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .addComponent(tTempatLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNis)
                    .addComponent(lblNama)
                    .addComponent(lblJenisKelamin)
                    .addComponent(lblTempatLahir)
                    .addComponent(lblTanggalLahir)
                    .addComponent(tNamaSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tNIS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cJenisKelamin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(pInputanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblHp)
                    .addComponent(lblKelas)
                    .addComponent(lblAlamat)
                    .addComponent(tHP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                    .addComponent(cKelas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        pInputanLayout.setVerticalGroup(
            pInputanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInputanLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pInputanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pInputanLayout.createSequentialGroup()
                        .addGroup(pInputanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNis)
                            .addComponent(lblHp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pInputanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tNIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pInputanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNama)
                            .addComponent(lblKelas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pInputanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tNamaSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pInputanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblJenisKelamin)
                            .addComponent(lblAlamat))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pInputanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pInputanLayout.createSequentialGroup()
                                .addComponent(cJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTempatLahir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTanggalLahir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2))))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pTampilan.add(pInputan, java.awt.BorderLayout.CENTER);

        pContentSiswa.add(pTampilan, java.awt.BorderLayout.PAGE_START);

        pTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242), 20));
        pTable.setLayout(new java.awt.BorderLayout());

        pPath.setMinimumSize(new java.awt.Dimension(100, 30));
        pPath.setPreferredSize(new java.awt.Dimension(920, 30));

        tFotoPath.setText("jLabel1");

        javax.swing.GroupLayout pPathLayout = new javax.swing.GroupLayout(pPath);
        pPath.setLayout(pPathLayout);
        pPathLayout.setHorizontalGroup(
            pPathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPathLayout.createSequentialGroup()
                .addComponent(tFotoPath)
                .addGap(0, 1172, Short.MAX_VALUE))
        );
        pPathLayout.setVerticalGroup(
            pPathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pPathLayout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(tFotoPath)
                .addContainerGap())
        );

        pTable.add(pPath, java.awt.BorderLayout.PAGE_END);

        jPanel2.setLayout(new java.awt.CardLayout());

        tblSiswa.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSiswaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSiswa);

        jPanel2.add(jScrollPane3, "card2");

        pTable.add(jPanel2, java.awt.BorderLayout.CENTER);

        pContentSiswa.add(pTable, java.awt.BorderLayout.CENTER);

        add(pContentSiswa, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void tNamaSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNamaSiswaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tNamaSiswaActionPerformed

    private void tTempatLahirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tTempatLahirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tTempatLahirActionPerformed

    private void tNISActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNISActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tNISActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        reset();//memanggil method reset untuk mengosongkan semua input pada form
    }//GEN-LAST:event_btnResetActionPerformed

    private void tblSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSiswaMouseClicked
        // TODO add your handling code here:
        //mengambil indeks baris yang di klik pada tabel siswa
        int baris = tblSiswa.rowAtPoint(evt.getPoint());
        //mengambil nilai dari kolom pertama(NIS) pada baris yang di klik dan mengubah ke string
        String nis = tblSiswa.getValueAt(baris, 0).toString();
        //mengambilnilai dari kolom kedua (namaSiswa) pada baris yang di klik dan mengubah ke string
        String namaSiswa = tblSiswa.getValueAt(baris, 1).toString();
        
        Object jkObj = tblSiswa.getValueAt(baris, 2);//mengambil object dari kolom ke 3(jenisKelamin disingkat jk)
        
        Object tempatLahirObj = tblSiswa.getValueAt(baris, 3);//mengambil object dari kolom ke 4(tempatLahir)
        
        Object tglLahirObj = tblSiswa.getValueAt(baris, 4);//mengambil object dari kolom ke 5(tglLahir)
        
        Object kelasObj = tblSiswa.getValueAt(baris, 5);//mengambil object dari kolom ke 6(kelas)
        
        Object hpObj = tblSiswa.getValueAt(baris, 6);//mengambil object dari kolom ke 7(hp)
        
        //menampilkan nilai nis pada field input dan membuatnya tidak bisa diubah
        tNIS.setText(nis);
        tNIS.setEditable(false);
        
        tNamaSiswa.setText(namaSiswa);//menampilkan nama siswa ke field input
        //mengonversi objek menjadi string, jika null maka hasilnya null atau string kosong
        String jenisKelamin=(jkObj != null)?
                jkObj.toString():null;
        String tempatLahir=(tempatLahirObj != null)?
                tempatLahirObj.toString():null;
        String tglLahir=(tglLahirObj!= null)?
                tglLahirObj.toString():null;
        String idKelas=(kelasObj != null)?
                kelasObj.toString():null;
        String noHP=(hpObj != null)?
                hpObj.toString():null;
        //menampilkan tempat lahir, no hp, dan memilih kelas sesuai data
        tTempatLahir.setText(tempatLahir);
        tHP.setText(noHP);
        cKelas.setSelectedItem(idKelas);
        //jika tanggal lahir tidak null dan tidak kosong, ubah ke format DATE dan tampilkan di komponen kalender
        if (tglLahir!=null && !tglLahir.isEmpty()){
            try {
                tTanggal.setDate(java.sql.Date.valueOf(tglLahir));
            } catch (IllegalArgumentException e) {
                //jika gagal parsing tanggal, kosongkan field tanggal
                tTanggal.setDate(null);
            }
        }else {
            tTanggal.setDate(null);
        }
        //Konversi kode jenis kelamin ke bentuk tampilan yang dipahami pengguna
        switch (jenisKelamin){
            case "L":
            cJenisKelamin.setSelectedItem("Laki-Laki");
            break;
            
            case "P":
            cJenisKelamin.setSelectedItem("Perempuan");
            break;
            
            default:
            cJenisKelamin.setSelectedItem(null);
            break;
        }
        
        try {
            //Query untuk mengambil data alamat, dan foto berdasarkan NIS
            String sql = "SELECT alamat, foto FROM siswa WHERE nis =?";
            
            Connection conn = koneksi.konek();//membuka koneksi ke data base
            
            PreparedStatement ps = conn.prepareCall(sql);//menyiapkan statement SQL dengan parameter 
            
            ps.setString(1, nis);//mengisi parameter dengan NIS
            
            ResultSet rs = ps.executeQuery();//menjalankan query dan menyimpan hasilnya
            
            if (rs.next()) { //jika data ditemukan 
                //mengambil alamat dan foto dari hasil Query
                String alamat = rs.getString("alamat");
                String foto = rs.getString("foto");
                
                tAlamat.setText(alamat);//menampilkan alamat ke field input
                //jika path foto tidak koosong, tampilkan gambar ke label foto
                if (foto != null && !foto.isEmpty()) {
                    ImageIcon icon = new ImageIcon(foto);
                    Image image = icon.getImage().getScaledInstance(tFoto.getWidth(), tFoto.getHeight(), Image.SCALE_SMOOTH);
                    //tampilkan path foto ke label fotopath
                    tFotoPath.setText(foto);
                    tFoto.setText(null);
                    tFoto.setIcon(new ImageIcon(image));
                } else {
                    //jika tidak ada foto, menampilkan teks "Foto" dan menghapus icon
                    tFoto.setText("Foto");
                    tFoto.setIcon(null);
                    
                }
                
            }
        } catch (SQLException e) { 
            //menampilkan error ke console jika terjadi kesalahan SQL
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_tblSiswaMouseClicked

    private void tFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tFotoMouseClicked
        // TODO add your handling code here:
        //Bloktry digunakan untuk menganggani kemunginan error saat memilih dan memuat file gambar
        try {
            JFileChooser chooser = new JFileChooser(); //membuat object JFileChooser untuk membuka dialog pemilihan file
            
            int result = chooser.showOpenDialog(null);
            //mengecek apakah pengguna menekan tombol "OPEN" (OKE)
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile(); //mengambil file yang dipilih oleh pengguna
                
                if (file != null) { //mengecek apakah file yang dipilih tidak null 
                    ImageIcon icon = new ImageIcon(file.toString()); //membuat objek ImageIcon dari file gambar yang dipilih
                    
                    Image image = icon.getImage().getScaledInstance( //mengubah ukuran gambar agar sesuai dengan label tFoto
                            tFoto.getWidth(),
                            tFoto.getHeight(),
                            Image.SCALE_SMOOTH);
                    
                    ImageIcon ic = new ImageIcon(image); //membuat ImageIcon baru dari gambar yang telah diubah ukuran nya
                    
                    tFoto.setText(null); //menghapus text pada label foto
                    
                    tFoto.setIcon(ic); //menampilkan gambar (icon) ke dalam label tFoto
                    
                    String filename = file.getAbsolutePath(); //mengambil path absolute dari file gambar dan menyimpan nya ke field tFotoPath
                    tFotoPath.setText(filename);
                }
            } else {
                //jika pengguna menekan tombol cancel, tampilkan pesan ke konsole 
                System.out.println("Pemilihan file dibatalkan oleh pengguna");
            }
        } catch (HeadlessException e) {
            //menanggani error jika terjadi kesalahan saat memilih atau memuat file gambar
            JOptionPane.showMessageDialog(null,"Error Upload : ");
        }
    }//GEN-LAST:event_tFotoMouseClicked

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        // Mengambil teks dari field NIS
            String nis = tNIS.getText();
    // Mengambil teks dari field Nama Siswa
                String namaSiswa = tNamaSiswa.getText();
    // Mengambil item yang dipilih dari combo box jenis kelamin dan mengubahnya menjadi string
                String jenisKelamin = cJenisKelamin.getSelectedItem().toString();
    // Variabel untuk menyimpan hasil konversi jenis kelamin (L/P)
                String jK = null;
    // Mengambil teks dari field Tempat Lahir
                String tempatLahir = tTempatLahir.getText();
    // Mengambil tanggal dari komponen kalender
                Date tglLahirDate = tTanggal.getDate();
    // Mengubah tanggal lahir menjadi format "yyyy-MM-dd"
                String tglLahir = new SimpleDateFormat("yyyy-MM-dd").format(tglLahirDate);
    // Mengambil teks dari field nomor HP
                String hp = tHP.getText();
    // Mengambil item yang dipilih dari combo box kelas
                String kelas = cKelas.getSelectedItem().toString();
    // Mengambil teks dari field alamat
                String alamat = tAlamat.getText();
    // Mengambil path file dari label path foto
                String filePath = tFotoPath.getText();
    // Konversi jenis kelamin dari teks menjadi kode (L atau P)
            switch (jenisKelamin) {
            case "Laki-laki":
            jK = "L";
            break;
            case "Perempuan":
            jK = "P";
            break;
            default:
            jK = null;
            break;
}
// Variabel untuk menyimpan path file foto tujuan
            String foto = null;
// Mengecek apakah ada path file foto yang dipilih
        if (filePath.length() != 0) {
            try {
        // Menyimpan path sumber file
                String sourcePath = filePath;
                File sourceFile = new File(sourcePath);
                // Menentukan folder tujuan untuk menyimpan foto
                String destinationFolderPath = "src/foto/";
                File destinationFolder = new File(destinationFolderPath);
                // Jika folder tujuan belum ada, buat folder tersebut
                if (!destinationFolder.exists()) {
                    destinationFolder.mkdir();
                } 
                // Mengambil ekstensi file (contoh: jpg, png, dll)
                String extension = sourcePath.substring(sourcePath.lastIndexOf('.') + 1);
                // Membuat nama file baru yang unik berdasarkan timestamp
                String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String destinationFileName = "foto-" + timestamp + "." + extension;
                // Membuat file tujuan dengan path dan nama file baru
                File destinationFile = new File(destinationFolderPath + destinationFileName);
                // Menyalin file dari sumber ke tujuan
                Files.copy(sourceFile.toPath(), destinationFile.toPath());
                // Menyimpan path foto yang telah dipindahkan
                foto = destinationFile.getPath();
        } catch (Exception e) {
            // Menampilkan pesan error jika gagal mengupload file
            JOptionPane.showMessageDialog(null, "Gagal upload file: " + e.getMessage());
        }
    } else {
        // Jika tidak ada file yang dipilih, set null
        foto = null;
    }
    try {
        // Query SQL untuk menyimpan data siswa ke database
        String sql = "INSERT INTO siswa(nis,nama_siswa,gender,tempat_lahir,tgl_lahir,alamat,no_hp,id_kelas,foto)"
                + " VALUES(?,?,?,?,?,?,?,?,?)";
        // Membuka koneksi ke database
        Connection conn = koneksi.konek();
        // Menyiapkan statement SQL dengan parameter
        PreparedStatement statement = conn.prepareStatement(sql);
        // Mengisi nilai parameter satu per satu
        statement.setString(1, nis);
        statement.setString(2, namaSiswa);
        statement.setString(3, jK);
        statement.setString(4, tempatLahir);
        statement.setString(5, tglLahir);
        statement.setString(6, alamat);
        statement.setString(7, hp);
        statement.setString(8, kelas);
        statement.setString(9, foto);
        // Menjalankan query penyimpanan
        statement.execute();
        // Menampilkan pesan bahwa data berhasil disimpan
        JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
    } catch (SQLException e) {
        // Menampilkan pesan jika terjadi kesalahan saat menyimpan data
        JOptionPane.showMessageDialog(null, "Data gagal disimpan!");
    }
    // Memuat ulang data siswa ke tabel
    load_tabel_siswa();
    // Mengosongkan semua input form setelah data disimpan
    reset();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        // Mengambil NIS dari field input
        String nis = tNIS.getText();

        // Mengambil Nama Siswa dari field input
        String namaSiswa = tNamaSiswa.getText();

        // Mengambil nilai dari combo box jenis kelamin dan mengubah menjadi String
        String jenisKelamin = cJenisKelamin.getSelectedItem().toString();

        // Variabel untuk menyimpan kode jenis kelamin ('L' atau 'P')
        String jK = null;

        // Mengambil Tempat Lahir dari field input
        String tempatLahir = tTempatLahir.getText();

        // Mengambil tanggal lahir dari komponen kalender
        Date tglLahirDate = tTanggal.getDate();

        // Mengubah tanggal lahir menjadi format "yyyy-MM-dd"
        String tglLahir = new SimpleDateFormat("yyyy-MM-dd").format(tglLahirDate);

        // Mengambil Nomor HP dari field input
        String hp = tHP.getText();

        // Mengambil Kelas dari combo box
        String kelas = cKelas.getSelectedItem().toString();

        // Mengambil Alamat dari field input
        String alamat = tAlamat.getText();

        // Mengambil path file foto dari field input tersembunyi
        String filePath = tFotoPath.getText();

        // Mengonversi pilihan jenis kelamin ke kode (L/P)
        switch (jenisKelamin) {
            case "Laki-laki":
                jK = "L";
                break;
            case "Perempuan":
                jK = "P";
                break;
            default:
                jK = null;
                break;
        }

        // Variabel untuk menyimpan path foto asli yang tersimpan di database
        String fotoAsli = null;

        try {
            // Query untuk mengambil path foto berdasarkan NIS
            String sql = "SELECT foto FROM siswa WHERE nis = ?";
            Connection conn = koneksi.konek();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nis);
            ResultSet rs = ps.executeQuery();

            // Jika data ditemukan, simpan path foto ke variabel fotoAsli
            if (rs.next()) {
                fotoAsli = rs.getString("foto");
            }
        } catch (SQLException e) {
            // Tampilkan pesan jika gagal mengambil foto dari database
            JOptionPane.showMessageDialog(null, "Gagal mengambil foto asli: " + e.getMessage());
        }

        // Menentukan apakah foto diubah oleh pengguna
        boolean fotoDiubah = (fotoAsli == null && !filePath.isEmpty())
                || (fotoAsli != null && !fotoAsli.equals(filePath));

        // Jika foto diubah, variabel 'foto' akan diisi dengan path baru
        String foto = fotoAsli;
 
        if (fotoDiubah) {
            try {
                // Ambil file dari path baru
                File sourceFile = new File(filePath);

                // Dapatkan ekstensi file
                String extension = filePath.substring(filePath.lastIndexOf('.') + 1);

                // Buat nama file baru berdasarkan waktu agar unik
                String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String destinationPath = "src/foto/foto-" + timestamp + "." + extension;

                // Salin file ke folder tujuan
                File destFile = new File(destinationPath);
                Files.copy(sourceFile.toPath(), destFile.toPath());

                // Simpan path tujuan ke variabel 'foto'
                foto = destinationPath;

            } catch (Exception e) {
                // Tampilkan pesan jika gagal upload file
                JOptionPane.showMessageDialog(null, "Gagal upload file: " + e.getMessage());
            }
        }

        try {
            // Query SQL berbeda tergantung apakah foto diubah atau tidak
            String sql2;
            if (fotoDiubah) {
                sql2 = "UPDATE siswa SET nama_siswa=?, gender=?, tempat_lahir=?, "
                        + "tgl_lahir=?, alamat=?, no_hp=?, id_kelas=?, foto=? WHERE nis=?";
            } else {
                sql2 = "UPDATE siswa SET nama_siswa=?, gender=?, tempat_lahir=?, "
                        + "tgl_lahir=?, alamat=?, no_hp=?, id_kelas=? WHERE nis=?";
            }

            // Membuka koneksi ke database
            Connection conn = koneksi.konek();

            // Menyiapkan statement untuk eksekusi SQL
            PreparedStatement statement = conn.prepareStatement(sql2);

            // Menetapkan parameter umum
            statement.setString(1, namaSiswa);
            statement.setString(2, jK);
            statement.setString(3, tempatLahir);
            statement.setString(4, tglLahir);
            statement.setString(5, alamat);
            statement.setString(6, hp);
            statement.setString(7, kelas);

            // Jika foto diubah, tetapkan parameter tambahan
            if (fotoDiubah) {
                statement.setString(8, foto);
                statement.setString(9, nis);
            } else {
                statement.setString(8, nis);
            }

            // Eksekusi perintah update
            statement.execute();

            // Tampilkan pesan sukses
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");

        } catch (SQLException e) {
            // Tampilkan pesan jika update gagal
            JOptionPane.showMessageDialog(null, "Gagal memperbarui data: " + e.getMessage());
        }

        // Muat ulang tabel agar perubahan terlihat
        load_tabel_siswa();

        // Kosongkan form setelah proses selesai
        reset();
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        String nis = tNIS.getText();//mengambil nilai NIS dari field input 
        
        String sql = "DELETE FROM siswa WHERE nis=?";// Query / Perintah untuk menghapus data siswa berdasarkan nis
        
            try {
            Connection conn = koneksi.konek(); ///membuka koneksi ke database
            
            PreparedStatement statement = conn.prepareStatement(sql); //menyiapkan statement SQL untuk di eksekusi
            
            statement.setString(1, nis); //mengisi parameter pertama (tanda ?) dengan nilai nis
            
            statement.execute();//menjalankan perintah delete
            
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");//menampilkan pesan bahwa data berhasil di hapus
        } catch (SQLException e) {
            //menampilkan pesan jika terjadi kesalahan saat menghapus 
            JOptionPane.showMessageDialog(null,"Gagal menghapus data"+e.getMessage());
        }
            load_tabel_siswa();//Muat ulang tabel agar perubahan terlihat
            
            reset();// Kosongkan form inputan setelah proses selesai
    }//GEN-LAST:event_btnHapusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cJenisKelamin;
    private javax.swing.JComboBox<String> cKelas;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblDataSiswa;
    private javax.swing.JLabel lblHp;
    private javax.swing.JLabel lblJenisKelamin;
    private javax.swing.JLabel lblKelas;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNis;
    private javax.swing.JLabel lblTanggalLahir;
    private javax.swing.JLabel lblTempatLahir;
    private javax.swing.JPanel pContentSiswa;
    private javax.swing.JPanel pHeaderSiswa;
    private javax.swing.JPanel pInputan;
    private javax.swing.JPanel pPath;
    private javax.swing.JPanel pTable;
    private javax.swing.JPanel pTampilan;
    private javax.swing.JPanel pTombol;
    private javax.swing.JTextArea tAlamat;
    private javax.swing.JLabel tFoto;
    private javax.swing.JLabel tFotoPath;
    private javax.swing.JTextField tHP;
    private javax.swing.JTextField tNIS;
    private javax.swing.JTextField tNamaSiswa;
    private com.toedter.calendar.JDateChooser tTanggal;
    private javax.swing.JTextField tTempatLahir;
    private javax.swing.JTable tblSiswa;
    // End of variables declaration//GEN-END:variables
}
