package com.aafv.screen.components;

import com.aafv.Connection;
import com.aafv.screen.Theme;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.regex.Pattern;

public class PanelOperaciones extends JPanel {

    // Acepta enteros y decimales, con signo opcional: 5, -3, 2.5
    private static final Pattern NUMERO = Pattern.compile("-?\\d+(\\.\\d+)?");

    private final TextField txtA = new TextField("A");
    private final TextField txtB = new TextField("B");
    private final JLabel lblResultado = new JLabel(" ");

    public PanelOperaciones(Connection motor) {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Operaciones básicas");
        titulo.setFont(Theme.sans(Font.BOLD, 12));
        titulo.setForeground(Theme.MUTED);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtA.setColumns(8);
        txtB.setColumns(8);

        Buttons btnSumar = new Buttons("Sumar", Theme.BLUE, Theme.BG, 20, 11, 14);
        Buttons btnRestar = new Buttons("Restar", Theme.GREEN, Theme.BG, 20, 11, 14);
        btnSumar.addActionListener(e -> ejecutar(motor, "suma", "+"));
        btnRestar.addActionListener(e -> ejecutar(motor, "resta", "-"));

        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        fila.setOpaque(false);
        fila.setAlignmentX(Component.LEFT_ALIGNMENT);
        fila.add(txtA);
        fila.add(txtB);
        fila.add(btnSumar);
        fila.add(btnRestar);

        lblResultado.setFont(Theme.mono(Font.PLAIN, 17));
        lblResultado.setForeground(Theme.TEXT);
        lblResultado.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(titulo);
        add(javax.swing.Box.createVerticalStrut(6));
        add(fila);
        add(javax.swing.Box.createVerticalStrut(10));
        add(lblResultado);
    }

    private void ejecutar(Connection motor, String pred, String simbolo) {
        String a = txtA.getText().trim();
        String b = txtB.getText().trim();

        if (!NUMERO.matcher(a).matches() || !NUMERO.matcher(b).matches()) {
            mostrar("Escribí dos números válidos.", Theme.AMBER);
            return;
        }
        try {
            String r = motor.operar(pred, a, b);
            mostrar(a + " " + simbolo + " " + b + " = " + r, Theme.GREEN);
        } catch (Exception ex) {
            mostrar("Error: " + ex.getMessage(), Theme.RED);
        }
    }

    private void mostrar(String texto, Color color) {
        lblResultado.setText(texto);
        lblResultado.setForeground(color);
    }
}
