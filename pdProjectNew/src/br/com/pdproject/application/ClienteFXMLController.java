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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private Button btnConfirmaRemocao = new Button("Deseja Remover?");

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @FXML
    public void goToCadastroCliente(ActionEvent event) {
        stagePrincipal.setTitle("Cadastro de Cliente");
        carregarPagina("ClientesCadastroFXML.fxml");
    }

    @FXML
    public void goToMenuCliente(ActionEvent event) {
        stagePrincipal.setTitle("Cliente");
        carregarPagina("ClienteFXML.fxml");
    }

    @FXML
    public void goToMenuPrincipal(ActionEvent event) {
        stagePrincipal.setTitle("Locato");
        carregarPagina("FXMLDocument.fxml");
    }

    @FXML
    public void goToAtualizaCliente(ActionEvent event) throws IOException {
        if (tblCliente.getSelectionModel() == null || tblCliente.getSelectionModel().getSelectedItem() == null) {
            erroElementoNaoSelecionado();
        } else {
            stagePrincipal.setUserData(tblCliente.getSelectionModel().getSelectedItem());
            stagePrincipal.setTitle("Atualiza cliente");
            carregarPagina("ClienteAtualizaFXML.fxml", stagePrincipal);
        }
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

        configurarDialogo();

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

    public void configurarDialogo() {
        btnConfirmaRemocao.setOnAction(e -> {
            if (tblCliente.getSelectionModel() == null || tblCliente.getSelectionModel().getSelectedItem() == null) {
                erroElementoNaoSelecionado();
            } else {
                Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
                ButtonType btnSim = new ButtonType("Sim");
                ButtonType btnNao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);

                Cliente c = tblCliente.getSelectionModel().getSelectedItem();

                dialogoExe.setTitle("Confirmar Remoção");
                dialogoExe.setHeaderText("Deseja remover o registro selecionado?");
                dialogoExe.setContentText("Deseja remover o cliente " + c.getNome() + " " + c.getSobrenome() + " ?");
                dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
                dialogoExe.showAndWait().ifPresent(b -> {
                    if (b == btnSim) {
                        remover();
                    }
                });
            }
        });
    }

}
