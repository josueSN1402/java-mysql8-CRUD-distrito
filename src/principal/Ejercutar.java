package principal;

import modelo.SqlDistrito;
import controlador.CtrlForm;
import modelo.Distrito;
import vista.frmDistrito;

public class Ejercutar {

    public static void main(String[] args) {
        Distrito dis = new Distrito();
        SqlDistrito cDis = new SqlDistrito();
        frmDistrito frm = new frmDistrito();

        CtrlForm ctrl = new CtrlForm(dis, cDis, frm);
        ctrl.iniciar();
    }
}
