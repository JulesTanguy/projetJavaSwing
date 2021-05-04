package modele.dao;

import modele.metier.Categorie;
import modele.metier.Salarie;
import modele.metier.Service;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author btssio
 */
public class DaoSalarie {

    static Connection cnx = ConnexionBDD.cnx;

    static ArrayList<Salarie> lesSalaries = getAll();

    /**
     * Rechercher un enregistrement dans la table SALARIE d'après son code
     * (String) et en faire un objet de type Salarie
     *
     * @param id code du salarie recherché
     * @return objet de type Salarie si trouvé dans la BDD, null sinon
     */
    public static Salarie getOneById(String id) throws SQLException, IOException {

        Salarie salarieTrouve = null;

        PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM Salarie WHERE Code = ?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) salarieTrouve = enregToObjet(rs);
        return salarieTrouve;
    }

    /**
     * Extraire l'ensemble des enregistrements de la table SALARIE
     *
     * @return liste d'objets de type Salarie
     */
    private static ArrayList<Salarie> getAll() {
        var salarieTrouve = new ArrayList<Salarie>();

        try {
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Salarie");
            while (rs.next()) {
                Salarie unSalarie = enregToObjet(rs);
                salarieTrouve.add(unSalarie);
            }
            salarieTrouve.sort(Salarie.SalarieNameComparator);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(DaoSalarie.class.getName()).log(Level.SEVERE, null, ex);
        }

        return salarieTrouve;
    }

    public static ArrayList<Salarie> getAllByService(int ServiceRecherche) {
        var salarieTrouve = new ArrayList<Salarie>();

        if (ServiceRecherche != 0) {

            for (Salarie unSalarie : lesSalaries) {
                Service leService = unSalarie.getUnService();
                int codeService = leService.getCode();
                if (ServiceRecherche == codeService) salarieTrouve.add(unSalarie);
            }

/*            try {
                PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM Salarie WHERE CodeServ = ?");
                pstmt.setString(1, valueOf(idService));
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Salarie unSalarie = enregToObjet(rs);
                    salarieTrouve.add(unSalarie);
                }
            } catch (SQLException | IOException ex) {
                Logger.getLogger(DaoSalarie.class.getName()).log(Level.SEVERE, null, ex);
            }*/

            return salarieTrouve;
        } else {
            return lesSalaries;
        }
    }


    public static ArrayList<String> getAllSalarieNameByService(int idService) {

        ArrayList<String> salarieTrouve = new ArrayList<>();
        var src = getAllByService(idService);

        for (Salarie unSalarie : src) salarieTrouve.add(unSalarie.getNom() + " " + unSalarie.getPrenom());

        return salarieTrouve;
    }

    public static void deleteSalarie(Salarie unSalarie) {

        var codeSalarie = unSalarie.getCode();

        try {
            var pstmt1 = cnx.prepareStatement("DELETE FROM Suivre WHERE CodeSal = ?");
            pstmt1.setString(1, codeSalarie);
            pstmt1.executeUpdate();

            var pstmt2 = cnx.prepareStatement("DELETE FROM Salarie WHERE CODE = ?");
            pstmt2.setString(1, codeSalarie);
            pstmt2.executeUpdate();

            lesSalaries.remove(unSalarie);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Échec de la connexion au serveur", "Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public static void ajouterSalarie(String Code, String Nom, String Prenom, String DateNaiss, String DateEmbauche, String Fonction,
                                      String TauxHoraire, String situationFamiliale, String NbrEnfants, String NumCat, String CodeServ) throws SQLException {

        PreparedStatement pstmt = cnx.prepareStatement(
                "INSERT INTO Salarie (Code, Nom,Prenom,DateNaiss,DateEmbauche,Fonction,TauxHoraire,situationFamiliale,NbrEnfants,NumCat,CodeServ) VALUES ('?', '?', '?', '?', '?', '?', ?, '?', ?, '?', ?)");
        pstmt.setString(1, Code);
        pstmt.setString(2, Nom);
        pstmt.setString(3, Prenom);
        pstmt.setString(4, DateNaiss);
        pstmt.setString(5, DateEmbauche);
        pstmt.setString(6, Fonction);
        pstmt.setString(7, TauxHoraire);
        pstmt.setString(8, situationFamiliale);
        pstmt.setString(9, NbrEnfants);
        pstmt.setString(10, NumCat);
        pstmt.setString(11, CodeServ);
        pstmt.executeUpdate();
    }

    /**
     * Transforme un enregistrement de la table SALARIE en instance de Salarie
     *
     * @param rs jeu d'enregistrements ; l'enregistrement courant est concerné
     * @return instance de Salarie
     */
    public static Salarie enregToObjet(ResultSet rs) throws SQLException, IOException {
        Salarie unSalarie;
        // Récupération du service du salarié
        Service unService = DaoService.getOneById(rs.getInt("CodeServ"));
        // Récupération de la catégorie du salarié
        Categorie uneCategorie = DaoCategorie.getOneById(rs.getString("NumCat"));
        unSalarie = new Salarie(
                rs.getString("Code"),
                rs.getString("Nom"),
                rs.getString("Prenom"),
                rs.getDate("DateNaiss"),
                rs.getDate("DateEmbauche"),
                rs.getString("Fonction"),
                rs.getDouble("TauxHoraire"),
                rs.getString("situationFamiliale"),
                rs.getInt("NbrEnfants"),
                uneCategorie,
                unService
        );
        return unSalarie;
    }

}
