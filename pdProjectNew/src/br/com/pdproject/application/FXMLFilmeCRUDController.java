/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdproject.application;

import com.br.pdproject.dao.FilmeDAO;
import com.br.pdproject.dominio.Filme;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class FXMLFilmeCRUDController extends AbstractController implements Initializable {

    @FXML
    private Button btnInserirFilme;
    
    @FXML
    private Button btnRemoverFilme;
    
    @FXML
    private Button btnAtualizarFilme;
    
    @FXML
    private Button btnFiltrar;
    
    @FXML
    private Button btnVoltarMenuInicial;
    
    @FXML
    private TableColumn<Filme, Integer> colIdFilme;
    
    @FXML
    private TableColumn<Filme, String> colTituloFilme;
    
    @FXML
    private TableColumn<Filme, String> colDescFilme;

    @FXML
    private TableColumn<Filme, Integer> colAnoFilme;

    @FXML
    private TableView<Filme> tblFilme;
    
    @FXML
    private TextField txtfiltro;
    
    public ObservableList<Filme> listaFilme = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        setColumn(colIdFilme, "id");
        setColumn(colTituloFilme, "titulo");
        setColumn(colDescFilme, "descricao");
        setColumn(colAnoFilme, "anoLancamento");
        tblFilme.setItems(listaFilme);
        listar();
    }
    
    public void listar(){
        FilmeDAO filmeDao = new FilmeDAO();
        listaFilme.clear();
        listaFilme.addAll(filmeDao.listarFilmes());
    }
    
    @FXML
    public void filtrarFilme() {
        
       FilmeDAO filmeDao = new FilmeDAO();
       listaFilme.clear();
       listaFilme.addAll(filmeDao.Filtrar(txtfiltro.getText()));
        
    }

     public void remover() {
        if (tblFilme.getSelectionModel() == null || tblFilme.getSelectionModel().getSelectedItem() == null) {
            erroElementoNaoSelecionado();
        } else{
            FilmeDAO filmeDao = new FilmeDAO();
            Filme f = tblFilme.getSelectionModel().getSelectedItem();
            filmeDao.removerFilme(f);
            listar();
        }
    }
    
    public void acaoInserir(ActionEvent event){
        carregarPagina("FXMLInserirFilme.fxml");
    }
    
     public void acaoAtualizar(ActionEvent event){
         if (tblFilme.getSelectionModel() == null || tblFilme.getSelectionModel().getSelectedItem() == null) {
            erroElementoNaoSelecionado();
        } else {
            stagePrincipal.setUserData(tblFilme.getSelectionModel().getSelectedItem());
            carregarPagina("FXMLAtualizarFilme.fxml");
         }
    }
    
    public void voltarMenuInicial(){
        stagePrincipal.setTitle("Menu Principal");
        carregarPagina("FXMLDocument.fxml");
    }
    
}
