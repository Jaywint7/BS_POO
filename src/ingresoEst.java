import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ingresoEst extends JFrame{
    private JTextField txtcod_matri;
    private JTextField txtnombre;
    private JTextField txtape;
    private JTextField txtdirec;
    private JTextField txtedad;
    private JTextField txt_telf;
    private JTextField txtnota1;
    private JTextField txtnota2;
    private JButton guardarInformacionButton;
    private JTextField txtcorreo;
    private JButton regresarMenúButton;
    private JPanel JPanel_datoest;

    public ingresoEst() {
        super("Datos Estudiante");
        setSize(800,400);
        setLocationRelativeTo(null);
        setContentPane(JPanel_datoest);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        regresarMenúButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFun menu = new menuFun();
                menu.setVisible(true);
                dispose();
            }
        });


        guardarInformacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    insertDatos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void insertDatos() throws SQLException {
        String matricula = txtcod_matri.getText();
        String nombre = txtnombre.getText();
        String apellido = txtape.getText();
        String direc = txtdirec.getText();
        String correo = txtcorreo.getText();
        String edad = txtedad.getText();
        String telf = txt_telf.getText();
        String nota1 = txtnota1.getText();
        String nota2 = txtnota2.getText();

        Connection conectar = conexion();
        String sql = "INSERT INTO estudiante(codMatricula, nombre, apellido,direcccion, correo, edad, telefono, nota1, nota2)values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conectar.prepareStatement(sql);
        pstmt.setInt(1,Integer.parseInt(matricula));
        pstmt.setString(2, nombre);
        pstmt.setString(3, apellido);
        pstmt.setString(4, direc);
        pstmt.setString(5, correo);
        pstmt.setInt(6,Integer.parseInt(edad));
        pstmt.setInt(7,Integer.parseInt(telf));
        pstmt.setDouble(8,Double.parseDouble(nota1));
        pstmt.setDouble(9,Double.parseDouble(nota2));

        int rowsAffected=pstmt.executeUpdate();
        if ( (rowsAffected > 0)) {
            JOptionPane.showMessageDialog(null, "REGISTRO INSERTADO CORRECTAMENTE");
            txtcod_matri.setText("");
            txtnombre.setText("");
            txtape.setText("");
            txtdirec.setText("");
            txtcorreo.setText("");
            txtedad.setText("");
            txt_telf.setText("");
            txtnota1.setText("");
            txtnota2.setText("");
        }
        pstmt.close();
        conectar.close();
    }

    public Connection conexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/curso";
        String user = "root";
        String password = "";

        return DriverManager.getConnection(url,user,password);
    }
}
