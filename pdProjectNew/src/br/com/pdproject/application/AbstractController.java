/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdproject.application;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author LUAN
 */
public abstract class AbstractController extends PdProjectNew {

    public void setColumn(TableColumn col, String atributo) {
        col.setCellValueFactory(new PropertyValueFactory<>(atributo));
    }

    public void carregarPagina(String pagina, Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(pagina));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void carregarPagina(String pagina) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(pagina));

            scenePrincipal.setRoot(root);
            stagePrincipal.setScene(scenePrincipal);
            stagePrincipal.sizeToScene();

            stagePrincipal.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void erroElementoNaoSelecionado() {
        Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
        dialogoErro.setTitle("Atenção");
        dialogoErro.setHeaderText("Elemento não selecionado");
        dialogoErro.setContentText("Um elemento deve ser selecionado para prosseguir com a operação!");
        dialogoErro.showAndWait();
    }

    public void atualizadoSucesso() {
        Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
        dialogoErro.setTitle("Sucesso");
        dialogoErro.setHeaderText("Operação Concluída!");
        dialogoErro.setContentText("O registro foi atualizado com sucesso!");
        dialogoErro.showAndWait();
    }
}
