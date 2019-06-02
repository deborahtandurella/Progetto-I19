package gui.utils;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class RefreshManager {

    public static void ordinazioniButton(JFXButton carrello){
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event1 ->
                carrello.setText(String.valueOf(ManagerOrdinazioni.getNumProdOrd()))),
                new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}
