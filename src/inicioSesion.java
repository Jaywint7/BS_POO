import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class inicioSesion extends JFrame{
    private JTextField txtusuario;
    private JPasswordField pswdcontra;
    private JButton btnIngresar;
    private JPanel JPanel_sesion;

    public inicioSesion(){
        super("Inicio Sesión");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(JPanel_sesion);


        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    verificarDatos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void verificarDatos() throws SQLException {
        String user = txtusuario.getText();
        String paswd = pswdcontra.getText();

        Connection conectar = conexion();
        String sql = "SELECT * FROM usuarioadmin WHERE usuAdmin = ? AND contra = ?";

        PreparedStatement pstmt = conectar.prepareStatement(sql);
        pstmt.setString(1, user);
        pstmt.setString(2, paswd);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            JOptionPane.showMessageDialog(null,"La información ingresada es correcta.");
            menuFun menu = new menuFun();
            menu.setVisible(true);
            dispose();
        }else{
            JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos.");
            txtusuario.setText("");
            pswdcontra.setText("");
        }
        rs.close();
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
