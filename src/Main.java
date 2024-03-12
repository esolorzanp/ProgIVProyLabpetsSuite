import control.EspecieControl;
import dao.EspecieDao;
import model.Especie;
import view.EspecieView;

public class Main {
    public static void main(String[] args) {
        Especie model = new Especie();
        EspecieDao dao = new EspecieDao();
        EspecieView view = new EspecieView();
        EspecieControl control = new EspecieControl(model, dao, view);
        control.iniciar();
    }
}