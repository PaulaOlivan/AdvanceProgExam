package gr.uop;

import java.util.Optional;
import java.util.concurrent.Flow;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane mainPane = new BorderPane();
        int textWidth = 100;
        int buttonWidth = textWidth / 2;
        int sliderWidth = 170;
        String tempText = "Temperature (";
        
        //Settings Pane
        FlowPane settings = new FlowPane();
        
        VBox startBox = new VBox();
        Label startLabel = new Label("Start");
        TimeDisplay defaultStart = new TimeDisplay(10, 00);
        TextField startText = new TextField(defaultStart.toString());
        startText.setAlignment(Pos.CENTER);
        startText.setMaxWidth(textWidth);
        startText.setDisable(true);
        HBox startButtonPane = new HBox();
        Button startPlus = new Button("+");
        Button startMinus = new Button("-");
        startPlus.setPrefWidth(buttonWidth);
        startMinus.setPrefWidth(buttonWidth);
        startButtonPane.getChildren().addAll(startPlus, startMinus);
        startButtonPane.setAlignment(Pos.CENTER);
        startBox.getChildren().addAll(startLabel, startText, startButtonPane);
        startBox.setAlignment(Pos.CENTER);
        
        VBox stopBox = new VBox();
        Label stopLabel = new Label("Stop");
        TimeDisplay defaultStop = new TimeDisplay(11, 00);
        TextField stopText = new TextField(defaultStop.toString());
        stopText.setAlignment(Pos.CENTER);
        stopText.setMaxWidth(textWidth);
        stopText.setDisable(true);
        HBox stopButtonPane = new HBox();
        Button stopPlus = new Button("+");
        Button stopMinus = new Button("-");
        stopPlus.setPrefWidth(buttonWidth);
        stopMinus.setPrefWidth(buttonWidth);
        stopButtonPane.getChildren().addAll(stopPlus, stopMinus);
        stopButtonPane.setAlignment(Pos.CENTER);
        stopBox.getChildren().addAll(stopLabel, stopText, stopButtonPane);
        stopBox.setAlignment(Pos.CENTER);

        VBox temperatureBox = new VBox();
        Slider tempSlider = new Slider(14, 26, 20);
        int value = ((int)tempSlider.getValue());
        Label tempLabel = new Label(tempText + value + ")");
        tempLabel.setAlignment(Pos.CENTER);
        tempSlider.setMajorTickUnit(2); // Labels
        tempSlider.setMinorTickCount(1);
        tempSlider.setBlockIncrement(1);
        tempSlider.setSnapToTicks(true);
        tempSlider.setShowTickMarks(true);
        tempSlider.setShowTickLabels(true);
        tempSlider.setPrefWidth(sliderWidth);
        temperatureBox.getChildren().addAll(tempLabel, tempSlider);
        temperatureBox.setAlignment(Pos.CENTER);
        Button saveAs = new Button("Save as...");

        settings.getChildren().addAll(startBox, stopBox, temperatureBox, saveAs);
        settings.setAlignment(Pos.TOP_CENTER);
        int marginElements = 20;
        int marginBorder = 20;
        settings.setMargin(startBox, new Insets(marginBorder, marginElements, marginElements, marginBorder));
        settings.setMargin(stopBox, new Insets(marginBorder, marginElements, marginElements, marginElements));
        settings.setMargin(temperatureBox, new Insets(marginBorder, marginElements, marginElements, marginElements));
        settings.setMargin(saveAs, new Insets(marginBorder, marginBorder, marginBorder, marginBorder));

        //Profiles Pane
        FlowPane profiles = new FlowPane();
        ObservableList<Profile> profilesSave = FXCollections.observableArrayList();
        ComboBox<Profile> comboProfile = new ComboBox<Profile>(profilesSave);
        Button activate = new Button("Activate");
        Button deactivate = new Button("Deactivate");
        Button delete = new Button("Delete");

        profiles.getChildren().addAll(comboProfile, activate, deactivate, delete);
        profiles.setAlignment(Pos.TOP_CENTER);
        int distanceElements = 5;
        int distanceVertically = 20;
        int distanceBorder = 20; 
        profiles.setMargin(comboProfile, new Insets(distanceBorder, distanceElements, distanceVertically, distanceBorder));
        profiles.setMargin(activate, new Insets(distanceBorder, distanceElements, distanceVertically, distanceElements));
        profiles.setMargin(deactivate, new Insets(distanceBorder, distanceElements, distanceBorder, distanceElements));
        profiles.setMargin(delete, new Insets(distanceBorder, distanceBorder, distanceBorder, distanceElements));
        comboProfile.setMinWidth(textWidth+20);
        comboProfile.setMaxWidth(textWidth+20);
        comboProfile.setMinHeight(comboProfile.getPrefHeight());
        comboProfile.setMaxHeight(comboProfile.getPrefHeight());
        activate.setMaxSize(activate.getPrefWidth(), activate.getPrefHeight());
        activate.setMinSize(activate.getPrefWidth(), activate.getPrefHeight());
        deactivate.setMaxSize(deactivate.getPrefWidth(), deactivate.getPrefHeight());
        deactivate.setMinSize(deactivate.getPrefWidth(), deactivate.getPrefHeight());
        delete.setMaxSize(delete.getPrefWidth(), delete.getPrefHeight());
        delete.setMinSize(delete.getPrefWidth(), delete.getPrefHeight());

        // Tabs for the controller
        TabPane tabsPane = new TabPane();
        Tab tab1 = new Tab("Settings", settings);
        tab1.setClosable(false);
        Tab tab2 = new Tab("Profiles"  , profiles);
        tab2.setClosable(false);
        tabsPane.getTabs().add(tab1);
        tabsPane.getTabs().add(tab2);
        
        //File Menu
        MenuItem MenuSettings = new MenuItem("Settings");
        MenuItem MenuProfile = new MenuItem("Profile");
        SeparatorMenuItem separator = new SeparatorMenuItem();
        MenuItem MenuExit = new MenuItem("Exit");
        Menu menu = new Menu("File");
        menu.getItems().addAll(MenuSettings, MenuProfile, separator, MenuExit);
        MenuBar menuBar = new MenuBar(menu);

        // Main Pane
        mainPane.setTop(menuBar);
        mainPane.setCenter(tabsPane);

        int minWidth = 300;
        int minHeight = 350;
        int prefWidth = 600;
        int prefHeight = 350;
        var scene = new Scene(mainPane, prefWidth, prefHeight);
        stage.setScene(scene);
        stage.setTitle("Controller");
        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        stage.show();

        /* Functionality of the Window */

        // Function to show Settings tab when the File -> Settings is click
        MenuSettings.setOnAction(e -> {
            tabsPane.getSelectionModel().select(tab1);
        });

        //Function to show Profiles tab when the File -> Profile is click
        MenuProfile.setOnAction(e -> {
            tabsPane.getSelectionModel().select(tab2);
        });

        // Function to close the Controller if the user press File -> Exit
        MenuExit.setOnAction(e -> {
            stage.close();
        });


        // Function to augment the time on the start section
        startPlus.setOnAction(e -> {
            String previousTime = startText.getText().trim();
            TimeDisplay time = new TimeDisplay(previousTime);
            time.tickUp();
            startText.setText(time.toString());
        });

        // Function to reduce the time on the start section
        startMinus.setOnAction(e -> {
            String previousTime = startText.getText().trim();
            TimeDisplay time = new TimeDisplay(previousTime);
            time.tickDown();
            startText.setText(time.toString());
        });

        // Function to augment the time on the stop section
        stopPlus.setOnAction(e -> {
            String previousTime = stopText.getText().trim();
            TimeDisplay time = new TimeDisplay(previousTime);
            time.tickUp();
            stopText.setText(time.toString());
        });

        // Function to reduce the time on the stop section
        stopMinus.setOnAction(e -> {
            String previousTime = stopText.getText().trim();
            TimeDisplay time = new TimeDisplay(previousTime);
            time.tickDown();
            stopText.setText(time.toString());
        });

        // Function to modify the Temperature Label (when you click somewhere in the slider)
        tempSlider.setOnMouseClicked(e -> {
            int temp = ((int)tempSlider.getValue());
            tempLabel.setText(tempText + temp + ")");
        });

        // Function to modify the Temperature Label (when you change temperature with arrows)
        tempSlider.setOnKeyPressed(e -> {
            int temp = ((int)tempSlider.getValue());
            tempLabel.setText(tempText + temp + ")");
        });

        //Function to modify the Temperature Label (when you drag the mouse)
        tempSlider.setOnMouseDragged(e -> {
            int temp = ((int)tempSlider.getValue());
            tempLabel.setText(tempText + temp + ")");
        });

        // Function to display the TextInputDialog to save a new profile
        saveAs.setOnAction(e -> {
            TextInputDialog addName = new TextInputDialog();
            addName.setTitle("Save new profile");
            addName.setHeaderText(null);
            addName.setContentText("Please enter a name for the profile:");
            addName.initOwner(stage);

            // Create the listener for the textfield
            addName.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.isEmpty()) { // Nothing change
                    
                } 
                else { // If the text field is not empty, show OK only if the name doesnt exist
                    for (Profile item : profilesSave) {
                        if (item.getName().contains(newValue)) {
                            addName.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
                        }
                        else{
                            addName.getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
                        }
                    }                    
                }
            });

            Optional<String> result = addName.showAndWait();

            if (result.isPresent()){
                String name = result.get()+"";
                System.out.println("The new profile must been save with name " + name);
                TimeDisplay startTime = new TimeDisplay(startText.getText().trim());
                TimeDisplay stopTime = new TimeDisplay(stopText.getText().trim());
                double temperature = tempSlider.getValue();
                Profile newProfile = new Profile(name, startTime, stopTime, temperature);
                profilesSave.add(newProfile);
            }
            else{
                System.out.println("Save of a new profile has been canceled");

            }

        });

        // Function to activate a profile selected from the combobox
        activate.setOnAction(e -> {
            Profile selectedProfile = comboProfile.getSelectionModel().getSelectedItem();
            selectedProfile.setActive(true); // Change active to true to active the profile
            activate.setDisable(true); // Change the availability of each button
            deactivate.setDisable(false);
            System.out.println("Profile:" + selectedProfile + "has change active to " + selectedProfile.isActive());
        });

        // Function to desactivate a profile selected from the combobox
        deactivate.setOnAction(e -> {
            Profile selectedProfile = comboProfile.getSelectionModel().getSelectedItem();
            selectedProfile.setActive(false); // Change active to false to deactivate the profile
            activate.setDisable(false);
            deactivate.setDisable(true);
            System.out.println("Profile:" + selectedProfile + "has change active to " + selectedProfile.isActive());
        });

        // Function to delete a profile selected from the combobox
        delete.setOnAction(e -> {
            Alert deleteAlert = new Alert(AlertType.WARNING);
            deleteAlert.setTitle("WARNING!");
            deleteAlert.setHeaderText(null);
            deleteAlert.setContentText("Are you sure you want to delete the selected profile?");

            deleteAlert.initModality(Modality.WINDOW_MODAL);
            deleteAlert.getButtonTypes().setAll(ButtonType.APPLY, ButtonType.CANCEL);
            deleteAlert.initOwner(stage);
            
            Optional<ButtonType> result = deleteAlert.showAndWait();
            if(result.get() == ButtonType.APPLY){
                Profile selectedProfile = comboProfile.getSelectionModel().getSelectedItem();
                if (selectedProfile != null){ // If there is a selected profile we can delete it
                    profilesSave.remove(selectedProfile);
                }
                // After the delete we select the next profile if there are at least one
                comboProfile.getSelectionModel().selectNext();
                
            }
            else if (result.get() == ButtonType.CANCEL){
                System.out.println("The delete profile action has been cancelled");
            }
        });

        //Function to desactive all buttons if there is no profile selected on the combobox
        activate.setDisable(true);
        delete.setDisable(true);
        deactivate.setDisable(true);
        comboProfile.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) { // There is  no profile selected so disable all buttons
                activate.setDisable(true);
                deactivate.setDisable(true);
                delete.setDisable(true);
            }
            else{ // Some profile is selected
                delete.setDisable(false); // Always active
                Profile selectedProfile = comboProfile.getSelectionModel().getSelectedItem();
                if (selectedProfile.isActive()){ // If the profile is active we disable the activate button
                    activate.setDisable(true);
                    deactivate.setDisable(false);
                }
                else{ // If the profile is desactive we disable the deactivate button
                    activate.setDisable(false);
                    deactivate.setDisable(true);
                }
            }
        }); 
    }

    public static void main(String[] args) {
        launch(args);
    }

}