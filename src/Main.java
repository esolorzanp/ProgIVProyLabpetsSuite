import control.ExamenCtrl;
import control.LoginCtrl;
import control.PerfilCtrl;
import control.RazaCtrl;
import dao.ExamenDao;
import dao.PerfilDao;
import dao.RazaDao;
import dao.UsuarioDao;
import model.Examen;
import model.Perfil;
import model.Raza;
import model.Usuario;
import view.ExamenFrm;
import view.LoginFrm;
import view.PerfilFrm;
import view.RazaFrm;

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

        Usuario model = new Usuario();
        UsuarioDao dao = new UsuarioDao();
        LoginFrm vista = new LoginFrm();
        LoginCtrl control = new LoginCtrl(model, dao, vista);
        control.iniciar();

    }
}