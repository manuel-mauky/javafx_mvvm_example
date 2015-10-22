package de.saxsys.todolist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

public class TodolistView {

    @FXML
    public TextField newItemText;
    @FXML
    public ListView<String> todoListview;

    private TodolistViewModel viewModel = new TodolistViewModel();
    
    public void initialize() {
        todoListview.setItems(viewModel.todoItems());
        newItemText.textProperty().bindBidirectional(viewModel.newItemTextProperty());
    }
    
    public void add() {
        viewModel.addItem();
    }
}
