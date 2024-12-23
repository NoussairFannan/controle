package com.example.medicalcabinet.Controller;


import com.example.medicalcabinet.Metier.ICabinetMetier;
import com.example.medicalcabinet.Module.Consultation;
import com.example.medicalcabinet.Module.Medecin;
import com.example.medicalcabinet.Module.Patient;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class GestionCabinetController {
    @FXML
    private TextField textSearchPatient;
    @FXML
    private TableView<Patient> tableShowPatient;
    @FXML
    private TableView<Medecin> tableShowMedecin;
    @FXML
    private TableView<Consultation> tableShowConsultation;
    @FXML
    private TableColumn<Patient, Integer> idPatientShow;
    @FXML
    private TableColumn<Patient, String> nomPatientShow;
    @FXML
    private TableColumn<Patient, String> prenomPatientShow;
    @FXML
    private TableColumn<Medecin, Integer> idMedecinShow;
    @FXML
    private TableColumn<Medecin, String> nomMedecinShow;
    @FXML
    private TableColumn<Medecin, String> prenomMedecinShow;
    @FXML
    private TableColumn<Consultation, Integer> idConsultationShow;
    @FXML
    private TableColumn<Consultation, String> patientConsultationShow;
    @FXML
    private TableColumn<Consultation, String> medecinConsultationShow;
    @FXML
    private TableColumn<Consultation, Date> dateConsultationShow;

    private ICabinetMetier metier;

    @FXML
    private void initialize() {
        metier = new ICabinetMetier();

        // Configure Patient Table Columns
        idPatientShow.setCellValueFactory(new PropertyValueFactory<>("id_patient"));
        nomPatientShow.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomPatientShow.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        // Configure Medecin Table Columns
        idMedecinShow.setCellValueFactory(new PropertyValueFactory<>("id_medecin"));
        nomMedecinShow.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomMedecinShow.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        // Configure Consultation Table Columns
        idConsultationShow.setCellValueFactory(new PropertyValueFactory<>("id_consultation"));
        dateConsultationShow.setCellValueFactory(new PropertyValueFactory<>("date_consultation"));

        refreshTables();
    }

    private void refreshTables() {
        tableShowPatient.getItems().setAll(metier.getPatients());
        tableShowMedecin.getItems().setAll(metier.getMedecins());
        tableShowConsultation.getItems().setAll(metier.getConsultations());
    }

    public void addPatient(ActionEvent actionEvent) {
        Patient newPatient = Dialogue.addPatientDialog();
        if (newPatient != null) {
            metier.addPatient(newPatient);
            refreshTables();
        }
    }

    public void deletePatient(ActionEvent actionEvent) {
        Patient selectedPatient = tableShowPatient.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            metier.deletePatient(selectedPatient.idPatient);
            refreshTables();
        } else {
            showWarning("Veuillez sélectionner un patient à supprimer.");
        }
    }

    public void addMedecin(ActionEvent actionEvent) {
        Medecin newMedecin = Dialogue.addMedecinDialog();
        if (newMedecin != null) {
            metier.addMedecin(newMedecin);
            refreshTables();
        }
    }

    public void deleteMedecin(ActionEvent actionEvent) {
        Medecin selectedMedecin = tableShowMedecin.getSelectionModel().getSelectedItem();
        if (selectedMedecin != null) {
            metier.deleteMedecin(selectedMedecin.idMedecin);
            refreshTables();
        } else {
            showWarning("Veuillez sélectionner un médecin à supprimer.");
        }
    }

    public void addConsultation(ActionEvent actionEvent) {
        Consultation newConsultation = Dialogue.addConsultationDialog(metier.listPatients(), metier.listMedecins());
        if (newConsultation != null) {
            metier.addConsultation(newConsultation.dateConsultation, newConsultation.idPatient, newConsultation.idMedcin);
            refreshTables();
        }
    }

    public void deleteConsultation(ActionEvent actionEvent) {
        Consultation selectedConsultation = tableShowConsultation.getSelectionModel().getSelectedItem();
        if (selectedConsultation != null) {
            metier.deleteConsultation(selectedConsultation.idConsultation);
            refreshTables();
        } else {
            showWarning("Veuillez sélectionner une consultation à supprimer.");
        }
    }

    public void searchPatient(ActionEvent actionEvent) {
        String keyword = textSearchPatient.getText();
        if (keyword != null && !keyword.isEmpty()) {
            tableShowPatient.getItems().setAll(metier.searchPatients(keyword));
        } else {
            refreshTables();
        }
    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message);
        alert.showAndWait();
    }
}
