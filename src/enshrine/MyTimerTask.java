/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enshrine;

import controllers.MapDisplayController;
import java.util.TimerTask;

/**
 *
 * @author dc_ca
 */
public class MyTimerTask extends TimerTask {

    @Override
    public void run() {
        MapDisplayController.attemptUpdateAll();
    }
}
