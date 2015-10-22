package de.saxsys.todolist;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TodolistViewModelTest {
    
    private TodolistViewModel viewModel;
    
    @Before
    public void setup() {
        viewModel = new TodolistViewModel();
    }
    
    @Test
    public void addItemsToList() {
        // given 
        assertThat(viewModel.todoItems()).isEmpty();
        assertThat(viewModel.newItemTextProperty().get()).isEmpty();
        
        // when
        viewModel.newItemTextProperty().setValue("test my code");
        viewModel.addItem();
        
        // then
        assertThat(viewModel.todoItems()).contains("test my code");
        assertThat(viewModel.newItemTextProperty().get()).isEmpty();
    }
    
    @Test
    public void addButtonIsDisabledWhenTextfieldIsEmpty() {
        // given
        assertThat(viewModel.newItemTextProperty().get()).isEmpty();
        assertThat(viewModel.addButtonDisabledProperty().get()).isTrue();
        
        // when
        viewModel.newItemTextProperty().set("test my code");
        
        // then
        assertThat(viewModel.addButtonDisabledProperty().get()).isFalse();
        
        
        // when
        viewModel.newItemTextProperty().set("");
        
        
        // then
        assertThat(viewModel.addButtonDisabledProperty().get()).isTrue();
    }
    
    @Test
    public void selectedItemCanBeDeleted() {
        // given
        viewModel.newItemTextProperty().set("hello");
        viewModel.addItem();
        
        viewModel.newItemTextProperty().set("world");
        viewModel.addItem();
        
        assertThat(viewModel.todoItems()).contains("hello", "world");
        
        // when
        viewModel.selectedIndex().set(1);
        viewModel.deleteItem();
        
        // then
        assertThat(viewModel.todoItems()).containsOnly("hello");
    }
    
    
    @Test
    public void deleteButtonIsDisabledWhenNothingIsSelected() {
        // given
        viewModel.newItemTextProperty().set("hello");
        viewModel.addItem();

        viewModel.newItemTextProperty().set("world");
        viewModel.addItem();

        assertThat(viewModel.todoItems()).contains("hello", "world");
        
        // when
        viewModel.selectedIndex().set(-1);
        
        // then
        assertThat(viewModel.deleteButtonDisabledProperty().get()).isTrue();
    }
    
    @Test
    public void deleteButtonIsEnabledWhenAnItemIsSelected() {
        // given
        viewModel.newItemTextProperty().set("hello");
        viewModel.addItem();

        viewModel.newItemTextProperty().set("world");
        viewModel.addItem();

        assertThat(viewModel.todoItems()).contains("hello", "world");
        
        // when
        viewModel.selectedIndex().set(0);
        
        // then
        assertThat(viewModel.deleteButtonDisabledProperty().get()).isFalse();
        
    }
}
