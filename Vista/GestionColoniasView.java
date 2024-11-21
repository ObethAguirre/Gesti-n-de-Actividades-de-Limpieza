
package Vista;

import Controlador.ColoniaController;
import Modelo.Colonia;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class GestionColoniasView extends JFrame {
    private JTextField txtNombre, txtCodigoPostal, txtMunicipio, txtEstado;
    private JButton btnAgregar, btnActualizar, btnEliminar;
    private JTable tableColonias;
    private DefaultTableModel tableModel;

    private ColoniaController coloniaController;

    public GestionColoniasView() throws SQLException {
        setTitle("Gestión de Colonias");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        coloniaController = new ColoniaController();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        txtNombre = new JTextField(20);
        txtCodigoPostal = new JTextField(10);
        txtMunicipio = new JTextField(20);
        txtEstado = new JTextField(20);

        btnAgregar = new JButton("Agregar Colonia");
        btnActualizar = new JButton("Actualizar Colonia");
        btnEliminar = new JButton("Eliminar Colonia");

        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(txtNombre);
        formPanel.add(new JLabel("Código Postal:"));
        formPanel.add(txtCodigoPostal);
        formPanel.add(new JLabel("Municipio:"));
        formPanel.add(txtMunicipio);
        formPanel.add(new JLabel("Estado:"));
        formPanel.add(txtEstado);
        formPanel.add(btnAgregar);
        formPanel.add(btnActualizar);
        formPanel.add(btnEliminar);

        // Tabla
        tableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Código Postal", "Municipio", "Estado"}, 0);
        tableColonias = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(tableColonias);

        panel.add(formPanel);
        panel.add(tableScrollPane);
        add(panel);

        // Listeners
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String codigoPostal = txtCodigoPostal.getText();
                String municipio = txtMunicipio.getText();
                String estado = txtEstado.getText();

                boolean added = coloniaController.agregarColonia(nombre, codigoPostal, municipio, estado);
                if (added) {
                    JOptionPane.showMessageDialog(GestionColoniasView.this, "Colonia agregada exitosamente.");
                    cargarColonias();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(GestionColoniasView.this, "Error al agregar la colonia.");
                }
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableColonias.getSelectedRow();
                if (selectedRow >= 0) {
                    int idColonia = (int) tableModel.getValueAt(selectedRow, 0);
                    String nombre = txtNombre.getText();
                    String codigoPostal = txtCodigoPostal.getText();
                    String municipio = txtMunicipio.getText();
                    String estado = txtEstado.getText();

                    boolean updated = coloniaController.actualizarColonia(idColonia, nombre, codigoPostal, municipio, estado);
                    if (updated) {
                        JOptionPane.showMessageDialog(GestionColoniasView.this, "Colonia actualizada exitosamente.");
                        cargarColonias();
                        limpiarCampos();
                    } else {
                        JOptionPane.showMessageDialog(GestionColoniasView.this, "Error al actualizar la colonia.");
                    }
                } else {
                    JOptionPane.showMessageDialog(GestionColoniasView.this, "Seleccione una colonia para actualizar.");
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableColonias.getSelectedRow();
                if (selectedRow >= 0) {
                    int idColonia = (int) tableModel.getValueAt(selectedRow, 0);
                    int confirm = JOptionPane.showConfirmDialog(GestionColoniasView.this,
                            "¿Está seguro de eliminar esta colonia?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        boolean deleted = coloniaController.eliminarColonia(idColonia);
                        if (deleted) {
                            JOptionPane.showMessageDialog(GestionColoniasView.this, "Colonia eliminada exitosamente.");
                            cargarColonias();
                            limpiarCampos();
                        } else {
                            JOptionPane.showMessageDialog(GestionColoniasView.this, "Error al eliminar la colonia.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(GestionColoniasView.this, "Seleccione una colonia para eliminar.");
                }
            }
        });

        tableColonias.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tableColonias.getSelectedRow();
            if (selectedRow >= 0) {
                txtNombre.setText((String) tableModel.getValueAt(selectedRow, 1));
                txtCodigoPostal.setText((String) tableModel.getValueAt(selectedRow, 2));
                txtMunicipio.setText((String) tableModel.getValueAt(selectedRow, 3));
                txtEstado.setText((String) tableModel.getValueAt(selectedRow, 4));
            }
        });

        cargarColonias();
    }

    private void cargarColonias() {
        tableModel.setRowCount(0);
        List<Colonia> colonias = coloniaController.listarColonias();
        for (Colonia colonia : colonias) {
            tableModel.addRow(new Object[]{
                colonia.getIdColonia(),
                colonia.getNombre(),
                colonia.getCodigoPostal(),
                colonia.getMunicipio(),
                colonia.getEstado()
            });
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtCodigoPostal.setText("");
        txtMunicipio.setText("");
        txtEstado.setText("");
    }
}
