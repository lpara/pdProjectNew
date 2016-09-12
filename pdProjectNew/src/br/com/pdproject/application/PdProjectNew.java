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
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Lucas
 */
public class PdProjectNew extends Application {

    
    public static Scene scenePrincipal;
    
    public static Stage stagePrincipal;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        scenePrincipal = new Scene(root);
        
        stagePrincipal = stage;
        
        Image icone = new Image("http://www.sjnk-am.com/common/img/icon_sizeL.png");
        
        stagePrincipal.getIcons().add(icone); 
        
        stagePrincipal.setScene(scenePrincipal);
        
        stagePrincipal.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static Scene getScenePrincipal() {
        return scenePrincipal;
    }

    public static void setScenePrincipal(Scene scenePrincipal) {
        PdProjectNew.scenePrincipal = scenePrincipal;
    }

    public static Stage getStagePrincipal() {
        return stagePrincipal;
    }

    public static void setStagePrincipal(Stage stagePrincipal) {
        PdProjectNew.stagePrincipal = stagePrincipal;
    }

}
