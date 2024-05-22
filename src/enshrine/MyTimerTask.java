/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enshrine;

import controllers.MapDisplayController;
import java.util.TimerTask;
import javafx.application.Platform;

/**
 *
 * @author dc_ca
 */
public class MyTimerTask extends TimerTask {
    private static int fi=0;

    @Override
    public void run() {
        //System.out.println(fi);
        //fi++;
        Platform.runLater(() -> {
            MapDisplayController.attemptUpdateAll();
        });
    }
}
