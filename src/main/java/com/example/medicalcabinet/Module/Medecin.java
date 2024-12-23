package com.example.medicalcabinet.Module;

public class Medecin {
    public int idMedecin;
    public String nom;
    public String prenom;
    public String email;
    public String tel;

    public Medecin(int idMedecin, String nom, String prenom, String email, String tel) {
        this.idMedecin = idMedecin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
    }

    public String toString(){
        return "idMedecin : "+idMedecin+" nom : "+nom+" prenom : "+prenom+" email : "+email+" tel : "+tel;
    }
}
