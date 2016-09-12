/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdproject.application;

import com.br.pdproject.dao.CategoriaDAO;
import com.br.pdproject.dao.FilmeDAO;
import com.br.pdproject.dominio.Categoria;
import com.br.pdproject.dominio.Filme;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class FXMLAtualizarCategoriaController extends AbstractController implements Initializable {

    @FXML
    private TextField tfNomeAttCategoria;
    
    @FXML
    private Button btnAtualizarCategoria;
    
    @FXML
    private Button btnAVoltar;
    
    private Categoria categoria;
    
    public ObservableList<Categoria> listaCategoria = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoria = (Categoria) stagePrincipal.getUserData();
        
        stagePrincipal.setUserData(null);
        
        tfNomeAttCategoria.setText(categoria.getNome());
    }

    public void atualizar() {
        categoria.setNome(tfNomeAttCategoria.getText());
        CategoriaDAO catDao = new CategoriaDAO();
        
        try {
            catDao.atualizarCategoria(categoria);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
       
        listar();
        
        carregarPagina("FXMLMenuCategoria.fxml");
        
    } 
    
    public void voltarMenuCategoria(){
        carregarPagina("FXMLMenuCategoria.fxml");
    }
    
    public void listar(){
        CategoriaDAO categoriaDao = new CategoriaDAO();
        listaCategoria.clear();
        listaCategoria.addAll(categoriaDao.buscarCategorias());
    } 
    
}
