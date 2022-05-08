package com.develogica.chapter_12;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HyperLinkDemo extends Application {
    TextField addressTextField = new TextField("https://google.com");
    WebView browser = new WebView();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage)  {

        var goButton = new Button("GO!");
        goButton.setOnAction(event -> loadPage());

        var menuButton = new MenuButton("Days of Week");
        menuButton.setPopupSide(Side.RIGHT);
        menuButton.getItems().addAll(populateMenu());

        var addressBar = new HBox(10, addressTextField, goButton, menuButton);

        var root = new VBox(10, addressBar, browser);

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("HyperLink Demo");
        stage.show();
    }

    private void loadPage() {
        browser.getEngine().load(addressTextField.getText().trim());
    }

    private List<MenuItem> populateMenu() {
        var days = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        var itemList = new ArrayList<MenuItem>();

        for (var day : days) {
            itemList.add(new MenuItem(day));
        }

        return itemList;
    }
}


