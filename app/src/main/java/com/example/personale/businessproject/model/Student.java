package com.example.personale.businessproject.model;

/**
 * Created by personale on 19/02/2017.
 */

public class Student {
    private int id;
    private static int counter;
    private String nome, telefono, email;
    private Corso corso;

    public Student(String nome, String telefono, String email, Corso corso) {
        this.id = counter++;
        this.nome = nome;
        this.telefono = telefono;
        this.email = email;
        this.corso = corso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id > 0)
            this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome != null)
            this.nome = nome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if(telefono != null)
            this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email != null)
            this.email = email;
    }

    public Corso getCorso() {
        return corso;
    }

    public void setCorso(Corso corso) {
        if(corso != null)
            this.corso = corso;
    }
}
