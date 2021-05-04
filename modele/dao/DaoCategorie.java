package modele.dao;

import modele.metier.Categorie;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author btssio
 */
public class DaoCategorie {

    static Connection cnx = ConnexionBDD.cnx;

    static ArrayList<Categorie> touteLesCategories = getAll();


    /**
     * Rechercher un enregistrement dans la table CATEGORIE d'après son code
     * (String) et en faire un objet de type Categorie
     *
     * @param id code de la catégorie recherchée
     * @return objet de type Categorie si trouvé dans la BDD, null sinon
     * @throws SQLException
     * @throws java.io.IOException
     */
    public static Categorie getOneById(String id) throws SQLException, IOException {

        return touteLesCategories.stream().filter(cat -> cat.getCode().equals(id)).findFirst().orElse(null);

    }


    /**
     * Extraire l'ensemble des enregistrements de la table CATEGORIE
     *
     * @return
     */
    private static ArrayList<Categorie> getAll() {
        ArrayList<Categorie> lesCategorieTrouvees = new ArrayList<>();

        try {
            PreparedStatement pstmt = null;
            pstmt = cnx.prepareStatement("SELECT * FROM Categorie");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Categorie uneCategorie = new Categorie(
                        rs.getString("Code"),
                        rs.getString("Libelle"),
                        rs.getDouble("salaireMini"),
                        rs.getString("CaisseDeRetraite"),
                        rs.getInt("PrimeResultat")
                );
                lesCategorieTrouvees.add(uneCategorie);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return lesCategorieTrouvees;
    }

    public static String[] getLibelleList() {

        ArrayList<String> lesCategorieFormate = new ArrayList<>();

        for (Categorie uneCategorie : touteLesCategories) lesCategorieFormate.add(uneCategorie.getLibelle());

        return lesCategorieFormate.toArray(new String[0]);

    }

    public static int getIndex(Categorie categorieRecherche) {

        int i = 0;
        while (!(touteLesCategories.get(i).equals(categorieRecherche))) {
            i++;
        }

        return i;

    }
}
