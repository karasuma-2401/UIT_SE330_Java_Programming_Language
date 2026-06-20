import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class Ex6 extends JFrame {
    private final String dbURL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=QLSV;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
    private final String dbUser = "";
    private final String dbPassword = "";

    private JTable tableStudent, tableClass, tableMetadata;
    private DefaultTableModel modelStudent, modelClass, modelMetadata;
    private JTextField txtID, txtName, txtClass, txtScore;
    private JTextField txtClassID, txtClassName, txtClassTeacher;
    
    private JComboBox<String> cbSearchType;
    private JTextField txtSearchSingle, txtScoreMin, txtScoreMax;
    private CardLayout searchCardLayout;
    private JPanel cardPanel;

    private JRadioButton rbAsc, rbDesc;
    private String currentSql = "SELECT * FROM SinhVien";
    private Object[] currentParams = null;

    public Ex6() {
        setTitle("Ex6: Database Management with Metadata Viewer");
        setSize(950, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Student", createStudentPanel());
        tabbedPane.addTab("Classroom", createClassroomPanel());
        tabbedPane.addTab("Database Metadata", createMetadataPanel());

        add(tabbedPane);
        loadDataStudent(currentSql, currentParams);
        loadDataClassroom();
    }

    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));
        
        inputPanel.add(new JLabel("Student Id:"));
        txtID = new JTextField();
        inputPanel.add(txtID);

        inputPanel.add(new JLabel("Full Name:"));
        txtName = new JTextField();
        inputPanel.add(txtName);

        inputPanel.add(new JLabel("Class:"));
        txtClass = new JTextField();
        inputPanel.add(txtClass);

        inputPanel.add(new JLabel("Score:"));
        txtScore = new JTextField();
        inputPanel.add(txtScore);

        JPanel btnPanel = new JPanel();
        JButton btnAdd = new JButton("Add");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");
        btnPanel.add(btnAdd);
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Student"));
        
        cbSearchType = new JComboBox<>(new String[]{"Student ID", "Full Name", "Class", "GPA Range"});
        searchPanel.add(new JLabel("Search By:"));
        searchPanel.add(cbSearchType);

        searchCardLayout = new CardLayout();
        cardPanel = new JPanel(searchCardLayout);

        JPanel singleInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        txtSearchSingle = new JTextField(15);
        singleInputPanel.add(txtSearchSingle);

        JPanel rangeInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        txtScoreMin = new JTextField(5);
        txtScoreMax = new JTextField(5);
        rangeInputPanel.add(new JLabel("From:"));
        rangeInputPanel.add(txtScoreMin);
        rangeInputPanel.add(new JLabel("To:"));
        rangeInputPanel.add(txtScoreMax);

        cardPanel.add(singleInputPanel, "SINGLE");
        cardPanel.add(rangeInputPanel, "RANGE");
        searchPanel.add(cardPanel);

        JButton btnSearch = new JButton("Search");
        searchPanel.add(btnSearch);

        JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sortPanel.setBorder(BorderFactory.createTitledBorder("Sort By GPA"));
        rbAsc = new JRadioButton("Ascending");
        rbDesc = new JRadioButton("Descending");
        ButtonGroup sortGroup = new ButtonGroup();
        sortGroup.add(rbAsc);
        sortGroup.add(rbDesc);
        sortPanel.add(rbAsc);
        sortPanel.add(rbDesc);

        JPanel actionPanel = new JPanel(new GridLayout(1, 2, 10, 5));
        actionPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        actionPanel.add(searchPanel);
        actionPanel.add(sortPanel);

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel upperTopPanel = new JPanel(new BorderLayout());
        upperTopPanel.add(inputPanel, BorderLayout.CENTER);
        upperTopPanel.add(btnPanel, BorderLayout.SOUTH);
        
        topPanel.add(upperTopPanel, BorderLayout.NORTH);
        topPanel.add(actionPanel, BorderLayout.SOUTH);

        tableStudent = new JTable();
        modelStudent = new DefaultTableModel();
        tableStudent.setModel(modelStudent);
        JScrollPane scrollPane = new JScrollPane(tableStudent);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        cbSearchType.addActionListener(e -> {
            String selected = (String) cbSearchType.getSelectedItem();
            if ("GPA Range".equals(selected)) {
                searchCardLayout.show(cardPanel, "RANGE");
            } else {
                searchCardLayout.show(cardPanel, "SINGLE");
            }
        });

        tableStudent.getSelectionModel().addListSelectionListener(e -> {
            int row = tableStudent.getSelectedRow();
            if (row >= 0) {
                txtID.setText(modelStudent.getValueAt(row, 0).toString());
                txtName.setText(modelStudent.getValueAt(row, 1).toString());
                txtClass.setText(modelStudent.getValueAt(row, 2).toString());
                txtScore.setText(modelStudent.getValueAt(row, 3).toString());
            }
        });

        btnAdd.addActionListener(e -> addStudent());
        btnEdit.addActionListener(e -> editStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnSearch.addActionListener(e -> searchStudent());

        rbAsc.addActionListener(e -> applySort());
        rbDesc.addActionListener(e -> applySort());

        return panel;
    }

    private JPanel createClassroomPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Classroom Information"));

        inputPanel.add(new JLabel("Class ID:"));
        txtClassID = new JTextField();
        inputPanel.add(txtClassID);

        inputPanel.add(new JLabel("Class Name:"));
        txtClassName = new JTextField();
        inputPanel.add(txtClassName);

        inputPanel.add(new JLabel("Teacher:"));
        txtClassTeacher = new JTextField();
        inputPanel.add(txtClassTeacher);

        JPanel btnPanel = new JPanel();
        JButton btnAdd = new JButton("Add");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");
        btnPanel.add(btnAdd);
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(btnPanel, BorderLayout.SOUTH);

        tableClass = new JTable();
        modelClass = new DefaultTableModel();
        tableClass.setModel(modelClass);
        JScrollPane scrollPane = new JScrollPane(tableClass);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        tableClass.getSelectionModel().addListSelectionListener(e -> {
            int row = tableClass.getSelectedRow();
            if (row >= 0) {
                txtClassID.setText(modelClass.getValueAt(row, 0).toString());
                txtClassName.setText(modelClass.getValueAt(row, 1).toString());
                txtClassTeacher.setText(modelClass.getValueAt(row, 2) != null ? modelClass.getValueAt(row, 2).toString() : "");
            }
        });

        btnAdd.addActionListener(e -> addClassroom());
        btnEdit.addActionListener(e -> editClassroom());
        btnDelete.addActionListener(e -> deleteClassroom());

        return panel;
    }

    private JPanel createMetadataPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnConstraints = new JButton("Show Constraints");
        JButton btnProcedures = new JButton("Show Stored Procedures");
        btnPanel.add(btnConstraints);
        btnPanel.add(btnProcedures);

        tableMetadata = new JTable();
        modelMetadata = new DefaultTableModel();
        tableMetadata.setModel(modelMetadata);
        JScrollPane scrollPane = new JScrollPane(tableMetadata);

        panel.add(btnPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        btnConstraints.addActionListener(e -> loadConstraints());
        btnProcedures.addActionListener(e -> loadStoredProcedures());

        return panel;
    }

    private void loadConstraints() {
        String sql = "SELECT name AS 'Constraint Name', type_desc AS 'Constraint Type' " +
                     "FROM sys.objects WHERE type IN ('C', 'F', 'PK', 'UQ')";
        executeMetadataQuery(sql, new String[]{"Constraint Name", "Constraint Type"});
    }

    private void loadStoredProcedures() {
        String sql = "SELECT name AS 'Procedure Name', create_date AS 'Created Date' " +
                     "FROM sys.procedures";
        executeMetadataQuery(sql, new String[]{"Procedure Name", "Created Date"});
    }

    private void executeMetadataQuery(String sql, String[] columnHeaders) {
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            Vector<String> columnNames = new Vector<>();
            for (String header : columnHeaders) {
                columnNames.add(header);
            }

            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getObject(1));
                row.add(rs.getObject(2));
                data.add(row);
            }
            modelMetadata.setDataVector(data, columnNames);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Metadata Fetch Error: " + e.getMessage());
        }
    }

    private void loadDataStudent(String sql, Object[] params) {
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                Vector<String> columnNames = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    columnNames.add(metaData.getColumnName(i));
                }

                Vector<Vector<Object>> data = new Vector<>();
                while (rs.next()) {
                    Vector<Object> row = new Vector<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.add(rs.getObject(i));
                    }
                    data.add(row);
                }
                modelStudent.setDataVector(data, columnNames);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchStudent() {
        String selected = (String) cbSearchType.getSelectedItem();
        currentSql = "SELECT * FROM SinhVien";
        currentParams = null;

        if ("Student ID".equals(selected)) {
            currentSql = "SELECT * FROM SinhVien WHERE MaSV = ?";
            currentParams = new Object[]{txtSearchSingle.getText().trim()};
        } else if ("Full Name".equals(selected)) {
            currentSql = "SELECT * FROM SinhVien WHERE HoTen LIKE ?";
            currentParams = new Object[]{"%" + txtSearchSingle.getText().trim() + "%"};
        } else if ("Class".equals(selected)) {
            currentSql = "SELECT * FROM SinhVien WHERE Lop = ?";
            currentParams = new Object[]{txtSearchSingle.getText().trim()};
        } else if ("GPA Range".equals(selected)) {
            try {
                double min = Double.parseDouble(txtScoreMin.getText().trim());
                double max = Double.parseDouble(txtScoreMax.getText().trim());
                currentSql = "SELECT * FROM SinhVien WHERE DiemTB BETWEEN ? AND ?";
                currentParams = new Object[]{min, max};
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for GPA range.");
                return;
            }
        }
        applySort();
    }

    private void applySort() {
        String finalSql = currentSql;
        if (rbAsc.isSelected()) {
            finalSql += " ORDER BY DiemTB ASC";
        } else if (rbDesc.isSelected()) {
            finalSql += " ORDER BY DiemTB DESC";
        }
        loadDataStudent(finalSql, currentParams);
    }

    private void loadDataClassroom() {
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Lop")) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            Vector<String> columnNames = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }

            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                data.add(row);
            }
            modelClass.setDataVector(data, columnNames);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addStudent() {
        String sql = "INSERT INTO SinhVien (MaSV, HoTen, Lop, DiemTB) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, txtID.getText());
            pstmt.setString(2, txtName.getText());
            pstmt.setString(3, txtClass.getText());
            pstmt.setDouble(4, Double.parseDouble(txtScore.getText()));
            pstmt.executeUpdate();
            loadDataStudent(currentSql, currentParams);
            clearStudentFields();
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Operation Error: " + e.getMessage());
        }
    }

    private void editStudent() {
        String sql = "UPDATE SinhVien SET HoTen = ?, Lop = ?, DiemTB = ? WHERE MaSV = ?";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, txtName.getText());
            pstmt.setString(2, txtClass.getText());
            pstmt.setDouble(3, Double.parseDouble(txtScore.getText()));
            pstmt.setString(4, txtID.getText());
            pstmt.executeUpdate();
            loadDataStudent(currentSql, currentParams);
            clearStudentFields();
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Operation Error: " + e.getMessage());
        }
    }

    private void deleteStudent() {
        String sql = "DELETE FROM SinhVien WHERE MaSV = ?";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, txtID.getText());
            pstmt.executeUpdate();
            loadDataStudent(currentSql, currentParams);
            clearStudentFields();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Operation Error: " + e.getMessage());
        }
    }

    private void addClassroom() {
        String sql = "INSERT INTO Lop (MaLop, TenLop, CVHT) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, txtClassID.getText());
            pstmt.setString(2, txtClassName.getText());
            pstmt.setString(3, txtClassTeacher.getText());
            pstmt.executeUpdate();
            loadDataClassroom();
            clearClassroomFields();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Operation Error: " + e.getMessage());
        }
    }

    private void editClassroom() {
        String sql = "UPDATE Lop SET TenLop = ?, CVHT = ? WHERE MaLop = ?";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, txtClassName.getText());
            pstmt.setString(2, txtClassTeacher.getText());
            pstmt.setString(3, txtClassID.getText());
            pstmt.executeUpdate();
            loadDataClassroom();
            clearClassroomFields();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Operation Error: " + e.getMessage());
        }
    }

    private void deleteClassroom() {
        String sql = "DELETE FROM Lop WHERE MaLop = ?";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, txtClassID.getText());
            pstmt.executeUpdate();
            loadDataClassroom();
            clearClassroomFields();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Operation Error: " + e.getMessage());
        }
    }

    private void clearStudentFields() {
        txtID.setText("");
        txtName.setText("");
        txtClass.setText("");
        txtScore.setText("");
    }

    private void clearClassroomFields() {
        txtClassID.setText("");
        txtClassName.setText("");
        txtClassTeacher.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Ex6().setVisible(true);
        });
    }
}