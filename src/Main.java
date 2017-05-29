import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.Controller;

public class Main extends Application
{
	public static void main(String[] args) throws Exception
	{
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("ui/main.fxml"));
		primaryStage.setTitle("嵌入式SQL演示程序");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
}
