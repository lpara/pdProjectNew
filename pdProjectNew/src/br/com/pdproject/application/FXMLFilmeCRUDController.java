/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdproject.application;

import com.br.pdproject.dao.FilmeDAO;
import com.br.pdproject.dominio.Filme;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class FXMLFilmeCRUDController implements Initializable {

    @FXML
    private Button btnInserirFilme;
    
    @FXML
    private Button btnRemoverFilme;
    
    @FXML
    private Button btnListarFilme;
    
    @FXML
    private Button btnAtualizarFilme;
    
    @FXML
    private Button btnListarPorCategoriaFilme;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void inserir(ActionEvent event){
        FilmeDAO filmDao = new FilmeDAO();
        Filme filme = new Filme();
//        filme.setTitulo(titulo);
//        filme.setDescricao(descricao);
        
        filmDao.inserirFilme(filme);
    }
    
}
