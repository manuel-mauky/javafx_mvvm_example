package de.saxsys.todolist;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TodolistViewModel {
    
    
    private StringProperty newItemText = new SimpleStringProperty("");
    private ObservableList<String> items = FXCollections.observableArrayList();
    
    private ReadOnlyBooleanWrapper addButtonDisabled = new ReadOnlyBooleanWrapper();
    private ReadOnlyBooleanWrapper deleteButtonDisabled = new ReadOnlyBooleanWrapper();
    
    private IntegerProperty selectedIndex = new SimpleIntegerProperty();
    
    
    public TodolistViewModel() {
        addButtonDisabled.bind(newItemText.isEmpty());
        
        deleteButtonDisabled.bind(selectedIndex.isEqualTo(-1));
    }
    
    public void addItem() {
        String value = newItemText.get();
        
        if(value != null && !value.trim().isEmpty()) {
            items.add(value);
            
            newItemText.set("");
        }
    }
    
    public void deleteItem() {
        if(!items.isEmpty()) {
            int i = selectedIndex.get();
            items.remove(i);
        }
    }
    
    public StringProperty newItemTextProperty() {
        return newItemText;
    }
    
    public ObservableList<String> todoItems() {
        return items;
    }
    
    public ReadOnlyBooleanProperty addButtonDisabledProperty() {
        return addButtonDisabled.getReadOnlyProperty();
    }
    
    public ReadOnlyBooleanProperty deleteButtonDisabledProperty() {
        return deleteButtonDisabled.getReadOnlyProperty();
    }
    
    public IntegerProperty selectedIndex() {
        return selectedIndex;
    }
}
