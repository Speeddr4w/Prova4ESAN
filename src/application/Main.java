package application;
	
import controller.CrudFuncionarioController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader =new FXMLLoader(Main.class.getResource("/view/ControllerFuncionario.fxml"));
	        AnchorPane pane = loader.load();
	        CrudFuncionarioController mainWindowController = loader.getController();
	        mainWindowController.setMain(this);
	        Scene scene = new Scene(pane);
	        primaryStage.setScene(scene);
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
