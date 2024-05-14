package com.empresa.javafx_sp;
import java.sql.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.DriverManager;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField txt_nombre;
    @FXML
    private TextField txt_ciudad;
    @FXML
    private TextField txt_facturacion;

    @FXML
    protected void onHelloButtonClick() {
       // welcomeText.setText("Welcome to JavaFX Application!");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/clientes";
            Connection conn= DriverManager.getConnection(url,"root","");
            String sp="{CALL `st_add_clientes`(?, ?, ?)}";
            CallableStatement cs=conn.prepareCall(sp);
            cs.setString(1,txt_nombre.getText());
            cs.setString(2,txt_ciudad.getText());
            cs.setString(3,txt_facturacion.getText());
            cs.executeUpdate();
            welcomeText.setText("Cliente guardado");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}