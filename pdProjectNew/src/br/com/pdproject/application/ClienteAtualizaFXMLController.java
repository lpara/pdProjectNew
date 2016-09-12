/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdproject.application;

import com.br.pdproject.dao.ClienteDAO;
import com.br.pdproject.dominio.Cliente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LUAN
 */
public class ClienteAtualizaFXMLController extends AbstractController implements Initializable {

    @FXML
    private Button btnListarCliente;

    @FXML
    private Button btnAtualizarCliente;

    @FXML
    private Button btnMenuCliente;

    @FXML
    private Button btnMenuPrincipal;

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
    public void goToMenuPrincipal(ActionEvent event) {
        stagePrincipal.setTitle("Locato");
        carregarPagina("ClienteCadastroFXML.fxml", stagePrincipal);
    }

    @FXML
    public void goToMenuCliente(ActionEvent event) {
        stagePrincipal.setTitle("Cliente");
        carregarPagina("ClienteFXML.fxml", stagePrincipal);
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

        cliente = (Cliente) stagePrincipal.getUserData();

        stagePrincipal.setUserData(null);

        txtNomeCliente.setText(cliente.getNome());
        txtSobrenomeCliente.setText(cliente.getSobrenome());
        txtEmailCliente.setText(cliente.getEmail());

    }

    public void atualizar() {

        cliente.setNome(txtNomeCliente.getText());
        cliente.setSobrenome(txtSobrenomeCliente.getText());
        cliente.setEmail(txtEmailCliente.getText());
        ClienteDAO cDAO = new ClienteDAO();

        try {
            cDAO.Atualizar(cliente);
        } catch (Exception e) {
            e.printStackTrace();
        }

        atualizadoSucesso();
        
        stagePrincipal.setTitle("Cliente");
        carregarPagina("ClienteFXML.fxml", stagePrincipal);

    }

}
