import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class verTabla extends JFrame{
    private JButton btnvisual;
    private JButton regresarMenúButton;
    private JPanel JPanel_reg;
    private JLabel lblmostrar;

    public verTabla() {
        super("Menú");
        setSize(1550,500);
        setLocationRelativeTo(null);
        setContentPane(JPanel_reg);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        regresarMenúButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFun menu = new menuFun();
                menu.setVisible(true);
                dispose();
            }
        });
        btnvisual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    verTabla();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void verTabla() throws SQLException{
        Connection conectar = conexion();
        String sql = "select * from estudiante";
        Statement statement = conectar.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        StringBuilder datos = new StringBuilder("<html>");

        while (resultSet.next()){
            datos.append("<b>CodMatricula: </b>").append(resultSet.getString("codMatricula")+"    --    ");
            datos.append("<b>Nombre: </b>").append(resultSet.getString("nombre")+"    --    ");
            datos.append("<b>Apellido: </b>").append(resultSet.getString("apellido")+"    --    ");
            datos.append("<b>Direccion: </b>").append(resultSet.getString("direcccion")+"    --    ");
            datos.append("<b>Correo: </b>").append(resultSet.getString("correo")+"    --    ");
            datos.append("<b>Edad: </b>").append(resultSet.getString("edad")+"    --    ");
            datos.append("<b>Telefono: </b>").append(resultSet.getString("telefono")+"    --    ");
            datos.append("<b>Nota1: </b>").append(resultSet.getString("nota1")+"    --    ");
            datos.append("<b>Nota2: </b>").append(resultSet.getString("nota2"));
            datos.append("<br>");
        }
        datos.append("</html>");
        lblmostrar.setText(datos.toString());
    }

    public Connection conexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/curso";
        String user = "root";
        String password = "";

        return DriverManager.getConnection(url,user,password);
    }
}
