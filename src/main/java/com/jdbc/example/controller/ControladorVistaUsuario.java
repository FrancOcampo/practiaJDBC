package com.jdbc.example.controller;

import com.jdbc.example.dao.PostgreUsuarioDAO;
import com.jdbc.example.dao.UsuarioDAO;
import com.jdbc.example.model.Mp;
import com.jdbc.example.model.Usuario;
import com.jdbc.example.view.UsuarioView;

import java.sql.SQLException;

public class ControladorVistaUsuario {

    private static ControladorVistaUsuario instancia;
    UsuarioView uv;
    UsuarioDAO userDao = new PostgreUsuarioDAO();

    private ControladorVistaUsuario(UsuarioView uv){
        this.uv=uv;
    }

    public static ControladorVistaUsuario getInstance(UsuarioView uv){
        if(instancia==null) instancia = new ControladorVistaUsuario(uv);
        return instancia;
    }

    public void initUV(){
        while(true) {
            int opcion=uv.mostrarMenu();
            try{
                switch (opcion) {
                    case 1 -> agregarUsuario(uv.capturarDatosUsuario());
                    case 2 -> uv.mostrarUsuarios(userDao.obtenerUsuarios());
                    case 3 -> actualizarUsuario(uv.capturarId());
                    case 4 -> eliminarUsuario(uv.capturarId());
                    case 5 ->{
                        Mp.println("Gracias por estar ☆*: .｡. o(≧▽≦)o .｡.:*☆ ADIOSSS!!");
                        return;
                    }
                    default -> uv.mostrarMensaje("Opcion Invalida ＞﹏＜, lee atentamente y prueba de nuevo");
                }
            }catch(Exception e){
                uv.mostrarMensaje("Ocurrio un error: "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void agregarUsuario(Usuario usuario){
        int ultimoId = userDao.getUltimoId();
        ultimoId++; //serializo la creacion de ids en la bd
        Usuario usuario_aux = new Usuario(ultimoId,usuario.getNombre(), usuario.getMail(), usuario.getEdad());
        try{
            userDao.agregarUsuario(usuario_aux);
            uv.mostrarMensaje("Se pudo agregar el Usuario exitosamente con id "+ultimoId);
        }catch (SQLException e) {
            System.err.println("Error al agregar un usuario " + e.getMessage());
        }
    }

    public void actualizarUsuario(int id){
        uv.mostrarMensaje("Los datos de ese usuario son: ");

        try {
            Mp.println(userDao.obtenerUsuarioID(id).toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        uv.mostrarMensaje("Ingresar los nuevos datos: ");
        try {
            Usuario usuario_aux=uv.capturarDatosUsuario();
            usuario_aux.setId(id);
            userDao.actualizarUsuario(usuario_aux);
            uv.mostrarMensaje("Se actualizó el usuario con exito!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarUsuario(int id){
        uv.mostrarMensaje("Está seguro de eliminar el siguiente usuario?");
        try {
            Mp.println(userDao.obtenerUsuarioID(id).toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(uv.pedirConfirmacion()){
            try {
                userDao.eliminarUsuario(id);
                uv.mostrarMensaje("Se eliminó el usuario exitosamente!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            uv.mostrarMensaje("Okis, volvemos al menú");
            return;
        }
    }
}
