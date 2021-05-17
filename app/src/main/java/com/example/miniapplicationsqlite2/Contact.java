package com.example.miniapplicationsqlite2;

public class Contact {
    private int id;
    private String nom;
    private String numero;

    public Contact(String nom, String numero) {
        this.nom = nom;
        this.numero = numero;
    }

    public Contact() {
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getNumero() {
        return numero;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "nom='" + nom + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}