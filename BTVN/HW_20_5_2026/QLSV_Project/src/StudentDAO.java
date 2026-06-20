import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public List<Student> getStudents(String sql, Object... params) {
        List<Student> students = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    students.add(new Student(
                        rs.getString("MaSV"),
                        rs.getString("HoTen"),
                        rs.getString("Lop"),
                        rs.getDouble("DiemTB")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    public List<Student> getAllStudents() {
        return getStudents("SELECT * FROM SinhVien");
    }

    public boolean addStudent(Student student) {
        String sql = "INSERT INTO SinhVien (MaSV, HoTen, Lop, DiemTB) VALUES (?, ?, ?, ?)";
        return executeUpdate(sql, student.getId(), student.getName(), student.getClassName(), student.getScore());
    }

    public boolean updateStudent(Student student) {
        String sql = "UPDATE SinhVien SET HoTen = ?, Lop = ?, DiemTB = ? WHERE MaSV = ?";
        return executeUpdate(sql, student.getName(), student.getClassName(), student.getScore(), student.getId());
    }

    public boolean deleteStudent(String studentId) {
        String sql = "DELETE FROM SinhVien WHERE MaSV = ?";
        return executeUpdate(sql, studentId);
    }
    private boolean executeUpdate(String sql, Object... params) {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}