package gui.cliente.utils;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock {

    public static void initClock(Label time){
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event1 -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            time.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.minutes(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

}
