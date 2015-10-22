package de.saxsys.todolist;

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
    @FXML
    public Button addButton;
    @FXML
    public Button deleteButton;

    private TodolistViewModel viewModel = new TodolistViewModel();
    
    public void initialize() {
        todoListview.setItems(viewModel.todoItems());
        viewModel.selectedIndex().bind(todoListview.getSelectionModel().selectedIndexProperty());
     
        newItemText.textProperty().bindBidirectional(viewModel.newItemTextProperty());
        
        addButton.disableProperty().bind(viewModel.addButtonDisabledProperty());
        deleteButton.disableProperty().bind(viewModel.deleteButtonDisabledProperty());
    }
    
    public void add() {
        viewModel.addItem();
    }

    public void delete() {
        viewModel.deleteItem();
    }
}
