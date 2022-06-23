package controlador;

import modelo.SqlDistrito;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import modelo.Distrito;
import modelo.SqlForm;
import vista.frmDistrito;

public class CtrlForm implements ActionListener {

    private Distrito dis;
    private SqlDistrito ctrlDis;
    private frmDistrito frm;

    SqlForm sFrm = new SqlForm();

    public CtrlForm(Distrito dis, SqlDistrito ctrlDis, frmDistrito frm) {
        this.dis = dis;
        this.ctrlDis = ctrlDis;
        this.frm = frm;
        this.frm.btnBuscar.addActionListener(this);
        this.frm.btnNuevo.addActionListener(this);
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnEditar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
    }

    public void iniciar() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDistrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        frm.setTitle("Distritos");
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
        frm.txtCod.requestFocus();
        if (!sFrm.conexion(frm.tableDatos)) {
            bloquearTodo();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnGuardar) {
            if (!"".equals(frm.txtNom.getText())) {
                int est;
                if (frm.rbEstado.isSelected()) {
                    est = 1;
                } else {
                    est = 0;
                }

                dis.setNomDis(frm.txtNom.getText());
                dis.setEstDis(est);

                if (ctrlDis.insertar(dis)) {
                    JOptionPane.showMessageDialog(null, "Registro guardado");
                    sFrm.mostrarDatos("", frm.tableDatos);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar el registro");
                }
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Tiene que llenar el campo de Nombre");
            }
        }

        if (e.getSource() == frm.btnEditar) {
            if (!"".equals(frm.txtCod.getText()) && !"".equals(frm.txtNom.getText())) {
                if (esNumerico(frm.txtCod.getText())) {
                    int est;
                    if (frm.rbEstado.isSelected()) {
                        est = 1;
                    } else {
                        est = 0;
                    }

                    dis.setIdDis(Integer.parseInt(frm.txtCod.getText()));
                    dis.setNomDis(frm.txtNom.getText());
                    dis.setEstDis(est);

                    if (ctrlDis.modificar(dis)) {
                        JOptionPane.showMessageDialog(null, "Registro editado");
                        sFrm.mostrarDatos("", frm.tableDatos);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo editar el registro");
                    }
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "El código tiene que ser un número");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Tiene que llenar todos los campos.");
            }
        }

        if (e.getSource() == frm.btnEliminar) {
            if (!"".equals(frm.txtCod.getText())) {
                if (esNumerico(frm.txtCod.getText())) {
                    dis.setIdDis(Integer.parseInt(frm.txtCod.getText()));

                    if (ctrlDis.eliminar(dis)) {
                        JOptionPane.showMessageDialog(null, "Registro eliminado");
                        sFrm.mostrarDatos("", frm.tableDatos);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro");
                    }
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "El código tiene que ser un número");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Tiene que llenar el campo de Código");
            }
        }

        if (e.getSource() == frm.btnBuscar) {

            if (esNumerico(frm.txtCod.getText())) {
                dis.setIdDis(Integer.parseInt(frm.txtCod.getText()));

                if (ctrlDis.buscar(dis)) {
                    sFrm.mostrarDatos(String.valueOf(dis.getIdDis()), frm.tableDatos);
                    boolean selec;
                    if (dis.getEstDis() == 1) {
                        selec = true;
                    } else {
                        selec = false;
                    }
                    frm.txtCod.setText(String.valueOf(dis.getIdDis()));
                    frm.txtNom.setText(dis.getNomDis());
                    frm.rbEstado.setSelected(selec);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro el registro buscado");
                }
            } else {
                sFrm.mostrarDatos("", frm.tableDatos);
            }
        }

        if (e.getSource() == frm.btnNuevo) {
            limpiar();
            sFrm.mostrarDatos("", frm.tableDatos);
        }
    }

    public boolean esNumerico(String text) {
        try {
            int num = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void limpiar() {
        frm.txtCod.setText(null);
        frm.txtNom.setText(null);
        frm.rbEstado.setSelected(false);
        frm.txtCod.requestFocus();
    }

    public void bloquearTodo() {
        frm.btnNuevo.setEnabled(false);
        frm.btnBuscar.setEnabled(false);
        frm.btnGuardar.setEnabled(false);
        frm.btnEditar.setEnabled(false);
        frm.btnEliminar.setEnabled(false);
        frm.txtCod.setEnabled(false);
        frm.txtNom.setEnabled(false);
        frm.rbEstado.setEnabled(false);
    }
}
