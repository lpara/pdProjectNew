/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdproject.application;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author LUAN
 */
public abstract class AbstractController {
    
    public void setColumn(TableColumn col, String atributo){
        col.setCellValueFactory(new PropertyValueFactory<>(atributo));
    }
}
