/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdproject.application;

import com.br.pdproject.dao.EquipeDAO;
import com.br.pdproject.dominio.Equipe;
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
public class FuncionarioAtualizaFXMLController extends AbstractController implements Initializable {

    @FXML
    private Button btnListarFuncionario;

    @FXML
    private Button btnAtualizarFuncionario;

    @FXML
    private Button btnMenuFuncionario;

    @FXML
    private Button btnMenuPrincipal;

    @FXML
    private TextField txtNomeFuncionario;

    @FXML
    private TextField txtSobrenomeFuncionario;

    @FXML
    private TextField txtEmailFuncionario;

    private Equipe funcionario;

    public Equipe getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Equipe funcionario) {
        this.funcionario = funcionario;
    }

    @FXML
    public void goToMenuPrincipal(ActionEvent event) {
        stagePrincipal.setTitle("Locato");
        carregarPagina("FuncionarioCadastroFXML.fxml", stagePrincipal);
    }

    @FXML
    public void goToMenuFuncionario(ActionEvent event) {
        stagePrincipal.setTitle("Funcionario");
        carregarPagina("FuncionarioFXML.fxml", stagePrincipal);
    }

    public ObservableList<Equipe> listaFuncionario = FXCollections.observableArrayList();

    private void listar() {

        EquipeDAO cDAO = new EquipeDAO();
        listaFuncionario.clear();
        listaFuncionario.addAll(cDAO.Listar());

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        funcionario = (Equipe) stagePrincipal.getUserData();

        stagePrincipal.setUserData(null);

        txtNomeFuncionario.setText(funcionario.getNome());
        txtSobrenomeFuncionario.setText(funcionario.getSobrenome());
        txtEmailFuncionario.setText(funcionario.getEmail());

    }

    public void atualizar() {

        funcionario.setNome(txtNomeFuncionario.getText());
        funcionario.setSobrenome(txtSobrenomeFuncionario.getText());
        funcionario.setEmail(txtEmailFuncionario.getText());
        EquipeDAO cDAO = new EquipeDAO();

        try {
            cDAO.Atualizar(funcionario);
        } catch (Exception e) {
            e.printStackTrace();
        }

        stagePrincipal.setTitle("Funcionario");
        carregarPagina("FuncionarioFXML.fxml", stagePrincipal);

    }

}
