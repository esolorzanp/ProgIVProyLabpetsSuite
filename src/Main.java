import control.*;
import dao.*;
import model.*;
import view.*;

public class Main {
    public static void main(String[] args) {
        Usuario model = new Usuario();
        UsuarioDao dao = new UsuarioDao();
        LoginFrm vista = new LoginFrm();
        LoginCtrl control = new LoginCtrl(model,dao,vista);
        control.iniciar();
    }
}