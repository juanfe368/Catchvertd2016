package com.vuforia.samples.Catchvertd;

import android.app.AlertDialog;

import java.util.ArrayList;

/**
 * Created by juanfe on 23/10/16.
 * Clase que permite realizar todas las acciones del usuario
 */

public class ClassUsuario {

    private String userName;
    private String userClave;
    private String nombreUsuario;
    private String emailUsuario;
    private String numCelularUsuario;
    private ConnectWs connectWs;

    public ClassUsuario(String pUserName, String pUserClave, String pNombreUsuario, String pEmailUsuario, String pNumCelular){
        this.userName = pUserName;
        this.userClave = pUserClave;
        this.nombreUsuario = pNombreUsuario;
        this.emailUsuario = pEmailUsuario;
        this.numCelularUsuario = pNumCelular;
        connectWs = new ConnectWs();
    }

    public ArrayList autenticarUsuario(ClassUsuario usu){
        //ArrayList arrayDatosUsuario =  connectWs.autenticarUsuario(usu.getUserName(),usu.getUserClave());
        //return arrayDatosUsuario;
        return null;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserClave() {
        return userClave;
    }

    public void setUserClave(String userClave) {
        this.userClave = userClave;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getNumCelularUsuario() {
        return numCelularUsuario;
    }

    public void setNumCelularUsuario(String numCelularUsuario) {
        this.numCelularUsuario = numCelularUsuario;
    }
}
