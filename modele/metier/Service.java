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
public class Service {


    //  Initialisation des variables

    private int code;
    private String designation;
    private String email;
    private String telephone;


    // Constructor

    public Service(int code, String designation, String email, String telephone) {
        this.code = code;
        this.designation = designation;
        this.email = email;
        this.telephone = telephone;
    }


    // Getter

    public int getCode() {
        return code;
    }

    public String getDesignation() {
        return designation;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }


    // Setter

    public void setCode(int code) {
        this.code = code;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    // toString

    @Override
    public String toString() {
        return "Service{" + "code=" + code + ", designation=" + designation + ", email=" + email + ", telephone=" + telephone + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
        final Service other = (Service) obj;
        if (this.code != other.code) {
            return false;
        }
        if (!Objects.equals(this.designation, other.designation)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.telephone, other.telephone)) {
            return false;
        }
        return true;
    }


}
