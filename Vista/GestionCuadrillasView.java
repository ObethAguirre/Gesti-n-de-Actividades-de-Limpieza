
package Vista;

import Controlador.CuadrillaController;
import Modelo.Cuadrilla;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GestionCuadrillasView extends JFrame {
    private JTextField txtNombreCuadrilla;
    private JButton btnAgregar, btnActualizar, btnEliminar;
    private JTable tableCuadrillas;
    private DefaultTableModel tableModel;

    private CuadrillaController cuadrillaController;

    public GestionCuadrillasView() {
        setTitle("Gestión de Cuadrillas");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        cuadrillaController = new CuadrillaController();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        txtNombreCuadrilla = new JTextField(20);

        btnAgregar = new JButton("Agregar Cuadrilla");
        btnActualizar = new JButton("Actualizar Cuadrilla");
        btnEliminar = new JButton("Eliminar Cuadrilla");

        formPanel.add(new JLabel("Nombre de la Cuadrilla:"));
        formPanel.add(txtNombreCuadrilla);
        formPanel.add(btnAgregar);
        formPanel.add(btnActualizar);
        formPanel.add(btnEliminar);

        // Tabla
        tableModel = new DefaultTableModel(new String[]{"ID", "Nombre"}, 0);
        tableCuadrillas = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(tableCuadrillas);

        panel.add(formPanel);
        panel.add(tableScrollPane);
        add(panel);

        // Listeners
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombreCuadrilla.getText();

                boolean added = cuadrillaController.agregarCuadrilla(nombre);
                if (added) {
                    JOptionPane.showMessageDialog(GestionCuadrillasView.this, "Cuadrilla agregada exitosamente.");
                    cargarCuadrillas();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(GestionCuadrillasView.this, "Error al agregar la cuadrilla.");
                }
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableCuadrillas.getSelectedRow();
                if (selectedRow >= 0) {
                    int idCuadrilla = (int) tableModel.getValueAt(selectedRow, 0);
                    String nombre = txtNombreCuadrilla.getText();

                    boolean updated = cuadrillaController.actualizarCuadrilla(idCuadrilla, nombre);
                    if (updated) {
                        JOptionPane.showMessageDialog(GestionCuadrillasView.this, "Cuadrilla actualizada exitosamente.");
                        cargarCuadrillas();
                        limpiarCampos();
                    } else {
                        JOptionPane.showMessageDialog(GestionCuadrillasView.this, "Error al actualizar la cuadrilla.");
                    }
                } else {
                    JOptionPane.showMessageDialog(GestionCuadrillasView.this, "Seleccione una cuadrilla para actualizar.");
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableCuadrillas.getSelectedRow();
                if (selectedRow >= 0) {
                    int idCuadrilla = (int) tableModel.getValueAt(selectedRow, 0);
                    int confirm = JOptionPane.showConfirmDialog(GestionCuadrillasView.this,
                            "¿Está seguro de eliminar esta cuadrilla?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        boolean deleted = cuadrillaController.eliminarCuadrilla(idCuadrilla);
                        if (deleted) {
                            JOptionPane.showMessageDialog(GestionCuadrillasView.this, "Cuadrilla eliminada exitosamente.");
                            cargarCuadrillas();
                            limpiarCampos();
                        } else {
                            JOptionPane.showMessageDialog(GestionCuadrillasView.this, "Error al eliminar la cuadrilla.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(GestionCuadrillasView.this, "Seleccione una cuadrilla para eliminar.");
                }
            }
        });

        tableCuadrillas.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tableCuadrillas.getSelectedRow();
            if (selectedRow >= 0) {
                txtNombreCuadrilla.setText((String) tableModel.getValueAt(selectedRow, 1));
            }
        });

        cargarCuadrillas();
    }

    private void cargarCuadrillas() {
        tableModel.setRowCount(0);
        List<Cuadrilla> cuadrillas = cuadrillaController.listarCuadrillas();
        for (Cuadrilla cuadrilla : cuadrillas) {
            tableModel.addRow(new Object[]{
                cuadrilla.getIdCuadrilla(),
                cuadrilla.getNombreCuadrilla()
            });
        }
    }

    private void limpiarCampos() {
        txtNombreCuadrilla.setText("");
    }
}
