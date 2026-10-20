package com.aafv;

import javax.swing.SwingUtilities;
import com.aafv.screen.Window;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Connection motor = new Connection();
        SwingUtilities.invokeLater(() -> new Window(motor).setVisible(true));
    }
}