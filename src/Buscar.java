import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Buscar {
    public JButton button1;
    public JLabel Busqueda;
    public JPanel panel1;
    public JTextField busqueda;
    private JButton regresar;

    public Buscar() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
                String user = "root";
                String password = "123456";


                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    System.out.println("Conectado a la base de datos");
                    String query = "SELECT * FROM PACIENTE WHERE cedula='" + busqueda.getText() + "'";

                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    if (resultSet.next()) {
                        String cedula= resultSet.getString("cedula");
                        String historial = resultSet.getString("n_historial_clinico");
                        String nombre = resultSet.getString("nombre");
                        int edad = resultSet.getInt("edad");

                        String telefono = resultSet.getString("telefono");
                        String descripcion = resultSet.getString("descripcion_enfermedad");


                        Busqueda.setText("<html>Cedula: " + cedula+ "<br>" +
                                "Historial Clinico"  + historial + "<br>" +
                                "Nombre:  " + nombre + "<br>" +
                                "Edad: "+edad+"<br>"+
                                "telefono: "+telefono+"<br>"+
                                "Descripcion de enfermedad: "+descripcion+"<br><html>");

                    } else {
                        busqueda.setText("Paciente no encontrado");
                    }
                } catch (SQLException ex) {
                    System.out.println("Error al ejecutar la consulta: " + ex.getMessage());
                }

            }
        });
        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Mi aplicación");
                frame.setContentPane(new NuevoPaciente().panelnuevo);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1000, 1000);
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(regresar)).dispose();
            }
        });
    }
}
