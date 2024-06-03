import control.*;
import dao.*;
import model.*;
import view.*;

public class Main {
    public static void main(String[] args) {
//        Especie model = new Especie();
//        EspecieDao dao = new EspecieDao();
//        EspecieView vista = new EspecieView();
//        EspecieCtrl control = new EspecieCtrl(model, dao, vista);
//        control.iniciar();

//        Examen model = new Examen();
//        ExamenDao dao = new ExamenDao();
//        ExamenFrm vista = new ExamenFrm();
//        ExamenCtrl control = new ExamenCtrl(model, dao, vista);
//        control.iniciar();

//        Raza model = new Raza();
//        RazaDao dao = new RazaDao();
//        RazaFrm vista = new RazaFrm();
//        RazaCtrl control = new RazaCtrl(model, dao, vista);
//        control.iniciar();

//        Perfil model = new Perfil();
//        PerfilDao dao = new PerfilDao();
//        PerfilFrm vista = new PerfilFrm();
//        PerfilCtrl control = new PerfilCtrl(model, dao, vista);
//        control.iniciar();

//        Usuario model = new Usuario();
//        UsuarioDao dao = new UsuarioDao();
//        LoginFrm vista = new LoginFrm();
//        LoginCtrl control = new LoginCtrl(model, dao, vista);
//        control.iniciar();

//        Tercero model = new Tercero();
//        TerceroDao dao = new TerceroDao();
//        TerceroFrm vista = new TerceroFrm();
//        TerceroCtrl control = new TerceroCtrl(model, dao, vista);
//        control.iniciar();

//        OrdenServicio model = new OrdenServicio();
//        OrdenServicioDao dao = new OrdenServicioDao();
//        OrdenesServicioFrm vista = new OrdenesServicioFrm();
////        FrmOrdenServicioCtrl control = new FrmOrdenServicioCtrl(model, dao, vista);
//        FrmOrdenServicioCtrl control = new FrmOrdenServicioCtrl(vista);
//        control.iniciar();

        MenuPrincipalFrm vista = new MenuPrincipalFrm();
        MenuPrincipalCtrl control = new MenuPrincipalCtrl(vista);
        control.iniciar();
    }
}