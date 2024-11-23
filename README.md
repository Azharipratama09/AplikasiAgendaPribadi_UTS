# AplikasiAgendaPribadi
 UTS-Muhammad Azhari Nur Pratama-2210010326
## 1.Deskripsi Program
Program ini adalah aplikasi GUI berbasis Java untuk mengelola Agenda harian. Pengguna dapat menambahkan, mengubah, menghapus, mencari Agenda, serta melakukan impor dan ekspor data. 
## 2.Komponen
Aplikasi ini dibuat menggunakan komponen GUI berikut:
  • JFrame: Sebagai kerangka utama aplikasi.
  
  • JPanel: Wadah komponen GUI.
  
  • JLabel: Label teks untuk elemen seperti "Tanggal", "Kegiatan", dll.
  
  • JDateChooser: Input untuk tanggal agenda.
  
  • JTextArea: Input dan tampilan isi keggiatan.
  
  • JButton: Tombol untuk berbagai aksi (Simpan, Ubah, Hapus, Cari, Impor, Ekspor, Keluar).
  
  • JButton: Tombol untuk berbagai aksi (Simpan, Ubah, Hapus, Cari, Impor, Ekspor, Keluar).
  
  • JTable: Menampilkan daftar agenda.
  
  • JFileChooser: Memilih file untuk impor atau ekspor data.

## 3Fitur Program

• Menambah Agenda: Pengguna dapat menambahkan Agenda baru dengan Tanggal dan kegiatan.

• Mengubah Agenda: Agenda yang ada dapat diperbarui.

• Menghapus Agenda: Agenda tertentu dapat dihapus.

• Mencari Agenda: Pencarian Agenda berdasarkan Tanggal/Kegiatan.

• Impor Data: Mengimpor catatan dari file.

• Ekspor Data: Mengekspor semua catatan ke file.

## Kode Terkait

## Menambah Catatan
~~~
if (tanggal == null || kegiatan == null || tanggal.isEmpty() || kegiatan.isEmpty()) {
        System.err.println("Error: Data tidak lengkap. Tanggal: " + tanggal + ", Kegiatan: " + kegiatan);
        return false;
    }

    String sqlInsert = "INSERT INTO agenda (tanggal, Kegiatan) VALUES (?, ?)";
    try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
        pstmt.setString(1, tanggal);
        pstmt.setString(2, kegiatan);
        pstmt.executeUpdate();
        System.out.println("Agenda berhasil ditambahkan.");
        return true;
    } catch (SQLException e) {
        System.err.println("Error adding agenda: " + e.getMessage());
        return false;
    }
}
 
~~~

## Menghapus Catatan
~~~
String sqlDelete = "DELETE FROM agenda WHERE id = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sqlDelete)) {
        pstmt.setInt(1, id);
        int rowsDeleted = pstmt.executeUpdate();
        return rowsDeleted > 0; // Mengembalikan true jika ada baris yang dihapus
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error menghapus agenda: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return false; // Mengembalikan false jika terjadi error
    }
}
~~~

## Memperbaharui Catatan
~~~
String sql = "UPDATE agenda SET tanggal = ?, kegiatan = ? WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, tanggal);
        stmt.setString(2, kegiatan);
        stmt.setString(3, id);
        return stmt.executeUpdate() > 0;
    }
}
~~~

### Mencari Agenda
~~~
private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
       String keyword = jTextField1.getText().toLowerCase();
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
    }    
~~~

### Ekspor Agenda
~~~
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

~~~
## Import Agenda
~~~
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
~~~

## Hasil Setelah DiRun 
![Cuplikan layar 2024-11-23 103531](https://github.com/user-attachments/assets/7bf88bd8-367a-4d04-a66f-b3ba3f094252)

## Indikator Penilaian:

| No  | Komponen                       |  Persentase  |
| :-: | --------------                 |   :-----:    |
|  1  | Fungsional aplikasi            |      20      |
|  2  | Desain dan UX                  |      20      |
|  3  | Penerapan Konsep OOP           |      15      |
|  4  | Kreativitas dan Inovasi Fitur  |      15      |
|  5  | Dokumentasi Kode               |      10      |
|  6  | Tantangan                      |      20      |
|     | *TOTAL*        | *100* |
