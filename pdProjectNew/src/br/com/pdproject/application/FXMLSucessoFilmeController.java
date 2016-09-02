/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdproject.application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class FXMLSucessoFilmeController extends AbstractController implements Initializable {

    @FXML
    private Button btnVoltar;
    
    @FXML
    private Button btnMenuFilmes;
    
    public void voltarCadastroFilmes(){
        carregarPagina("FXMLInserirFilme.fxml");
    }
    
    public void carregarMenuFilme(){
        carregarPagina("FXMLFilmeCRUD.fxml");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
