package Module;

import Scenes.Control;
import javafx.application.Application;

public class MainClass {
    public static void main(String[] args) {

        DataBase.putDataInLists();
        Application.launch(Control.class, args);

    }
}
