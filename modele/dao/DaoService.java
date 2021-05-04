package modele.dao;

import modele.metier.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author btssio
 */
public class DaoService {

    static Connection cnx = ConnexionBDD.cnx;

    static ArrayList<Service> tousLesServices = getAll();


    /**
     * Extraire l'ensemble des enregistrements de la table SERVICE
     *
     * @return liste d'objets de type Service
     */
    private static ArrayList<Service> getAll() {

        var lesServicesTrouves = new ArrayList<Service>();

        try {
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Service");
            while (rs.next()) {
                Service unService = new Service(
                        rs.getInt("Code"),
                        rs.getString("Designation"),
                        rs.getString("Email"),
                        rs.getString("Tel")
                );
                lesServicesTrouves.add(unService);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lesServicesTrouves;
    }


    /**
     * Obtient la liste des salariés
     */
    public static String[] getDesignationList() {


        var lesServicesFormate = new ArrayList<String>();
        lesServicesFormate.add("Tout les services");

        for (Service unService : tousLesServices) {
            lesServicesFormate.add(unService.getDesignation());
        }

        return lesServicesFormate.toArray(new String[0]);

    }

    /**
     * Rechercher un enregistrement dans la table SERVICE d'après son code (int)
     * et en faire un objet de type Service
     *
     * @param id code du service recherché
     * @return objet de type Service si trouvé dans la BDD, null sinon
     * @throws SQLException
     */
    public static Service getOneById(int id) throws SQLException {

        return tousLesServices.stream().filter(service -> service.getCode() == id).findFirst().orElse(null);

    }

    public static int getIndex(Service serviceRecherche) {


        int i = 0;
        while (!(tousLesServices.get(i).equals(serviceRecherche))) {
            i++;
        }

        return i;

    }

}
