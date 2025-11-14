package application;

import application.controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        GameController controller = new GameController();
        Scene scene = new Scene(controller.getRoot(), 380, 720);

        // Cargar CSS desde resources en el classpath
        String css = MainApp.class.getResource("/application/resources/style.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("Flip & Match");
        stage.setScene(scene);
        
        stage.setResizable(false);      // Desactiva redimensionar y el botón de maximizar
        stage.setMaximized(false);      // Por si el SO intenta abrir maximizado
        stage.setFullScreen(false);     // Evitar pantalla completa (F11)
        
        // Opcional: fijar límites exactos por si tu SO ignora algo
        
        stage.setMinWidth(380);
        stage.setMaxWidth(380);
        stage.setMinHeight(720);
        stage.setMaxHeight(720);
        
        stage.show();

        controller.startNewGame("Animals"); // mazo por defecto
    }

    public static void main(String[] args) {
        launch(args);
    }
}

