package com.jdbc.example.view;

import java.util.List;
import java.util.Scanner;

import com.jdbc.example.controller.ControladorVistaUsuario;
import com.jdbc.example.model.Mp;
import com.jdbc.example.model.Usuario;


public class UsuarioView {
    private final Scanner scanner = new Scanner(System.in);
    //private ControladorVistaUsuario controladorUsuario = ControladorVistaUsuario.getInstance(this);

    public int mostrarMenu(){
        Mp.println("1. Agregar usuario");
        Mp.println("2. Ver usuarios");
        Mp.println("3. Actualizar usuario");
        Mp.println("4. Eliminar usuario");
        Mp.println("5. Salir");
        Mp.println("Seleccione una opción xfa: ");
        return scanner.nextInt();
    }

    public Usuario capturarDatosUsuario(){
        Mp.println("Nombre: ");
        String nombre = scanner.next();
        Mp.println("Mail: ");
        String mail = scanner.next();
        Mp.println("Edad: ");
        int edad = scanner.nextInt();
        return new Usuario(0, nombre, mail, edad);
        //controladorUsuario.agregarUsuario(nombre,mail,edad);
    }

    public void mostrarUsuarios(List<Usuario> listaUsuarios){
        for(Usuario u: listaUsuarios){
            System.out.println(u.toString()+" su id es: "+u.getId());
        }
    }

    public int capturarId(){
        imprimirSeparador();
        imprimirSeparador();
        Mp.println("Ingresar el Id: ");
        return scanner.nextInt();
    }

    public void mostrarMensaje(String mensaje){
        imprimirSeparador();
        imprimirSeparador();
        Mp.println(mensaje);
    }

    private void imprimirSeparador(){
        Mp.println("\n--------------------------------------------------\n");
    }

    public boolean pedirConfirmacion(){
        mostrarMensaje("    Desea continuar \n  s=si, otra tecla cancelará la operacion");
        String s=scanner.next();
        if(s.toLowerCase().equals("s")) return true;
        return false;
    }
}
