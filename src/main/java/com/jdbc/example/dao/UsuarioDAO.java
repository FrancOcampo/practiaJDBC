package com.jdbc.example.dao;

import com.jdbc.example.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {
    void agregarUsuario(Usuario usuario) throws SQLException;
    Usuario obtenerUsuarioID(int id) throws SQLException;
    List<Usuario> obtenerUsuarios() throws SQLException;
    void actualizarUsuario(Usuario usuario) throws SQLException;
    void eliminarUsuario(int id) throws SQLException;
    int getUltimoId();
}
