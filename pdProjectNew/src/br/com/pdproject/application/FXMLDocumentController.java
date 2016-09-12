/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdproject.application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Lucas
 */
public class FXMLDocumentController extends AbstractController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private Button btnCRUDFilme;
    
    @FXML
    private Button btnCRUDCategoria;
    
    @FXML
    private void redirecionarInserirFilmes(ActionEvent event) {
         try {
             stagePrincipal.setTitle("Menu Filme");
            carregarPagina("FXMLFilmeMenu.fxml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void redirecionarCategoria(ActionEvent event) {
         try {
             stagePrincipal.setTitle("Menu Categoria");
            carregarPagina("FXMLMenuCategoria.fxml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void redirecionarCliente(ActionEvent event) {
        try {
            stagePrincipal.setTitle("Cliente");
            carregarPagina("ClienteFXML.fxml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void redirecionarFuncionario(ActionEvent event) {
        try {
            stagePrincipal.setTitle("Funcionário");
            carregarPagina("FuncionarioFXML.fxml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
