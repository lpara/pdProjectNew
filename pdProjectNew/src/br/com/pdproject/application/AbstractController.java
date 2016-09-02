/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdproject.application;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author LUAN
 */
public abstract class AbstractController {
    
    public void setColumn(TableColumn col, String atributo){
        col.setCellValueFactory(new PropertyValueFactory<>(atributo));
    }
    
    public void carregarPagina(String pagina){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(pagina));
            Stage stage = new Stage();
            stage.setTitle("Ações de Filmes");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
