import control.ExamenCtrl;
import dao.ExamenDao;
import model.Examen;
import view.ExamenFrm;

public class Main {
    public static void main(String[] args) {
//        Especie model = new Especie();
//        EspecieDao dao = new EspecieDao();
//        EspecieView vista = new EspecieView();
//        EspecieCtrl control = new EspecieCtrl(model, dao, vista);
//        control.iniciar();

        Examen model = new Examen();
        ExamenDao dao = new ExamenDao();
        ExamenFrm vista = new ExamenFrm();
        ExamenCtrl control = new ExamenCtrl(model, dao, vista);
        control.iniciar();
    }
}