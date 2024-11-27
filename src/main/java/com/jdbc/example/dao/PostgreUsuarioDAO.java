package com.jdbc.example.dao;

import com.jdbc.example.config.ConexionPostgreBD;
import com.jdbc.example.model.Mp;
import com.jdbc.example.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreUsuarioDAO implements UsuarioDAO{

    @Override
    public void agregarUsuario(Usuario usuario) throws SQLException{
        String sql = "INSERT INTO usuarios (nombre, mail, edad) values (?,?,?)";

        try{
            Connection conn= ConexionPostgreBD.getConnection();

            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, usuario.getNombre());
            pstm.setString(2, usuario.getMail());
            pstm.setInt(3, usuario.getEdad());
            pstm.executeUpdate();

            System.out.println("Update Exitoso!!");

        }catch (SQLException e1){
            do{
                System.err.println("Error al agregar usuario "+e1.getMessage()+" codigo de error: "+e1.getErrorCode());
                e1.printStackTrace();
                e1=e1.getNextException();
            }while(e1!=null);
        }
    }

    @Override
    public Usuario obtenerUsuarioID(int id) throws SQLException {
        String sql="select * from usuarios where id=?";
        Usuario user_aux=null;
        try{
            Connection conn = ConexionPostgreBD.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,id);

            ResultSet rs = pstm.executeQuery();
            rs.next();

            user_aux = new Usuario(rs.getInt("id"),rs.getString("nombre"), rs.getString("mail"), rs.getInt("edad"));
        } catch (SQLException e1) {
            do{
                e1.printStackTrace();
                e1=e1.getNextException();
            }while(e1!=null);
        }
        return user_aux;
    }

    @Override
    public List<Usuario> obtenerUsuarios() throws SQLException {

        List<Usuario> listaUsuarios = new ArrayList<>();

        String sql="SELECT * FROM usuarios";

        try(Connection conn = ConexionPostgreBD.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql)){

            while(rs.next()){
                listaUsuarios.add(new Usuario(  rs.getInt("id"),
                                                rs.getString("nombre"),
                                                rs.getString("mail"),
                                                rs.getInt("edad"))
                                                );
            }

        }catch(SQLException e1){
            e1.printStackTrace();
        }

        return listaUsuarios;
    }

    @Override
    public void actualizarUsuario(Usuario usuario) throws SQLException {
        String sql="UPDATE usuarios SET nombre = ?, mail = ?, edad = ? WHERE id = ?";

        try(Connection conn = ConexionPostgreBD.getConnection();
            PreparedStatement pstm =conn.prepareStatement(sql)){

            pstm.setString(1, usuario.getNombre());
            pstm.setString(2, usuario.getMail());
            pstm.setInt(3, usuario.getEdad());
            pstm.setInt(4, usuario.getId());
            int cantFilasAfectadas = pstm.executeUpdate();

            System.out.println("Cantidad de filas afectadas: "+cantFilasAfectadas);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarUsuario(int id) throws SQLException {
        String sql="delete from usuarios where id=?";

        try(Connection conn = ConexionPostgreBD.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)){

            pstm.setInt(1,id);

            int cantFilasAfectadas = pstm.executeUpdate();

            System.out.println("Cantidad de filas borradas: "+cantFilasAfectadas);
        }catch(SQLException e){
            System.err.println("Error al abrir la conexion "+e.getMessage());
        }
    }

    @Override
    public int getUltimoId(){
        String sql= "Select max(id) from usuarios";
        int ultimoId=-1;
        try(Connection conn = ConexionPostgreBD.getConnection();
            Statement stm = conn.createStatement();){

            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            if(rs!=null) ultimoId = rs.getInt(1);
            else ultimoId=1;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return ultimoId;
    }

}
