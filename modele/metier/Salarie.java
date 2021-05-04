/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.metier;

import java.util.Comparator;
import java.util.Date;

/**
 * @author Jules
 */
public class Salarie {

    //  Initialisation des variables
    private String code;
    private String nom;
    private String prenom;
    private Date dateNaiss;
    private Date dateEmbauche;
    private String fonction;
    private double tauxHoraire;
    private String situationFamiliale;
    private int nbrEnfants;

    private Categorie uneCategorie;
    private Service unService;

    // Constructor

    public Salarie() {
        this.code = null;
        this.nom = null;
        this.prenom = null;
        this.dateNaiss = null;
        this.dateEmbauche = null;
        this.fonction = null;
        this.tauxHoraire = 0;
        this.situationFamiliale = null;
        this.nbrEnfants = 0;
        this.uneCategorie = null;
        this.unService = null;
    }

    public Salarie(String code, String nom, String prenom, Date dateNaiss, Date dateEmbauche, String fonction, double tauxHoraire, String situationFamiliale, int nbrEnfants, Categorie uneCategorie, Service unService) {
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.dateEmbauche = dateEmbauche;
        this.fonction = fonction;
        this.tauxHoraire = tauxHoraire;
        this.situationFamiliale = situationFamiliale;
        this.nbrEnfants = nbrEnfants;
        this.uneCategorie = uneCategorie;
        this.unService = unService;
    }

    // Getter
    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public String getFonction() {
        return fonction;
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public String getSituationFamiliale() {
        return situationFamiliale;
    }

    public int getNbrEnfants() {
        return nbrEnfants;
    }

    public Service getUnService() {
        return unService;
    }

    public Categorie getUneCategorie() {
        return uneCategorie;
    }

    // Setter
    public void setCode(String code) {
        this.code = code;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public void setSituationFamiliale(String situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

    public void setNbrEnfants(int nbrEnfants) {
        this.nbrEnfants = nbrEnfants;
    }

    public void setUnService(Service unService) {
        this.unService = unService;
    }

    public void setUneCategorie(Categorie uneCategorie) {
        this.uneCategorie = uneCategorie;
    }

    // toString
    @Override
    public String toString() {
        return "Salarie{" + "code=" + code + ", nom=" + nom + ", prenom=" + prenom + ", dateNaiss=" + dateNaiss + ", dateEmbauche=" + dateEmbauche + ", fonction=" + fonction + ", tauxHoraire=" + tauxHoraire + ", situationFamiliale=" + situationFamiliale + ", nbrEnfants=" + nbrEnfants + ", unService=" + unService + ", uneCategorie=" + uneCategorie + '}';
    }

    public static Comparator<Salarie> SalarieNameComparator = new Comparator<Salarie>() {

        public int compare(Salarie s1, Salarie s2) {
            String StudentName1 = s1.getNom().toUpperCase();
            String StudentName2 = s2.getNom().toUpperCase();

            //ascending order
            return StudentName1.compareTo(StudentName2);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }
    };

}
