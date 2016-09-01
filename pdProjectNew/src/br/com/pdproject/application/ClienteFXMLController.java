/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdproject.application;

import com.br.pdproject.dao.GenericDAO;
import com.br.pdproject.dominio.Cliente;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author LUAN
 */
public class ClienteFXMLController extends AbstractController implements Initializable {

    @FXML
    private Button listarClientes;
    
    @FXML
    private TableColumn<Cliente, String> colNomeCliente;

    @FXML
    private TableColumn<Cliente, String> colEmailCliente;

    @FXML
    private TableView<Cliente> tblCliente;
    
    public ObservableList<Cliente> listaCliente = FXCollections.observableArrayList();

    private void listar() {
       EntityManager em = GenericDAO.getEntityManager().createEntityManager();
       List<Cliente> clientes =  em.createQuery("from Cliente order by nome").getResultList();
       listaCliente.clear();
       listaCliente.addAll(clientes);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setColumn(colNomeCliente, "Nome");
        setColumn(colEmailCliente, "email");
        tblCliente.setItems(listaCliente);
        listar();
    }    
    
}
