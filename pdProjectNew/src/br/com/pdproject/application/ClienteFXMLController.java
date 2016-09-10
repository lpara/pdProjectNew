/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdproject.application;

import com.br.pdproject.dao.ClienteDAO;
import com.br.pdproject.dominio.Cliente;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LUAN
 */
public class ClienteFXMLController extends AbstractController implements Initializable {

    @FXML
    private Button btnInserirCliente;
    
    @FXML
    private Button btnRemoverCliente;
    
    @FXML
    private Button btnListarCliente;
    
    @FXML
    private Button btnAtualizarCliente;
    
    @FXML
    private Button btnPersisteCliente;
    
    @FXML
    private Button btnFiltrar;
    
    @FXML
    private Button btnMenuPrincipal;
    
    @FXML
    private TableColumn<Cliente, Integer> colIdCliente;
    
    @FXML
    private TableColumn<Cliente, String> colNomeCliente;
    
    @FXML
    private TableColumn<Cliente, String> colSobrenomeCliente;

    @FXML
    private TableColumn<Cliente, String> colEmailCliente;
    
    @FXML
    private TableColumn<Cliente, Date> colDataCadastroCliente;

    @FXML
    private TableView<Cliente> tblCliente;
    
    @FXML
    private TextField txtNomeCliente;
    
    @FXML
    private TextField txtSobrenomeCliente;
    
    @FXML
    private TextField txtEmailCliente;
    
    @FXML
    private TextField txtfiltro;
    
    private Cliente cliente;
    
    private Stage clienteStage;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @FXML
    public void goToCadastroCliente(ActionEvent event) {
        Node node = (Node) event.getSource();
        clienteStage = (Stage) node.getScene().getWindow();
        carregarPagina("ClienteCadastroFXML.fxml", clienteStage);
    }
    
    @FXML
    public void goToMenuCliente(ActionEvent event) {
//        Node node = (Node) event.getSource();
//        Stage stage = (Stage) node.getScene().getWindow();
        carregarPagina("ClienteFXML.fxml");
    }
    
    @FXML
    public void goToAtualizaCliente(ActionEvent event) throws IOException {
       stagePrincipal.setUserData(tblCliente.getSelectionModel().getSelectedItem());
       carregarPagina("ClienteAtualizaFXML.fxml");
    }
    
    public ObservableList<Cliente> listaCliente = FXCollections.observableArrayList();

    private void listar() {
        
       ClienteDAO cDAO = new ClienteDAO();
       listaCliente.clear();
       listaCliente.addAll(cDAO.Listar());
        
    }
    
    @FXML
    public void filtrarCliente() {
        
       ClienteDAO cDAO = new ClienteDAO();
       listaCliente.clear();
       listaCliente.addAll(cDAO.Filtrar(txtfiltro.getText()));
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        clienteStage = new Stage();
        
        setColumn(colIdCliente, "id");
        setColumn(colNomeCliente, "nome");
        setColumn(colSobrenomeCliente, "sobrenome");
        setColumn(colEmailCliente, "email");
        setColumn(colDataCadastroCliente, "dataCadastro");
        tblCliente.setItems(listaCliente);
        listar();
    }

    public void salvar() {
        
        ClienteDAO cDAO = new ClienteDAO();
        cDAO.Salvar(txtNomeCliente.getText(), txtSobrenomeCliente.getText(), txtEmailCliente.getText());
        listar();
    }
    
    public void remover() {
        
        ClienteDAO cDAO = new ClienteDAO();
        Cliente c = tblCliente.getSelectionModel().getSelectedItem();
        cDAO.Remover(c);
        listar();
    }
    
}
