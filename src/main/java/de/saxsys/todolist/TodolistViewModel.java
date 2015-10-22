package de.saxsys.todolist;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TodolistViewModel {
    
    
    private StringProperty newItemText = new SimpleStringProperty("");
    private ObservableList<String> items = FXCollections.observableArrayList();
    
    
    public void addItem() {
        items.add(newItemText.get());
        
        newItemText.set("");
    }
    
    public StringProperty newItemTextProperty() {
        return newItemText;
    }
    
    public ObservableList<String> todoItems() {
        return items;
    }
    
}
