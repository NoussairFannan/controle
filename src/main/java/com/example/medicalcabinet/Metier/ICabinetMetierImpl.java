package com.example.medicalcabinet.Metier;

import java.util.List;
import Module.Consultation;
import Module.Medecin;
import Module.Patient;

public interface ICabinetMetierImpl {
        // Patients
        List<Patient> getPatients();
        List<Patient> searchPatients(String keyword);
        void addPatient(Patient patient);
        void deletePatient(int idPatient);
        List<Consultation> getConsultationsByPatient(int idPatient);

        // Medecins
        void addMedecin(Medecin medecin);
        List<Medecin> getMedecins();
        void deleteMedecin(int idMedecin);
        List<Consultation> getConsultationsByMedecin(int idMedecin);

        // Consultations
        void addConsultation(Consultation consultation, int idPatient, int idMedecin);
        List<Consultation> getConsultations();
        void deleteConsultation(int idConsultation);

}
