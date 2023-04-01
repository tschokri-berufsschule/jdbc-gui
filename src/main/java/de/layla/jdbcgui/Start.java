package de.layla.jdbcgui;

import de.layla.jdbcgui.mainWindow.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Start extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new MainWindow(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}