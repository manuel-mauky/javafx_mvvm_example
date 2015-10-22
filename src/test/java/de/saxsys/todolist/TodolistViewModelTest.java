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
}
