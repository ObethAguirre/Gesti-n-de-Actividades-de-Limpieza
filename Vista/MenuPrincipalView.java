
package Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuPrincipalView extends JFrame {
    public MenuPrincipalView() {
        setTitle("Menú Principal - Sistema de Gestión de Limpieza");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton btnActividades = new JButton("Gestionar Actividades");
        JButton btnColonias = new JButton("Gestionar Colonias");
        JButton btnCuadrillas = new JButton("Gestionar Cuadrillas");
        JButton btnJefesCuadrilla = new JButton("Gestionar Jefes de Cuadrilla");
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");

        panel.add(btnActividades);
        panel.add(btnColonias);
        panel.add(btnCuadrillas);
        panel.add(btnJefesCuadrilla);
        panel.add(btnCerrarSesion);

        add(panel);

        // Listeners para abrir los formularios
        btnActividades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new GestionActividadesView().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuPrincipalView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnColonias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new GestionColoniasView().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuPrincipalView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnCuadrillas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GestionCuadrillasView().setVisible(true);
            }
        });

        btnJefesCuadrilla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GestionJefesCuadrillaView().setVisible(true);
            }
        });

        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MenuPrincipalView.this, "Sesión cerrada.");
                dispose();
                new LoginView().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPrincipalView().setVisible(true));
    }
}
