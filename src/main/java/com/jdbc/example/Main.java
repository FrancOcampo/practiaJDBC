package com.jdbc.example;

import com.jdbc.example.controller.ControladorVistaUsuario;
import com.jdbc.example.dao.PostgreUsuarioDAO;
import com.jdbc.example.dao.UsuarioDAO;
import com.jdbc.example.view.UsuarioView;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido a IntelliJ IDEA!");

        UsuarioView uv = new UsuarioView(); //creo vista

        ControladorVistaUsuario cvu = ControladorVistaUsuario.getInstance(uv); //creo controlador

        cvu.initUV();//ejecuto la vista principal

    }
}