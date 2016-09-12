/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdproject.application;

import com.br.pdproject.dao.EquipeDAO;
import com.br.pdproject.dominio.Equipe;
import java.io.IOException;
import java.net.URL;
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
public class FuncionarioFXMLController extends AbstractController implements Initializable {

    @FXML
    private Button btnInserirFuncionario;

    @FXML
    private Button btnRemoverFuncionario;

    @FXML
    private Button btnListarFuncionario;

    @FXML
    private Button btnAtualizarFuncionario;

    @FXML
    private Button btnPersisteFuncionario;

    @FXML
    private Button btnFiltrar;

    @FXML
    private Button btnMenuPrincipal;

    @FXML
    private Button btnConfirmaRemocao = new Button("Deseja Remover?");

    @FXML
    private TableColumn<Equipe, Integer> colIdFuncionario;

    @FXML
    private TableColumn<Equipe, String> colNomeFuncionario;

    @FXML
    private TableColumn<Equipe, String> colSobrenomeFuncionario;

    @FXML
    private TableColumn<Equipe, String> colEmailFuncionario;

    @FXML
    private TableColumn<Equipe, String> colLoginFuncionario;

    @FXML
    private TableView<Equipe> tblFuncionario;

    @FXML
    private TextField txtNomeFuncionario;

    @FXML
    private TextField txtSobrenomeFuncionario;

    @FXML
    private TextField txtEmailFuncionario;

    @FXML
    private TextField txtLoginFuncionario;

    @FXML
    private TextField txtSenhaFuncionario;

    @FXML
    private TextField txtFiltro;

    private Equipe funcionario;

//    private Stage funcionarioStage;
    public Equipe getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Equipe funcionario) {
        this.funcionario = funcionario;
    }

    @FXML
    public void goToCadastroFuncionario(ActionEvent event) {
        stagePrincipal.setTitle("Cadastro de Funcionario");
        carregarPagina("FuncionarioCadastroFXML.fxml");
    }

    @FXML
    public void goToMenuFuncionario(ActionEvent event) {
        stagePrincipal.setTitle("Funcionario");
        carregarPagina("FuncionarioFXML.fxml");
    }

    @FXML
    public void goToMenuPrincipal(ActionEvent event) {
        stagePrincipal.setTitle("Locato");
        carregarPagina("FXMLDocument.fxml");
    }

    @FXML
    public void goToAtualizaFuncionario(ActionEvent event) throws IOException {
        if (tblFuncionario.getSelectionModel() == null || tblFuncionario.getSelectionModel().getSelectedItem() == null) {
            erroElementoNaoSelecionado();
        } else {
            stagePrincipal.setUserData(tblFuncionario.getSelectionModel().getSelectedItem());
            stagePrincipal.setTitle("Atualiza funcionario");
            carregarPagina("FuncionarioAtualizaFXML.fxml", stagePrincipal);
        }
    }

    public ObservableList<Equipe> listaFuncionario = FXCollections.observableArrayList();

    private void listar() {

        EquipeDAO eDAO = new EquipeDAO();
        listaFuncionario.clear();
        listaFuncionario.addAll(eDAO.Listar());

    }

    @FXML
    public void filtrarFuncionario() {

        EquipeDAO eDAO = new EquipeDAO();
        listaFuncionario.clear();
        listaFuncionario.addAll(eDAO.Filtrar(txtFiltro.getText()));

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        configurarDialogo();

        setColumn(colIdFuncionario, "id");
        setColumn(colNomeFuncionario, "nome");
        setColumn(colSobrenomeFuncionario, "sobrenome");
        setColumn(colEmailFuncionario, "email");
        setColumn(colLoginFuncionario, "login");

        tblFuncionario.setItems(listaFuncionario);

        listar();
    }

    public void salvar() {

        EquipeDAO eDAO = new EquipeDAO();
        eDAO.Salvar(txtNomeFuncionario.getText(), txtSobrenomeFuncionario.getText(),
                txtEmailFuncionario.getText(), txtSenhaFuncionario.getText(), txtSenhaFuncionario.getText());
        listar();
    }

    public void remover() {

        EquipeDAO eDAO = new EquipeDAO();
        Equipe f = tblFuncionario.getSelectionModel().getSelectedItem();
        eDAO.Remover(f);
        listar();
    }

    public void configurarDialogo() {
        btnConfirmaRemocao.setOnAction(e -> {

            if (tblFuncionario.getSelectionModel() == null || tblFuncionario.getSelectionModel().getSelectedItem() == null) {
                erroElementoNaoSelecionado();
            } else {

                Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
                ButtonType btnSim = new ButtonType("Sim");
                ButtonType btnNao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);

                Equipe f = tblFuncionario.getSelectionModel().getSelectedItem();

                dialogoExe.setTitle("Confirmar Remoção");
                dialogoExe.setHeaderText("Deseja remover o registro selecionado?");
                dialogoExe.setContentText("Deseja remover o funcionario " + f.getNome() + " " + f.getSobrenome() + " ?");
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
