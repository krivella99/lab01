

import Controller.StatisticsController;
import Model.StatisticsModel;
import View.StatisticsView;
import Model.DataHandler;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //создаем
        StatisticsModel model = new StatisticsModel();
        StatisticsView view = new StatisticsView();
        DataHandler dataHandler = new DataHandler();
        StatisticsController controller = new StatisticsController(model, view, dataHandler);

        //показывать картинку
        view.display();
    }
}