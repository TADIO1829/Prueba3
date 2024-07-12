import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Ingresar extends Component {
    public JTextField userTxt;
    public JPasswordField contraTxT;
    public JButton Login;
    public JPanel panel;
    public JLabel user;
    public JLabel contra;

    public Ingresar() {
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = userTxt.getText().trim();
                String contra = new String(contraTxT.getPassword());
                if (validarUsuario(usuario, contra)) {

                    JFrame frame = new JFrame("Mi aplicación");
                    frame.setContentPane(new NuevoPaciente().panelnuevo);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(1000, 1000);
                    frame.setLocationRelativeTo(null);
                    frame.pack();
                    frame.setVisible(true);
                    ((JFrame) SwingUtilities.getWindowAncestor(Login)).dispose();

                } else {
                    JOptionPane.showMessageDialog(Ingresar.this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }
    private boolean validarUsuario(String usuario, String contrasena) {
        String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user = "root";
        String password = "123456";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM USUARIO WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
