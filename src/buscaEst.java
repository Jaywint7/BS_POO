import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class buscaEst extends JFrame {
    private JTextField txtcod_matri;
    private JButton regresarMenúButton;
    private JPanel JPanel_buscar;
    private JTextField txtnombre;
    private JTextField txtape;
    private JTextField txtdirec;
    private JTextField txt_telf;
    private JTextField txtcorreo;
    private JTextField txtedad;
    private JButton btnVerBusq;

    public buscaEst(){
        super("Datos Estudiante");
        setSize(800,400);
        setLocationRelativeTo(null);
        setContentPane(JPanel_buscar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        regresarMenúButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFun menu = new menuFun();
                menu.setVisible(true);
                dispose();
            }
        });
        btnVerBusq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mostrar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void mostrar() throws SQLException {
        int id= Integer.parseInt(txtcod_matri.getText());

        Connection conectado=conexion();
        String sql = "SELECT * FROM estudiante WHERE codMatricula = ?";

        PreparedStatement pstmt=conectado.prepareStatement(sql);
        pstmt.setInt(1,id);

        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            String nombre=rs.getString("nombre");
            txtnombre.setText(nombre);
            String apellido=rs.getString("apellido");
            txtape.setText(apellido);
            String direccion=rs.getString("direcccion");
            txtdirec.setText(direccion);
            String correo=rs.getString("correo");
            txtcorreo.setText(correo);
            String edad=rs.getString("edad");
            txtedad.setText(edad);
            String telf=rs.getString("telefono");
            txt_telf.setText(telf);
        }
        rs.close();
        pstmt.close();
        conectado.close();
    }


    public Connection conexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/curso";
        String user = "root";
        String password = "";

        return DriverManager.getConnection(url,user,password);
    }
}
