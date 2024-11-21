package Vista;

import Controlador.JefeCuadrillaController;
import Controlador.CuadrillaController;
import Modelo.JefeCuadrilla;
import Modelo.Cuadrilla;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GestionJefesCuadrillaView extends JFrame {
    private JTextField txtNombreJefe;
    private JComboBox<Cuadrilla> cmbCuadrillas;
    private JButton btnAgregar, btnActualizar, btnEliminar;
    private JTable tableJefes;
    private DefaultTableModel tableModel;

    private JefeCuadrillaController jefeController;
    private CuadrillaController cuadrillaController;

    public GestionJefesCuadrillaView() {
        setTitle("Gestión de Jefes de Cuadrilla");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        jefeController = new JefeCuadrillaController();
        cuadrillaController = new CuadrillaController();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        txtNombreJefe = new JTextField(20);
        cmbCuadrillas = new JComboBox<>();

        btnAgregar = new JButton("Agregar Jefe");
        btnActualizar = new JButton("Actualizar Jefe");
        btnEliminar = new JButton("Eliminar Jefe");

        formPanel.add(new JLabel("Nombre del Jefe:"));
        formPanel.add(txtNombreJefe);
        formPanel.add(new JLabel("Cuadrilla Asignada:"));
        formPanel.add(cmbCuadrillas);
        formPanel.add(btnAgregar);
        formPanel.add(btnActualizar);
        formPanel.add(btnEliminar);

        // Tabla
        tableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Cuadrilla"}, 0);
        tableJefes = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(tableJefes);

        panel.add(formPanel);
        panel.add(tableScrollPane);
        add(panel);

        // Listeners
        btnAgregar.addActionListener(e -> {
            String nombre = txtNombreJefe.getText();
            Cuadrilla cuadrilla = (Cuadrilla) cmbCuadrillas.getSelectedItem();

            boolean added = jefeController.agregarJefeCuadrilla(nombre, cuadrilla);
            if (added) {
                JOptionPane.showMessageDialog(this, "Jefe de Cuadrilla agregado exitosamente.");
                cargarJefes();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar el Jefe de Cuadrilla.");
            }
        });

        btnActualizar.addActionListener(e -> {
            int selectedRow = tableJefes.getSelectedRow();
            if (selectedRow >= 0) {
                int idJefe = (int) tableModel.getValueAt(selectedRow, 0);
                String nombre = txtNombreJefe.getText();
                Cuadrilla cuadrilla = (Cuadrilla) cmbCuadrillas.getSelectedItem();

                boolean updated = jefeController.actualizarJefeCuadrilla(idJefe, nombre, cuadrilla);
                if (updated) {
                    JOptionPane.showMessageDialog(this, "Jefe de Cuadrilla actualizado exitosamente.");
                    cargarJefes();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar el Jefe de Cuadrilla.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un Jefe de Cuadrilla para actualizar.");
            }
        });

        btnEliminar.addActionListener(e -> {
            int selectedRow = tableJefes.getSelectedRow();
            if (selectedRow >= 0) {
                int idJefe = (int) tableModel.getValueAt(selectedRow, 0);
                boolean deleted = jefeController.eliminarJefeCuadrilla(idJefe);
                if (deleted) {
                    JOptionPane.showMessageDialog(this, "Jefe de Cuadrilla eliminado exitosamente.");
                    cargarJefes();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el Jefe de Cuadrilla.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un Jefe de Cuadrilla para eliminar.");
            }
        });

        // Listener para llenar los campos al seleccionar una fila de la tabla
        tableJefes.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tableJefes.getSelectedRow();
            if (selectedRow >= 0) {
                txtNombreJefe.setText((String) tableModel.getValueAt(selectedRow, 1)); // Nombre del jefe

                // Seleccionar la cuadrilla correspondiente en el JComboBox
                String nombreCuadrilla = (String) tableModel.getValueAt(selectedRow, 2); // Nombre de la cuadrilla
                for (int i = 0; i < cmbCuadrillas.getItemCount(); i++) {
                    Cuadrilla cuadrilla = (Cuadrilla) cmbCuadrillas.getItemAt(i);
                    if (cuadrilla.getNombreCuadrilla().equals(nombreCuadrilla)) {
                        cmbCuadrillas.setSelectedIndex(i);
                        break;
                    }
                }
            }
        });

        cargarCuadrillas();
        cargarJefes();
    }

    private void cargarCuadrillas() {
        cmbCuadrillas.removeAllItems();
        List<Cuadrilla> cuadrillas = cuadrillaController.listarCuadrillas();
        for (Cuadrilla cuadrilla : cuadrillas) {
            cmbCuadrillas.addItem(cuadrilla);
        }
    }

    private void cargarJefes() {
        tableModel.setRowCount(0);
        List<JefeCuadrilla> jefes = jefeController.listarJefesCuadrilla();
        for (JefeCuadrilla jefe : jefes) {
            tableModel.addRow(new Object[]{
                    jefe.getIdJefe(),
                    jefe.getNombre(),
                    jefe.getCuadrillaAsignada().getNombreCuadrilla()
            });
        }
    }

    private void limpiarCampos() {
        txtNombreJefe.setText("");
        if (cmbCuadrillas.getItemCount() > 0) {
            cmbCuadrillas.setSelectedIndex(0);
        }
    }
}
