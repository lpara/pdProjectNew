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
import javafx.animation.FadeTransition;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private Button btnMenuCliente;
    
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
    
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @FXML
    public void goToCadastroCliente(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        carregarPagina("ClienteCadastroFXML.fxml", stage);
    }
    
    @FXML
    public void goToMenuCliente(ActionEvent event) {
//        Node node = (Node) event.getSource();
//        Stage stage = (Stage) node.getScene().getWindow();
        carregarPagina("ClienteFXML.fxml");
    }
    
    @FXML
    public void goToAtualizaCliente(ActionEvent event) throws IOException {
        
       FXMLLoader loader = new FXMLLoader();
	loader.setLocation(getClass().getResource("ClienteAtualizaFXML.fxml"));
	AnchorPane page = (AnchorPane) loader.load();
	FadeTransition ft = new FadeTransition(Duration.millis(900), page);
	ft.setFromValue(0.0);
	ft.setToValue(0.97);
	ft.play();
	Stage stage = new Stage();
	page.setOpacity(0.85);
	Popup popup = new Popup();
	popup.setAutoHide(true);
	popup.getContent().add(page);
	popup.show(stage);
//        FXMLLoader loader = new FXMLLoader();
//       URL resource = getClass().getResource("ClienteAtualizaFXML.fxml");
//        loader.setLocation(resource);
//        ClienteAtualizaFXMLController control = new ClienteAtualizaFXMLController();
//        control.setCliente(tblCliente.getSelectionModel().getSelectedItem());
//        loader.setController(control);
//        
//        try {
//             Parent root = loader.load(getClass().getResource("ClienteAtualizaFXML.fxml"));
////            Parent root = loader.load();
//            root.setUserData(cliente);
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//            Stage stage = new Stage();
////            Parent root = FXMLLoader.load(getClass().getResource(pagina));
//            stage.setTitle("Ações de Filmes");
//            stage.setScene(new Scene(root));
//            stage.show();
        
//        carregarPagina("ClienteAtualizaFXML.fxml");
    }
    
    public ObservableList<Cliente> listaCliente = FXCollections.observableArrayList();

    private void listar() {
        
       ClienteDAO cDAO = new ClienteDAO();
       listaCliente.clear();
       listaCliente.addAll(cDAO.Listar());
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
