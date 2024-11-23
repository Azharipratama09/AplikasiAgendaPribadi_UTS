import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

class Agenda {
    private int id;
    private String tanggal;
    private String kegiatan;

    public Agenda(int id, String tanggal, String kegiatan) {
        this.id = id;
        this.tanggal = tanggal;
        this.kegiatan = kegiatan;
    }

    public int getId() {
        return id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getKegiatan() {
        return kegiatan;
    }
}


public class AgendaDatabaseHelper {
    private Connection conn;

    public AgendaDatabaseHelper() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:agenda.db");
            createTable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Membuat tabel agenda jika belum ada
    private void createTable() {
    String sqlCreateTable = "CREATE TABLE IF NOT EXISTS agenda (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "tanggal TEXT NOT NULL, " +
                            "Kegiatan TEXT NOT NULL)"; // Kolom 'judul' dihapus
    try (Statement stmt = conn.createStatement()) {
        stmt.execute(sqlCreateTable);
        System.out.println("Tabel agenda berhasil dibuat atau sudah ada.");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error creating table: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace(); // Untuk debugging
    }
}

    // Menambah agenda baru
public boolean tambahAgenda(String tanggal, String kegiatan) throws SQLException {
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


    // Menghapus agenda berdasarkan ID
    public boolean hapusAgenda(int id) {
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

    // Memperbarui agenda berdasarkan ID
    public boolean updateAgenda(String id, String tanggal, String kegiatan) throws SQLException {
    String sql = "UPDATE agenda SET tanggal = ?, kegiatan = ? WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, tanggal);
        stmt.setString(2, kegiatan);
        stmt.setString(3, id);
        return stmt.executeUpdate() > 0;
    }
}

    // Mengambil daftar agenda
    public List<Agenda> getAgendaList() {
        List<Agenda> agendaList = new ArrayList<>();
        String sqlSelect = "SELECT id, tanggal, Kegiatan FROM agenda";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sqlSelect)) {
            while (rs.next()) {
                agendaList.add(new Agenda(rs.getInt("id"), rs.getString("tanggal"), rs.getString("Kegiatan")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mengambil data agenda: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return agendaList;
    }

    boolean updateAgenda(int id, String tanggal, String kegiatan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
