import mainview.MainView;
import mainview.MainViewController;
import service.DataAccessObject;

public class Main {

    public static void main(String[] args) {
            // Model
            DataAccessObject dao = new DataAccessObject();
            // View
            MainView view = new MainView();
            // Controller
            new MainViewController(view, dao); // dependency injection (view und dao wird vorgegeben)
    }
}