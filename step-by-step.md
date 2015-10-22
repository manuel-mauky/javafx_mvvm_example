# Todolist Beispiel:

## Feature: Items hinzufügen

- fxml-Datei mit Scenebuilder bauen
- Starter-Klasse bauen, die die FXML lädt und anzeigt.
- ViewModel schnittstelle festlegen (Properties und Action-Methoden)
	- `void addItem()`
	- `StringProperty newItemText()`
	- `ObservableList<String> todoItems()`
- ViewModel Test anlegen
- TDD addItem implementieren
	- Test
	```java
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
	```

	- Properties anlegen und per Getter nach außen geben
	- addItem implementieren


- View-Klasse anlegen und mit fxml verknüpfen (Controller)
- ViewModel mit View binden

## Feature: Add-Button nur Enabled wenn Textfeld != leer.
- neue Properties hinzufügen
- Test

```java
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
```

- Implementierung
```java
	public TodolistViewModel() {
		addButtonDisabled.bind(newItemText.isEmpty());
	}
```
- Binding herstellen
- Ausprobieren

## Feature: Selektiertes Item löschen
- FXML erweitern
- neue Properties
- Test
```java
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
```

- Implementierung
```java
public void deleteItem() {
    if(!items.isEmpty()) {
        int i = selectedIndex.get();
        items.remove(i);
    }
}
```

- Binding herstellen
- Ausprobieren

## Feature: Kleinere Verbesserungen der Usability
- Nach hinzufügen wird wieder Textfeld fokussiert -> nicht im ViewModel sondern direkt in der View
- ENTER fügt ebenfalls ein neues Item hinzu wenn Textfeld fokussiert ist
	`newItemText.setOnAction(e -> viewModel.addItem());`
- Problem: Leere Items können plötzlich hinzugefügt werden. Wurde vorher nicht abgeprüft, da der Button ja disabled war.
-> Daher: TDD um Problem zu fixen
- Test

```java
@Test
public void emptyItemCannotBeAdded() {
    // given
    viewModel.newItemTextProperty().set("");


    // when
    viewModel.addItem();

    // then
    assertThat(viewModel.todoItems()).doesNotContain("").isEmpty();
}
```

- Implementierung

```java
public void addItem() {
    String value = newItemText.get();

    if(value != null && !value.trim().isEmpty()) {
        items.add(value);

        newItemText.set("");
    }
}
```
