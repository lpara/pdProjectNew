/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdproject.application;

import static br.com.pdproject.application.PdProjectNew.stagePrincipal;
import com.br.pdproject.dao.CategoriaDAO;
import com.br.pdproject.dao.FilmeDAO;
import com.br.pdproject.dominio.Categoria;
import com.br.pdproject.dominio.Filme;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class FXMLAtualizarFilmeController extends AbstractController implements Initializable {

    @FXML
    private TextField tfTituloAttFilme;
    
    @FXML
    private TextArea taDescricaoAttFilme;
    
    @FXML
    private ComboBox<Integer> cbAnoAttFilme;
    
    @FXML
    private ComboBox<String> cbCategoriaAttFilme;
    
    @FXML
    private Button btnAtualizarFilme;
    
    private Filme filme;
    
    public ObservableList<Filme> listaFilme = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarComboCategoria();
        carregarComboAno();
        filme = (Filme) stagePrincipal.getUserData();
       
       stagePrincipal.setUserData(null);
       
       tfTituloAttFilme.setText(filme.getTitulo());
       taDescricaoAttFilme.setText(filme.getDescricao());
       cbAnoAttFilme.setValue(filme.getAnoLancamento());
       cbCategoriaAttFilme.setValue(filme.getCategoria().getNome());
    }

    public void listar(){
        FilmeDAO filmeDao = new FilmeDAO();
        listaFilme.clear();
        listaFilme.addAll(filmeDao.listarFilmes());
    }  
    
    public void atualizar() {
        
        
        filme.setTitulo(tfTituloAttFilme.getText());
        filme.setDescricao(taDescricaoAttFilme.getText());
        filme.setAnoLancamento(cbAnoAttFilme.getValue());
        Categoria cat = new Categoria();
        CategoriaDAO catDao = new CategoriaDAO();
        cat = catDao.buscarPorNome(cbCategoriaAttFilme.getValue());
        filme.setCategoria(cat);
        FilmeDAO filmeDao = new FilmeDAO();
        
        try {
            filmeDao.atualizarFilme(filme);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
       
        listar();
        
        carregarPagina("FXMLFilmeMenu.fxml");
        
    }
    
    private void carregarComboCategoria(){
        CategoriaDAO catDAO = new CategoriaDAO();
        List<Categoria> categorias = catDAO.buscarCategorias();
        
        for(Categoria cat : categorias){
            cbCategoriaAttFilme.getItems().add(cat.getNome());
        }
    }
    
    private void carregarComboAno(){
        SimpleDateFormat ano = new SimpleDateFormat("yyyy"); 
        Date data = new Date();
        String anoAtualStr=ano.format(data);
        int anoAtual = Integer.parseInt(anoAtualStr);
        for(Integer i = 1800; i <= anoAtual; i++){
            cbAnoAttFilme.getItems().add(i);
        }
    }
    
}
