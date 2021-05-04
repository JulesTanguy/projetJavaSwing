/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.metier;

import java.util.Objects;


/**
 * @author Jules
 */
public class Categorie {


    //  Initialisation des variables

    private String code;
    private String libelle;
    private double salaireMini;
    private String caisseRetraite;
    private int prime;


    // Constructor

    public Categorie(String code, String libelle, double salaireMini, String caisseRetraite, int prime) {
        this.code = code;
        this.libelle = libelle;
        this.salaireMini = salaireMini;
        this.caisseRetraite = caisseRetraite;
        this.prime = prime;
    }


    // Getter

    public String getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    public double getSalaireMini() {
        return salaireMini;
    }

    public String getCaisseRetraite() {
        return caisseRetraite;
    }

    public int getPrime() {
        return prime;
    }


    // Setter

    public void setCode(String code) {
        this.code = code;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setSalaireMini(double salaireMini) {
        this.salaireMini = salaireMini;
    }

    public void setCaisseRetraite(String caisseRetraite) {
        this.caisseRetraite = caisseRetraite;
    }

    public void setPrime(int prime) {
        this.prime = prime;
    }


    // toString

    @Override
    public String toString() {
        return "Categorie{" + "code=" + code + ", libelle=" + libelle + ", salaireMini=" + salaireMini + ", caisseRetraite=" + caisseRetraite + ", prime=" + prime + '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categorie other = (Categorie) obj;
        if (Double.doubleToLongBits(this.salaireMini) != Double.doubleToLongBits(other.salaireMini)) {
            return false;
        }
        if (this.prime != other.prime) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        if (!Objects.equals(this.caisseRetraite, other.caisseRetraite)) {
            return false;
        }
        return true;
    }


}
