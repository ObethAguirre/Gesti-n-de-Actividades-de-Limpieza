package Vista;

import Controlador.ActividadController;
import Modelo.Actividad;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;

public class GestionActividadesView extends JFrame {
    private JTextField txtDescripcion;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JTextField txtImagen;
    private JButton btnAgregar, btnActualizar, btnEliminar, btnSeleccionarImagen;
    private JTable tableActividades;
    private JLabel lblImagenVistaPrevia;
    private DefaultTableModel tableModel;

    private ActividadController actividadController;

    public GestionActividadesView() throws SQLException {
        setTitle("Gestión de Actividades");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        actividadController = new ActividadController();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        txtDescripcion = new JTextField(20);
        txtFecha = new JTextField(10);
        txtHora = new JTextField(10);
        txtImagen = new JTextField(20);

        btnSeleccionarImagen = new JButton("Seleccionar Imagen");
        lblImagenVistaPrevia = new JLabel();
        lblImagenVistaPrevia.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagenVistaPrevia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblImagenVistaPrevia.setPreferredSize(new Dimension(200, 200));

        btnAgregar = new JButton("Agregar Actividad");
        btnActualizar = new JButton("Actualizar Actividad");
        btnEliminar = new JButton("Eliminar Actividad");

        formPanel.add(new JLabel("Descripción:"));
        formPanel.add(txtDescripcion);
        formPanel.add(new JLabel("Fecha (YYYY-MM-DD):"));
        formPanel.add(txtFecha);
        formPanel.add(new JLabel("Hora (HH:MM:SS):"));
        formPanel.add(txtHora);
        formPanel.add(new JLabel("Ruta de Imagen:"));
        formPanel.add(txtImagen);
        formPanel.add(btnSeleccionarImagen);
        formPanel.add(lblImagenVistaPrevia);
        formPanel.add(btnAgregar);
        formPanel.add(btnActualizar);
        formPanel.add(btnEliminar);

        // Tabla
        tableModel = new DefaultTableModel(new String[]{"ID", "Descripción", "Fecha", "Hora", "Imagen"}, 0);
        tableActividades = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(tableActividades);

        panel.add(formPanel);
        panel.add(tableScrollPane);
        add(panel);

        // Listeners
btnSeleccionarImagen.addActionListener(e -> {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(GestionActividadesView.this);
    if (result == JFileChooser.APPROVE_OPTION) {
        String originalPath = fileChooser.getSelectedFile().getAbsolutePath();
        String fileName = fileChooser.getSelectedFile().getName();

        // Carpeta de destino dentro del proyecto
        String projectPath = System.getProperty("user.dir"); // Directorio base del proyecto
        String targetFolder = projectPath + "/imagenes/actividades";
        String targetPath = targetFolder + "/" + fileName;

        // Crear la carpeta si no existe
        File folder = new File(targetFolder);
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (created) {
                System.out.println("Carpeta creada: " + targetFolder);
            } else {
                System.out.println("Error al crear la carpeta: " + targetFolder);
            }
        } else {
            System.out.println("La carpeta ya existe: " + targetFolder);
        }

        // Copiar el archivo a la carpeta
        try {
            Files.copy(Paths.get(originalPath), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Imagen copiada a: " + targetPath);
            txtImagen.setText(targetPath); // Ruta relativa

            // Vista previa de la imagen
            File imageFile = new File(targetPath);
            if (imageFile.exists()) {
                ImageIcon imageIcon = new ImageIcon(targetPath);
                Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                lblImagenVistaPrevia.setIcon(new ImageIcon(image));
                System.out.println("Vista previa cargada.");
            } else {
                System.out.println("La imagen no existe en: " + targetPath);
            }
        } catch (IOException ex) {
            System.out.println("Error al copiar la imagen.");
            ex.printStackTrace();
        }
    }
});

        btnAgregar.addActionListener(e -> {
            String descripcion = txtDescripcion.getText();
            String fecha = txtFecha.getText();
            String hora = txtHora.getText();
            String imagen = txtImagen.getText();

            boolean added = actividadController.agregarActividad(descripcion, java.sql.Date.valueOf(fecha),
                    java.sql.Time.valueOf(hora), imagen);
            if (added) {
                JOptionPane.showMessageDialog(GestionActividadesView.this, "Actividad agregada exitosamente.");
                cargarActividades();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(GestionActividadesView.this, "Error al agregar la actividad.");
            }
        });

        btnActualizar.addActionListener(e -> {
            int selectedRow = tableActividades.getSelectedRow();
            if (selectedRow >= 0) {
                int idActividad = (int) tableModel.getValueAt(selectedRow, 0);
                String descripcion = txtDescripcion.getText();
                String fecha = txtFecha.getText();
                String hora = txtHora.getText();
                String imagen = txtImagen.getText();

                boolean updated = actividadController.actualizarActividad(idActividad, descripcion,
                        java.sql.Date.valueOf(fecha), java.sql.Time.valueOf(hora), imagen);
                if (updated) {
                    JOptionPane.showMessageDialog(GestionActividadesView.this, "Actividad actualizada exitosamente.");
                    cargarActividades();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(GestionActividadesView.this, "Error al actualizar la actividad.");
                }
            } else {
                JOptionPane.showMessageDialog(GestionActividadesView.this, "Seleccione una actividad para actualizar.");
            }
        });

        btnEliminar.addActionListener(e -> {
            int selectedRow = tableActividades.getSelectedRow();
            if (selectedRow >= 0) {
                int idActividad = (int) tableModel.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(GestionActividadesView.this,
                        "¿Está seguro de eliminar esta actividad?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean deleted = actividadController.eliminarActividad(idActividad);
                    if (deleted) {
                        JOptionPane.showMessageDialog(GestionActividadesView.this, "Actividad eliminada exitosamente.");
                        cargarActividades();
                        limpiarCampos();
                    } else {
                        JOptionPane.showMessageDialog(GestionActividadesView.this, "Error al eliminar la actividad.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(GestionActividadesView.this, "Seleccione una actividad para eliminar.");
            }
        });

        tableActividades.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tableActividades.getSelectedRow();
            if (selectedRow >= 0) {
                txtDescripcion.setText((String) tableModel.getValueAt(selectedRow, 1));
                txtFecha.setText(tableModel.getValueAt(selectedRow, 2).toString());
                txtHora.setText(tableModel.getValueAt(selectedRow, 3).toString());
                txtImagen.setText((String) tableModel.getValueAt(selectedRow, 4));

                // Vista previa de la imagen
                String imagePath = txtImagen.getText();
                if (imagePath != null && !imagePath.isEmpty()) {
                    ImageIcon imageIcon = new ImageIcon(imagePath);
                    Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    lblImagenVistaPrevia.setIcon(new ImageIcon(image));
                } else {
                    lblImagenVistaPrevia.setIcon(null);
                }
            }
        });

        cargarActividades();
    }

    private void cargarActividades() {
        tableModel.setRowCount(0);
        List<Actividad> actividades = actividadController.listarActividades();
        for (Actividad actividad : actividades) {
            tableModel.addRow(new Object[]{
                    actividad.getIdActividad(),
                    actividad.getDescripcion(),
                    actividad.getFecha(),
                    actividad.getHora(),
                    actividad.getEvidenciaImagen()
            });
        }
    }

    private void limpiarCampos() {
        txtDescripcion.setText("");
        txtFecha.setText("");
        txtHora.setText("");
        txtImagen.setText("");
        lblImagenVistaPrevia.setIcon(null);
    }
}
