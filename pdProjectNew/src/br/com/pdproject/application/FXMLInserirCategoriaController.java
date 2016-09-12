/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdproject.application;

import com.br.pdproject.dao.CategoriaDAO;
import com.br.pdproject.dominio.Categoria;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class FXMLInserirCategoriaController extends AbstractController implements Initializable {

    @FXML
    private TextField tfNomeCategoria;
    
    @FXML
    private Text tfAlerta; 
    
    @FXML
    private Button btnCadastrar;
    
    @FXML
    private Button btnVoltar;
    
    public void acaoInserir(ActionEvent event){
        CategoriaDAO catDao = new CategoriaDAO();
        Categoria cat = new Categoria();
        if(isTextoNull(tfNomeCategoria.getText())){
            tfAlerta.setVisible(true);
        }else{
            tfAlerta.setVisible(false);
            cat.setNome(tfNomeCategoria.getText());
            catDao.inserirCategoria(cat);
            avancarTelaSucesso();
        }
        
    }
    
    public void avancarTelaSucesso(){
        carregarPagina("FXMLSucessoCategoria.fxml");
    }
    public void voltarPagina() {
        carregarPagina("FXMLMenuCategoria.fxml");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    protected boolean isTextoNull(String texto){
        if(texto == null || texto.equals("")){
          return true;
        }  
        return false;
    }    
    
}
