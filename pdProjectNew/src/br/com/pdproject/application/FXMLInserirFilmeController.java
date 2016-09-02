/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdproject.application;

import com.br.pdproject.dao.CategoriaDAO;
import com.br.pdproject.dao.FilmeDAO;
import com.br.pdproject.dao.IdiomaDAO;
import com.br.pdproject.dominio.Categoria;
import com.br.pdproject.dominio.Filme;
import com.br.pdproject.dominio.Idioma;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class FXMLInserirFilmeController extends AbstractController implements Initializable {

    @FXML
    private TextField tfTituloFilme;
    
    @FXML
    private TextArea taDescricaoFilme;
    
    @FXML
    private ComboBox<String> cbIdiomaFilme;
    
    @FXML
    private ComboBox<String> cbCategoriaFilme;
    
    @FXML
    private Text tfAlerta;   
    
    @FXML
    private Button btnCadastrar;
    
    @FXML
    private Button btnVoltar;
    
    public void acaoInserir(ActionEvent event){
        FilmeDAO filmDao = new FilmeDAO();
        CategoriaDAO catDao = new CategoriaDAO();
        IdiomaDAO idiomaDao = new IdiomaDAO();
        Filme filme = new Filme();
        if(isTextoNull(tfTituloFilme.getText()) || isTextoNull(taDescricaoFilme.getText()) || cbCategoriaFilme.getItems() == null || cbIdiomaFilme.getItems() == null){
            tfAlerta.setVisible(true);
        }else{
            tfAlerta.setVisible(false);
            filme.setTitulo(tfTituloFilme.getText());
            filme.setDescricao(taDescricaoFilme.getText());
            Categoria cat = new Categoria();
            cat = catDao.buscarPorNome(cbCategoriaFilme.getValue());
            Idioma idioma = new Idioma();
            idioma = idiomaDao.buscarPorNome(cbIdiomaFilme.getValue());
            filme.setCategoria(cat);
            filme.setIdioma(idioma);
            filmDao.inserirFilme(filme);
            avancarTelaSucesso();
        }
        
    }
    
    private void carregarComboCategoria(){
        CategoriaDAO catDAO = new CategoriaDAO();
        List<Categoria> categorias = catDAO.buscarCategorias();
        
        for(Categoria cat : categorias){
            cbCategoriaFilme.getItems().add(cat.getNome());
        }
    }
    
    private void carregarComboIdioma(){
        IdiomaDAO idiomaDao = new IdiomaDAO();
        List<Idioma> idiomas = idiomaDao.listarIdiomas();
        
        for(Idioma idioma : idiomas){
            cbIdiomaFilme.getItems().add(idioma.getNome());
        }
    }
    
    public void avancarTelaSucesso(){
        carregarPagina("FXMLSucessoFilme.fxml");
    }
    
    
    public void voltarPagina() {
        carregarPagina("FXMLFilmeCRUD.fxml");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarComboCategoria();
        carregarComboIdioma();
    }

    protected boolean isTextoNull(String texto){
      if(texto == null || texto.equals("")){
          return true;
      }  
      return false;
    }
    
}
