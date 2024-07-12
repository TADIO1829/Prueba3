import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NuevoPaciente {
    public JTextField cedulaTxt;
    public JTextField nombreTxt;
    public JTextField telefonoTxT;
    public JTextField historialTxt;
    public JTextField apellidoTxT;
    public JTextField edadTxT;
    public JButton ingresarNuevoPacienteButton;
    public JButton buscarPacienteButton;
    public JTextField desTxt;
    public JPanel panelnuevo;

    public NuevoPaciente() {
        buscarPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                JFrame frame = new JFrame("Mi aplicación");
                frame.setContentPane(new Buscar().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1000, 1000);
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(buscarPacienteButton)).dispose();
            }
        });
        ingresarNuevoPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula=cedulaTxt.getText();
                int historial =Integer.parseInt(historialTxt.getText());
                String nombre=nombreTxt.getText();
                String apellido=apellidoTxT.getText();
                int edad=Integer.parseInt(edadTxT.getText());
                String telefono=telefonoTxT.getText();
                String descripcion=desTxt.getText();


                Pacientes pacientes = new Pacientes(cedula,descripcion,telefono,edad,apellido,nombre,historial);
                String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
                String user = "root";
                String password = "123456";
                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    String sql = "INSERT INTO PACIENTE (cedula,n_historial_clinico,nombre,apellido,telefono,edad,descripcion_enfermedad) VALUES (?,?,?,?,?,?,?)";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, pacientes.getCedula());
                    statement.setInt(2, pacientes.getHistorial());
                    statement.setString(3, pacientes.getNombre());
                    statement.setString(4, pacientes.getApellido());
                    statement.setString(5, pacientes.getTelefono());
                    statement.setInt(6, pacientes.getEdad());
                    statement.setString(7, pacientes.getDescripcion());

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(buscarPacienteButton, "Datos de persona insertados correctamente!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(buscarPacienteButton, "Error al insertar datos del paciente: " + ex.getMessage());
                }
            }
        });
    }


}
