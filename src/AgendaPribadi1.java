
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zhar
 */
public class AgendaPribadi1 extends javax.swing.JFrame {

    private final AgendaDatabaseHelper dbHelper;
    private final TableRowSorter<DefaultTableModel> sorter;
    private final DefaultTableModel model;
    private String TanggalBaru;

   public AgendaPribadi1() {
        initComponents();
        dbHelper = new AgendaDatabaseHelper();
        model = (DefaultTableModel) jTable1.getModel();
        sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);
        // Tambahkan listener hanya sekali di sini
        addTableSelectionListener();
        loadAgendaData();
    
jTable1.getSelectionModel().addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting() && jTable1.getSelectedRow() != -1) {
        int selectedRow = jTable1.getSelectedRow();
        String tanggalString = (String) jTable1.getValueAt(selectedRow, 1); // Ambil data tanggal
        String kegiatanString = (String) jTable1.getValueAt(selectedRow, 2); // Ambil data kegiatan

        // Sesuaikan format dengan data tabel
        SimpleDateFormat format = new SimpleDateFormat("dd MM yy");

        try {
            java.util.Date utilDate = format.parse(tanggalString);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // Parsing string ke objek Date
            java.util.Date TanggalString = null;
            jDateChooser1.setDate(TanggalString); // Set tanggal ke jDateChooser
            jTextArea1.setText(kegiatanString); // Set kegiatan ke jTextArea
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Gagal mengkonversi tanggal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); // Debugging
        }
    }
});
   }


     private void tambahAgenda() throws SQLException {
        String tanggal = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
        String kegiatan = jTextArea1.getText();

        if (tanggal.isEmpty() || kegiatan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mohon lengkapi semua data!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (dbHelper.tambahAgenda(tanggal, kegiatan)) {
            loadAgendaData();
            JOptionPane.showMessageDialog(this, "Kegiatan berhasil ditambahkan!");
            resetForm();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan kegiatan.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateAgenda() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin diperbarui!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString());
        String tanggal = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
        String kegiatan = jTextArea1.getText();

        if (tanggal.isEmpty() || kegiatan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mohon lengkapi semua data!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (dbHelper.updateAgenda(id, tanggal, kegiatan)) {
            loadAgendaData();
            JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!");
            resetForm();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void hapusAgenda() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString());
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (dbHelper.hapusAgenda(id)) {
                loadAgendaData();
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menghapus data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cariAgenda() {
        String keyword = jTextField1.getText().toLowerCase();
        if (keyword.trim().isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
        }
    }

    private void resetForm() {
        jDateChooser1.setDate(null);
        jTextArea1.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Aplikasi Agenda Pribadi");

        jDateChooser1.setDateFormatString("dd MM yy");

        jLabel2.setText("Tanggal       :");

        jLabel3.setText("Kegiatan    :");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Tanggal", "Kegiatan"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jLabel4.setText("Cari");

        jButton1.setText("Cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Hapus");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Tambah");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Edit");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Ekspor");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Impor");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Keluar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(306, 306, 306)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(131, 131, 131))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(jLabel2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)))
                        .addGap(48, 48, 48)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5)
                            .addComponent(jButton6)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel3)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(215, 215, 215)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(449, 449, 449)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton7))
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton4))
                        .addGap(218, 218, 218)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(58, 58, 58)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jButton1))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 String tanggal = ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText();
    String kegiatan = jTextArea1.getText();

    if (tanggal.isEmpty() || kegiatan.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Mohon lengkapi semua data!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

        try {
            if (dbHelper.tambahAgenda(tanggal, kegiatan)) {
                loadAgendaData();
                JOptionPane.showMessageDialog(this, "Kegiatan berhasil ditambahkan!");
            }   } catch (SQLException ex) {
            Logger.getLogger(AgendaPribadi1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
int selectedRow = jTable1.getSelectedRow();

    // Validasi apakah ada baris yang dipilih
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih baris yang ingin diperbarui!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Ambil model tabel
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

    // Validasi jumlah kolom
    if (model.getColumnCount() < 3) {
        JOptionPane.showMessageDialog(this, "Tabel tidak memiliki kolom yang cukup!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Ambil data dari form input
    String tanggal = ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText();
    String kegiatan = jTextArea1.getText();

    // Validasi input
    if (tanggal.isEmpty() || kegiatan.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Mohon lengkapi semua data!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Perbarui data di tabel
    model.setValueAt(tanggal, selectedRow, 1); // Kolom 1 untuk tanggal
    model.setValueAt(kegiatan, selectedRow, 2); // Kolom 2 untuk kegiatan

    // Ambil ID dari tabel (kolom pertama)
    String id = model.getValueAt(selectedRow, 0).toString(); // Pastikan kolom 0 adalah ID
        try {
            // Perbarui database
            if (!dbHelper.updateAgenda(id, tanggal, kegiatan)) {
                JOptionPane.showMessageDialog(this, "Gagal memperbarui data.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgendaPribadi1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
  int selectedRow = jTable1.getSelectedRow();
if (selectedRow == -1) {
JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
return;
}
int id = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString());
dbHelper.hapusAgenda(id);
loadAgendaData();
JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");


    }                                     

    private void CariActionPerformed(java.awt.event.ActionEvent evt) {                                     
    String keyword = jTextField1.getText();
DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
jTable1.setRowSorter(sorter);

if (keyword.trim().isEmpty()) {
sorter.setRowFilter(null);
} else {
sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
      int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin keluar?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
 JFileChooser fileChooser = new JFileChooser();
fileChooser.setDialogTitle("Simpan File");
int userSelection = fileChooser.showSaveDialog(this);

if (userSelection == JFileChooser.APPROVE_OPTION) {
File fileToSave = fileChooser.getSelectedFile();

try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToSave + ".csv"))) {
     DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
     for (int i = 0; i < model.getColumnCount(); i++) {
         bw.write(model.getColumnName(i) + ",");
     }
     bw.newLine();
     for (int i = 0; i < model.getRowCount(); i++) {
         for (int j = 0; j < model.getColumnCount(); j++) {
             bw.write(model.getValueAt(i, j).toString() + ",");
         }
         bw.newLine();
     }
     JOptionPane.showMessageDialog(this, "Data berhasil diekspor!");
 } catch (IOException ex) {
     JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
 }
}
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
      JFileChooser fileChooser = new JFileChooser();
fileChooser.setDialogTitle("Pilih File");
int userSelection = fileChooser.showOpenDialog(this);

if (userSelection == JFileChooser.APPROVE_OPTION) {
File fileToOpen = fileChooser.getSelectedFile();

try (BufferedReader br = new BufferedReader(new FileReader(fileToOpen))) {
     DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
     String line;
     while ((line = br.readLine()) != null) {
         String[] rowData = line.split(",");
         model.addRow(rowData);
     }
     JOptionPane.showMessageDialog(this, "Data berhasil diimpor!");
 } catch (IOException ex) {
     JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
 }
}
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       String keyword = jTextField1.getText().toLowerCase();
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
    }//GEN-LAST:event_jButton1ActionPerformed

private void EksporKeCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToSave + ".csv"))) {
                // Menulis header kolom
                bw.write("ID,Tanggal,Kegiatan\n"); // Sesuaikan dengan kolom tabel Anda

                // Menulis data dari model tabel
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        bw.write(model.getValueAt(i, j).toString());
                        if (j < model.getColumnCount() - 1) {
                            bw.write(",");
                        }
                    }
                    bw.newLine();
                }
                JOptionPane.showMessageDialog(this, "Data berhasil diekspor!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Gagal mengekspor data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void imporDariCSV() throws SQLException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(fileToOpen))) {
                String line;
                br.readLine(); // Skip header line (if exists)
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length >= 3) { // Minimal 3 kolom (ID, Tanggal, Kegiatan)
                        String tanggal = data[1];
                        String kegiatan = data[2];
                        dbHelper.tambahAgenda(tanggal, kegiatan);
                    }
                }
                loadAgendaData();
                JOptionPane.showMessageDialog(this, "Data berhasil diimpor!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Gagal mengimpor data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

     private void loadAgendaData() {
        model.setRowCount(0);
        List<Agenda> agendaList = dbHelper.getAgendaList();
        for (Agenda agenda : agendaList) {
            model.addRow(new Object[]{agenda.getId(), agenda.getTanggal(), agenda.getKegiatan()});
        }
    }

    private void addTableSelectionListener() {
    jTable1.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting() && jTable1.getSelectedRow() != -1) {
            int selectedRow = jTable1.getSelectedRow();
            String tanggalString = (String) jTable1.getValueAt(selectedRow, 1);
            String kegiatanString = (String) jTable1.getValueAt(selectedRow, 2);

            // Sesuaikan format dengan data tabel
            SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy"); 
            try {
                java.util.Date utilDate = format.parse(tanggalString); // Parsing tanggal
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // Konversi ke SQL Date

                jDateChooser1.setDate(utilDate); // Menggunakan java.util.Date untuk JDateChooser
                jTextArea1.setText(kegiatanString); // Set kegiatan ke jTextArea
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Gagal mengkonversi tanggal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    });
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AgendaPribadi1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgendaPribadi1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgendaPribadi1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgendaPribadi1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgendaPribadi1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    private static class model {

        private static void setRowCount(int i) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static void addRow(Object[] object) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static int getColumnCount() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static String getColumnName(int i) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static int getRowCount() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static Object getValueAt(int i, int j) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public model() {
        }
    }
}
