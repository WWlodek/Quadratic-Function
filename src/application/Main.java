package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 1200, 700);
			
			primaryStage.setTitle("Quadratic Function Drawer");
			// Add Elements to scene ================
			HBox hbox1 = new HBox(8);
			hbox1.setId("hbox1");
			hbox1.setMinHeight(50);

			Label labelA = new Label("Parameter a:");
			labelA.setId("label");
			Label labelB = new Label("Parameter b:");
			labelB.setId("label");
			Label labelC = new Label("Parameter c:");
			labelC.setId("label");

			TextField txtA = new TextField();
			txtA.setId("txt");
			TextField txtB = new TextField();
			txtB.setId("txt");
			TextField txtC = new TextField();
			txtC.setId("txt");

			Button but1 = new Button();
			but1.setId("button");
			but1.setText("Draw!");
			but1.setMinWidth(90);
			Button but2 = new Button();
			but2.setId("button");
			but2.setText("Clear");
			but2.setMinWidth(90);

			hbox1.getChildren().add(labelA);
			hbox1.getChildren().add(txtA);
			hbox1.getChildren().add(labelB);
			hbox1.getChildren().add(txtB);
			hbox1.getChildren().add(labelC);
			hbox1.getChildren().add(txtC);
			hbox1.getChildren().add(but1);
			hbox1.getChildren().add(but2);
			hbox1.setAlignment(Pos.CENTER);
			root.setTop(hbox1);

			// Canvas ================
			Canvas can = new Canvas(1196, 645);
			can.setId("can");
			
			// Drawing on Canvas ======
			GraphicsContext gc = can.getGraphicsContext2D();

			// Main Y line ============
			gc.setStroke(Color.GOLD);
			gc.setLineWidth(3);
			gc.strokeLine(can.getWidth() / 2, 0, can.getWidth() / 2, can.getHeight()); 
			
			// Draw Y arrow ===========
			gc.strokeLine(can.getWidth() / 2, 0, can.getWidth() / 2 - 10, 10); 												
			gc.strokeLine(can.getWidth() / 2, 0, can.getWidth() / 2 + 10, 10);
			gc.fillText("Y", can.getWidth() / 2 + 20, 15);

			// Main X line ============
			gc.setStroke(Color.GOLD);
			gc.setLineWidth(3);
			gc.strokeLine(0, can.getHeight() / 2, can.getWidth(), can.getHeight() / 2);
																						
			// Draw X arrow ============
			gc.strokeLine(can.getWidth(), can.getHeight() / 2, can.getWidth() - 10, can.getHeight() / 2 - 10);																									// X
			gc.strokeLine(can.getWidth(), can.getHeight() / 2, can.getWidth() - 10, can.getHeight() / 2 + 10);
			gc.fillText("X", can.getWidth() - 15, can.getHeight() / 2 - 15);

			for (int i = 0; i < can.getWidth(); i += 100) {

				// X lines ============
				gc.setStroke(Color.BISQUE);
				gc.setLineWidth(1);
				gc.strokeLine(0 + i, 0, 0 + i, can.getHeight());
				gc.fillText(String.valueOf((i / 100) - 6), 0 + i - 5, can.getHeight() / 2 - 5); // cyfry

			}

			for (int i = 23; i < can.getHeight(); i += 100) {

				// Y lines ============
				gc.setStroke(Color.BISQUE);
				gc.setLineWidth(1);
				gc.strokeLine(0, 0 + i, can.getWidth(), 0 + i);
				gc.fillText(String.valueOf((-i - 23) / 100 + 3), can.getWidth() / 2 + 5, 0 + i + 3); // cyfry

			}

			// Draw Button Event ==========		
			draw(txtA, txtB, txtC, but1, can, gc);

			// Clear Button Event =========
			clear(but2, can, gc);
			
			// Add Canvas to scene ========
			HBox hbox2 = new HBox(1);
			hbox2.setId("hbox2");
			hbox2.getChildren().add(can);
			root.setCenter(hbox2);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clear(Button but2, Canvas can, GraphicsContext gc) {
		but2.setOnAction(event -> {
			// Clear Canvas
			gc.clearRect(0, 0, can.getWidth(), can.getHeight());
			// Draw grids
			gc.setStroke(Color.GOLD);
			gc.setLineWidth(3);
			gc.strokeLine(can.getWidth() / 2, 0, can.getWidth() / 2, can.getHeight());
			gc.strokeLine(0, can.getHeight() / 2, can.getWidth(), can.getHeight() / 2);
			gc.strokeLine(can.getWidth() / 2, 0, can.getWidth() / 2 - 10, 10); 												
			gc.strokeLine(can.getWidth() / 2, 0, can.getWidth() / 2 + 10, 10);
			gc.fillText("Y", can.getWidth() / 2 + 20, 15);
			gc.strokeLine(can.getWidth(), can.getHeight() / 2, can.getWidth() - 10, can.getHeight() / 2 - 10);																									// X
			gc.strokeLine(can.getWidth(), can.getHeight() / 2, can.getWidth() - 10, can.getHeight() / 2 + 10);
			gc.fillText("X", can.getWidth() - 15, can.getHeight() / 2 - 15);

			gc.setStroke(Color.BISQUE);
			gc.setLineWidth(1);

			for (int i = 0; i < can.getWidth(); i += 100) {

				gc.strokeLine(0 + i, 0, 0 + i, can.getHeight());
				gc.fillText(String.valueOf((i / 100) - 6), 0 + i - 5, can.getHeight() / 2 - 5);

			}

			for (int i = 23; i < can.getHeight(); i += 100) {

				gc.strokeLine(0, 0 + i, can.getWidth(), 0 + i);
				gc.fillText(String.valueOf((-i - 23) / 100 + 3), can.getWidth() / 2 + 5, 0 + i + 3);

			}
		});
	}

	public void draw(TextField txtA, TextField txtB, TextField txtC, Button but1, Canvas can, GraphicsContext gc) {
		but1.setOnAction(event -> {

			String textA = txtA.getText();
			double a = Double.parseDouble(textA);

			String textB = txtB.getText();
			double b = Double.parseDouble(textB);

			String textC = txtC.getText();
			double c = Double.parseDouble(textC);

			for (int j = 0; j < 10000; j++) {
				// Function - random positive X's and computing Y's
				double x1 = Math.random() * 10;
				double y1 = -((x1 * x1) * a + b * x1 + c);
				double x = (x1 * 100) + can.getWidth() / 2;
				double y = ((y1 * 100) + can.getHeight() / 2);
				// Draw positive function points
				gc.setStroke(Color.RED);
				gc.setLineWidth(4);
				gc.strokeLine(x, y, x, y);
				
				// Function - random negative X's and computing Y's
				double minx1 = -(Math.random() * 10);
				double miny1 = -((minx1 * minx1) * a + b * minx1 + c);
				double minx = (minx1 * 100) + can.getWidth() / 2;
				double miny = ((miny1 * 100) + can.getHeight() / 2);
				// Draw negative function points
				gc.setStroke(Color.BLUE);
				gc.setLineWidth(4);
				gc.strokeLine(minx, miny, minx, miny);
			
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
