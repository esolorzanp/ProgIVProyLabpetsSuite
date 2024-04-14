import control.ExamenCtrl;
import control.RazaCtrl;
import dao.ExamenDao;
import dao.RazaDao;
import model.Examen;
import model.Raza;
import view.ExamenFrm;
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

        Raza model = new Raza();
        RazaDao dao = new RazaDao();
        RazaFrm vista = new RazaFrm();
        RazaCtrl control = new RazaCtrl(model, dao, vista);
        control.iniciar();
    }
}