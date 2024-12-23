package com.example.medicalcabinet.Module;

import java.sql.Date;

public class Consultation {
    public int idConsultation;
    public Date dateConsultation;

    public Consultation(int idConsultation, Date dateConsultation) {
        this.idConsultation = idConsultation;
        this.dateConsultation = dateConsultation;
    }
    public String toString(){
        return "idConsultation : "+idConsultation+" dateConsultation : "+dateConsultation;
    }
}
