import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class Ex2 extends JFrame {
    private final String dbURL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=QLSV;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
    private final String dbUser = "";        
    private final String dbPassword = "";

    private JTable table;
    public Ex2() {
        setTitle("Ex2: Show list of table from CSDL");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadData();
    }
    private void loadData() {
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            DatabaseMetaData dbMetaData = conn.getMetaData();

            rs = dbMetaData.getTables(null, null, "%", new String[] { "TABLE" });
            Vector<String> columnNames = new Vector<>();
            columnNames.add("TABLE_NAMES");

            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getString("TABLE_NAME"));
                data.add(row);
            }
            DefaultTableModel model = new DefaultTableModel(data, columnNames);
                table.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Ex2().setVisible(true);
        });
    }
}