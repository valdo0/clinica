package com.clinica.clinica.usuario.model.DTOS;

public class PutUsuarioDTO {
    private String nombre;
    private String password;
    private String telefono;
    private String rol;

    public PutUsuarioDTO(String nombre, String password, String telefono, String rol) {
        this.nombre = nombre;
        this.password = password;
        this.telefono = telefono;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
