package de.saxsys.todolist;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TodolistApp extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		FXMLLoader fxmlLoader = new FXMLLoader(TodolistApp.class.getResource("/Todolist.fxml"));
		
		Parent root = fxmlLoader.load();
		
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
}
