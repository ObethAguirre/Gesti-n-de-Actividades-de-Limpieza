
package Vista;


import Controlador.AdministradorController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContraseña;
    private JButton btnLogin;

    public LoginView() {
        setTitle("Login - Sistema de Gestión de Limpieza");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        
        JLabel lblUsuario = new JLabel("Usuario:");
        txtUsuario = new JTextField();
        
        JLabel lblContraseña = new JLabel("Contraseña:");
        txtContraseña = new JPasswordField();
        
        btnLogin = new JButton("Iniciar Sesión");
        
        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(lblContraseña);
        panel.add(txtContraseña);
        panel.add(new JLabel());
        panel.add(btnLogin);

        add(panel);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String contraseña = new String(txtContraseña.getPassword());
                AdministradorController adminController = new AdministradorController();

                if (adminController.validarCredenciales(usuario, contraseña)) {
                    JOptionPane.showMessageDialog(LoginView.this, "Inicio de sesión exitoso.");
                    dispose();
                    new MenuPrincipalView().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginView.this, "Credenciales incorrectas. Intente de nuevo.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
