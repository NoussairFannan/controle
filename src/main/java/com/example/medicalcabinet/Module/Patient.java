package com.example.medicalcabinet.Module;

import java.sql.Date;

public class Patient {
    public int idPatient;
    public String nom;
    public String prenom;
    public String telephone;
    public String email;
    public String cin;
    public Date dateNaissance;

    public Patient(int idPatient, String nom, String prenom, String telephone, String email, String cin, Date dateNaissance) {
        this.idPatient = idPatient;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.cin = cin;
        this.dateNaissance = dateNaissance;
    }
    public Patient(){}


    public String toString(){
        return "idPatient : "+idPatient+" nom : "+nom+" prenom : "+prenom+" telephone : "+telephone+" email : "+email+" cin : "+cin+" dateNaissance : "+dateNaissance;
    }
}
