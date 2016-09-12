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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class FXMLMenuCategoriaController extends AbstractController implements Initializable {

    @FXML
    private Button btnInserirCatoria;
    
    @FXML
    private Button btnRemoverCategoria;
    
    @FXML
    private Button btnAtualizarCategoria;
    
    @FXML
    private Button btnFiltrar;
    
    @FXML
    private Button btnVoltarMenuInicial;
    
    @FXML
    private TableColumn<Categoria, Integer> colIdCategoria;
    
    @FXML
    private TableColumn<Categoria, String> colNomeCategoria;
    
    @FXML
    private TableView<Categoria> tblCategoria;
    
    @FXML
    private TextField txtfiltro;
    
    public ObservableList<Categoria> listaCategoria = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setColumn(colIdCategoria, "id");
        setColumn(colNomeCategoria, "nome");
        tblCategoria.setItems(listaCategoria);
        listar();
    }    
    
    public void listar(){
        CategoriaDAO categoriaDao = new CategoriaDAO();
        listaCategoria.clear();
        listaCategoria.addAll(categoriaDao.buscarCategorias());
    }
    
    @FXML
    public void filtrarCategoria() {
        
       CategoriaDAO categoriaDao = new CategoriaDAO();
       listaCategoria.clear();
       listaCategoria.addAll(categoriaDao.Filtrar(txtfiltro.getText()));
        
    }
    
    public void remover() {
        if (tblCategoria.getSelectionModel() == null || tblCategoria.getSelectionModel().getSelectedItem() == null) {
            erroElementoNaoSelecionado();
        } else{
            CategoriaDAO categoriaDao = new CategoriaDAO();
            Categoria c = tblCategoria.getSelectionModel().getSelectedItem();
            categoriaDao.removerCategoria(c);
            listar();
        }
    }
    
    public void acaoInserir(ActionEvent event){
        carregarPagina("FXMLInserirCategoria.fxml");
    }
    
    public void acaoAtualizar(ActionEvent event){
        if (tblCategoria.getSelectionModel() == null || tblCategoria.getSelectionModel().getSelectedItem() == null) {
            erroElementoNaoSelecionado();
        } else {
            stagePrincipal.setUserData(tblCategoria.getSelectionModel().getSelectedItem());
            carregarPagina("FXMLAtualizarCategoria.fxml");
        }
    }
    
    public void voltarMenuInicial(){
        stagePrincipal.setTitle("Menu Principal");
        carregarPagina("FXMLDocument.fxml");
    }
}
