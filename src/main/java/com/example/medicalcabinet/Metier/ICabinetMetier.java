package com.example.medicalcabinet.Metier;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.example.medicalcabinet.Module.Consultation;
import com.example.medicalcabinet.Module.Medecin;
import com.example.medicalcabinet.Module.Patient;
import Singleton.SingletonConnexionDB;

public class ICabinetMetier implements ICabinetMetierImpl{

    private Connection connection = SingletonConnexionDB.getConnexion();

    //______________________Patient__________________________
    @Override
    public List<Patient> getPatients() {
        List<Patient> patients = new ArrayList<>();
        try {
            String query = "SELECT * FROM Professeur";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                patients.add(new Patient(
                    rs.getInt("id_patient"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("cin"),
                    rs.getString("telephone"),
                    rs.getString("email"),
                    rs.getDate("date_naissance")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public List<Patient> searchPatients(String motCle) {
        List<Patient> patients = new ArrayList<>();
        try {
            String query = "SELECT * FROM professeur WHERE nom LIKE ? OR prenom LIKE ? OR cin LIKE ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + motCle + "%");
            ps.setString(2, "%" + motCle + "%");
            ps.setString(3, "%" + motCle + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                patients.add(new Patient(
                        rs.getInt("id_patient"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("cin"),
                        rs.getString("telephone"),
                        rs.getString("email"),
                        rs.getDate("date_naissance")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public void addPatient(Patient patient) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Patient (nom, prenom, cin, telephone, email, date_naissance) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, patient.nom);
            ps.setString(2, patient.prenom);
            ps.setString(3, patient.cin);
            ps.setString(4, patient.telephone);
            ps.setString(5, patient.email);
            ps.setDate(6, new Date(patient.dateNaissance.getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePatient(int idPatient) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Patient WHERE id_patient = ?");
            ps.setInt(1, idPatient);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Consultation> getConsultationsByPatient(int idPatient) {
        List<Consultation> consultations = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Consultation WHERE id_patient = ?");
            ps.setInt(1, idPatient);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                consultations.add(new Consultation(
                        rs.getInt("id_consultation"),
                        rs.getDate("date_consultation")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }

    //______________________Medecin__________________________
    @Override
    public void addMedecin(Medecin medecin) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Medecin (nom, prenom, email, tel) VALUES (?, ?, ?, ?)");
            ps.setString(1, medecin.nom);
            ps.setString(2, medecin.prenom);
            ps.setString(3, medecin.email);
            ps.setString(4, medecin.tel);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Medecin> getMedecins() {
        List<Medecin> medecins = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Medecin");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                medecins.add(new Medecin(
                        rs.getInt("id_medecin"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("tel")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medecins;
    }

    @Override
    public void deleteMedecin(int idMedecin) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Medecin WHERE id_medecin = ?");
            ps.setInt(1, idMedecin);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Consultation> getConsultationsByMedecin(int idMedecin) {
        List<Consultation> consultations = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Consultation WHERE id_medecin = ?");
            ps.setInt(1, idMedecin);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                consultations.add(new Consultation(
                        rs.getInt("id_consultation"),
                        rs.getDate("date_consultation")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }

    //______________________Consultation__________________________
    @Override
    public void addConsultation(Consultation consultation ,int idPartient ,int idMedecin ) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Consultation (date_consultation, id_patient, id_medecin) VALUES (?, ?, ?)");
            ps.setDate(1, new Date(consultation.dateConsultation.getTime()));
            ps.setInt(2, idPartient);
            ps.setInt(3, idMedecin);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Consultation> getConsultations() {
        List<Consultation> consultations = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Consultation");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                consultations.add(new Consultation(
                        rs.getInt("id_consultation"),
                        rs.getDate("date_consultation")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }

    @Override
    public void deleteConsultation(int idConsultation) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Consultation WHERE id_consultation = ?");
            ps.setInt(1, idConsultation);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
