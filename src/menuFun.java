import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuFun extends JFrame {
    private JButton bntIngresoEst;
    private JButton btnverRegis;
    private JButton btnBuscar;
    private JPanel JPanel_menu;
    private JButton cerrarSesiónButton;

    public menuFun(){
        super("Menú");
        setSize(600,400);
        setLocationRelativeTo(null);
        setContentPane(JPanel_menu);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cerrarSesiónButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inicioSesion inicio = new inicioSesion();
                inicio.setVisible(true);
                dispose();
            }
        });
        bntIngresoEst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ingresoEst datos = new ingresoEst();
                datos.setVisible(true);
                dispose();
            }
        });
        btnverRegis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verTabla tabla = new verTabla();
                tabla.setVisible(true);
                dispose();
            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscaEst buscar = new buscaEst();
                buscar.setVisible(true);
                dispose();
            }
        });
    }
}
