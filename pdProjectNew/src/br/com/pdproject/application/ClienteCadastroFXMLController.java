/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdproject.application;

import com.br.pdproject.dao.ClienteDAO;
import com.br.pdproject.dominio.Cliente;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LUAN
 */
public class ClienteCadastroFXMLController extends AbstractController implements Initializable {

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
    private Button btnConfirmaCadastro = new Button("Sucesso");

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
    public void goToCadastroCliente(ActionEvent event) {
        stagePrincipal.setTitle("Cadastro de Ciente");
        carregarPagina("ClientesCadastroFXML.fxml", stagePrincipal);
    }

    @FXML
    public void goToMenuCliente(ActionEvent event) {
        stagePrincipal.setTitle("Menu Cliente");
        carregarPagina("ClienteFXML.fxml", stagePrincipal);
    }

    @FXML
    public void goToMenuPrincipal(ActionEvent event) {
        stagePrincipal.setTitle("Locato");
        carregarPagina("FXMLDocument.fxml", stagePrincipal);
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
        configurarDialogo();
    }

    public void salvar() {

        ClienteDAO cDAO = new ClienteDAO();
        cDAO.Salvar(txtNomeCliente.getText(), txtSobrenomeCliente.getText(), txtEmailCliente.getText());

        listar();

    }

    public void configurarDialogo() {
        btnConfirmaCadastro.setOnAction(e -> {

            salvar();
            Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
            ButtonType btnNovoCadastro = new ButtonType("Novo Cadastro");
            ButtonType btnVoltarMenuCliente = new ButtonType("Menu cliente");
            ButtonType btnVoltarMenuPrincipal = new ButtonType("Menu Principal");

            dialogoExe.setTitle("Sucesso");
            dialogoExe.setHeaderText("O registro foi inserido com sucesso!!!");
            dialogoExe.setContentText("O que deseja fazer?");
            dialogoExe.getButtonTypes().setAll(btnNovoCadastro, btnVoltarMenuCliente, btnVoltarMenuPrincipal);
            dialogoExe.showAndWait().ifPresent(b -> {
                if (b == btnNovoCadastro) {
                    goToCadastroCliente(e);
                } else if (b == btnVoltarMenuCliente) {
                    goToMenuCliente(e);
                } else if (b == btnVoltarMenuPrincipal) {
                    goToMenuPrincipal(e);
                }
            });
        });
    }

}
