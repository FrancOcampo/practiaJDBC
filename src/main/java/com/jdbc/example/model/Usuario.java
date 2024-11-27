package com.jdbc.example.model;

public class Usuario {

    private int id;
    private String nombre;
    private String mail;
    private int edad;

    public Usuario() {}

    public Usuario(int id, String nombre, String mail, int edad){
        this.id=id;
        this.nombre=nombre;
        this.mail=mail;
        this.edad=edad;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString(){
        return "ID: "+getId()+" El nombre es: "+this.nombre+" con el mail: "+this.mail+
                " y edad: "+this.edad;
    }
}
