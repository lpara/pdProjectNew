/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdproject.application;

import com.br.pdproject.dao.EquipeDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LUAN
 */
public class FuncionarioCadastroFXMLController extends AbstractController implements Initializable {

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
    private Button btnMenuFuncionario;

    @FXML
    private Button btnMenuPrincipal;

    @FXML
    private Button btnConfirmaCadastro = new Button("Sucesso");


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
    public void goToCadastroFuncionario(ActionEvent event) {
        stagePrincipal.setTitle("Cadastro de Funcionario");
        carregarPagina("FuncionarioCadastroFXML.fxml", stagePrincipal);
    }

    @FXML
    public void goToMenuFuncionario(ActionEvent event) {
        stagePrincipal.setTitle("Menu Funcionario");
        carregarPagina("FuncionarioFXML.fxml", stagePrincipal);
    }

    @FXML
    public void goToMenuPrincipal(ActionEvent event) {
        stagePrincipal.setTitle("Locato");
        carregarPagina("FXMLDocument.fxml", stagePrincipal);
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarDialogo();
    }

    public void salvar() {

        EquipeDAO cDAO = new EquipeDAO();
        cDAO.Salvar(txtNomeFuncionario.getText(), txtSobrenomeFuncionario.getText(), txtEmailFuncionario.getText(), txtLoginFuncionario.getText(), txtSenhaFuncionario.getText());

    }

    public void configurarDialogo() {
        btnConfirmaCadastro.setOnAction(e -> {

            salvar();
            Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
            ButtonType btnNovoCadastro = new ButtonType("Novo Cadastro");
            ButtonType btnVoltarMenuFuncionario = new ButtonType("Menu funcionario");
            ButtonType btnVoltarMenuPrincipal = new ButtonType("Menu Principal");

            dialogoExe.setTitle("Sucesso");
            dialogoExe.setHeaderText("O registro foi inserido com sucesso!!!");
            dialogoExe.setContentText("O que deseja fazer?");
            dialogoExe.getButtonTypes().setAll(btnNovoCadastro, btnVoltarMenuFuncionario, btnVoltarMenuPrincipal);
            dialogoExe.showAndWait().ifPresent(b -> {
                if (b == btnNovoCadastro) {
                    goToCadastroFuncionario(e);
                } else if (b == btnVoltarMenuFuncionario) {
                    goToMenuFuncionario(e);
                } else if (b == btnVoltarMenuPrincipal) {
                    goToMenuPrincipal(e);
                }
            });
        });
    }

}
