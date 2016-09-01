/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdproject.application;

import com.br.pdproject.dao.EquipeDAO;
import com.br.pdproject.dominio.Equipe;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Lucas
 */
public class PdProjectNew extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        problema2();
        launch(args);
    }
    
     static void problema2() {
        EquipeDAO  equipeDao = new EquipeDAO();          
        Equipe eq = equipeDao.buscarEmpregadoMike();

        if(eq != null){
                System.out.println("--------------------------------------------------------");
                System.out.println("Nome: '"+eq.getPrimeiroNome()+" "+eq.getUltimoNome()+"'");
                System.out.println("Endereço: '" + eq.getEndereco().getEnderecoPrincipal()+"'");
                System.out.println("Telefone: '" + eq.getEndereco().getTelefone()+"'");
                System.out.println("Cidade: '" + eq.getEndereco().getCidade().getNome_cidade()+"'");
                System.out.println("País: '" + eq.getEndereco().getCidade().getPais().getNome()+"'");
                System.out.println("--------------------------------------------------------");
        }

    }
    
}
