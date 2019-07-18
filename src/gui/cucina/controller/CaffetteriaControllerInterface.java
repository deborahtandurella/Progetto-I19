package gui.cucina.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;

public interface CaffetteriaControllerInterface {
    public VBox loadProdottiOrdinati();
    public VBox loadProdottiTemp();
    public void refresh(VBox vBox);
    public void setPronto(ActionEvent event);
}
