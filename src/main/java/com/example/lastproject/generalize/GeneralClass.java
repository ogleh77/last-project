package com.example.lastproject.generalize;

import animatefx.animation.FadeIn;
import animatefx.animation.Shake;
import animatefx.animation.SlideInRight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class GeneralClass {
    final String MALE = "Male";
    final String FEMALE = "Female";
    private String ROLE = "";
    private String gender = "";
    private Shake shake;

    private final ObservableList<String> shift;
    private final ObservableList<String> paidBy;

    public GeneralClass() {
        shift = FXCollections.observableArrayList();
        paidBy = FXCollections.observableArrayList();
        shift.addAll("Morning", "Noon", "Afternoon", "Night");
        paidBy.addAll("eDahab", "Zaad service");
        shake = new Shake();
    }

    public String selected(RadioButton radio1, RadioButton radio2) {
        if (radio1.isSelected()) {
            gender = MALE;

        } else if (radio2.isSelected()) {
            gender = FEMALE;
        }
        return gender;
    }

    public boolean isPoxing(CheckBox checkBox) {
        if (checkBox.isSelected()) {
            return true;
        }
        return false;
    }

    protected FXMLLoader openWideWindow(String url, BorderPane borderPane, StackPane stack) throws IOException {
        if (stack.getChildren().size() > 1) stack.getChildren().remove(stack.getChildren().get(1));
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        AnchorPane anchorPane = loader.load();
//        if (borderPane.getCenter().getId().equals(anchorPane.getId())) {
//            System.out.println("Already opened.....");
//        }
        SlideInRight slideInRight = new SlideInRight(anchorPane);
        slideInRight.play();
        borderPane.setCenter(anchorPane);

        //Insha Allah laba jeer furanka ka jooji System.out.println(borderPane.getCenter().getId());
        return loader;
    }

    protected FXMLLoader openShortWindow(String url, StackPane stack) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        AnchorPane anchorPane = null;
        if (stack.getChildren().size() > 1) stack.getChildren().remove(stack.getChildren().get(1));
        anchorPane = loader.load();
//        if (stack.getChildren().get(0).getId().equals(anchorPane.getId())) {
//            System.out.println("Already opened..");
//        }
        new FadeIn(anchorPane).play();
        stack.getChildren().add(anchorPane);
        return loader;
    }

    protected FXMLLoader openTabPane(String url, StackPane stack) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        TabPane tabPane = null;
        if (stack.getChildren().size() > 1) {
            stack.getChildren().remove(stack.getChildren().get(1));
        }
        tabPane = loader.load();
        //new FadeIn(anchorPane).play();
        stack.getChildren().add(tabPane);
        return loader;
    }

    //----------------------Form validation----------------------

    public boolean isValid(ObservableList<Control> mandotoryFileds, ToggleGroup group) {
        boolean isValid = true;
        try {

            for (Control control : mandotoryFileds) {
                if (control instanceof TextInputControl) {
                    if ((((TextInputControl) control).getText().isBlank() || ((TextInputControl) control).getText().isEmpty())) {
                        shake.setNode(control);
                        control.setStyle("-fx-border-color: #cb3d3d;-fx-border-width: 2");
                        shake.play();
                        isValid = false;
                    } else {
                        control.setStyle(null);
                    }
                } else if (control instanceof ComboBoxBase<?> && (((ComboBoxBase<?>) control).getValue() == null)) {
                    shake.setNode(control);
                    shake.play();
                    control.setStyle("-fx-border-color: #cb3d3d;-fx-border-width: 2");
                    isValid = false;
                } else {
                    control.setStyle(null);
                }
            }
            if (group.getSelectedToggle() == null) {
                shake.setNode((Node) group.getToggles().get(0));
                shake.play();
                shake.setNode((Node) group.getToggles().get(1));
                ((Node) group.getToggles().get(0)).setStyle("-fx-border-color: #cb3d3d;-fx-border-width: 2");
                ((Node) group.getToggles().get(1)).setStyle("-fx-border-color: #cb3d3d;-fx-border-width: 2");
                shake.play();

                isValid = false;
            } else {
                ((Node) group.getToggles().get(0)).setStyle(null);
                ((Node) group.getToggles().get(1)).setStyle(null);
            }

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }


        return isValid;
    }


    public String role(RadioButton superAdmin, RadioButton admin, RadioButton user) {
        if (superAdmin.isSelected()) {
            return ROLE = "SuperAdmin";
        } else if (admin.isSelected()) {
            return ROLE = "Admin";
        } else {
            return ROLE = "User";
        }
    }

    public ObservableList<String> getShift() {
        return shift;
    }

    public ObservableList<String> getPaidBy() {
        return paidBy;
    }
}
