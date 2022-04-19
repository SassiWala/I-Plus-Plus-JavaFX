/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.sql.SQLException;
import org.json.JSONArray;
import pidev.entities.Admin;
import pidev.entities.User;
import pidev.services.ServiceUser;
import pidev.utils.DataSource;
import pidev.utils.PasswordUtils;

/**
 *
 * @author 21695
 */
public class Pidev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        DataSource ds = new DataSource();
            JSONArray roles = new JSONArray();
            roles.put("hello world");

        User p1 = new User("Sassi", "Wala", "loopo@gmail.com", "Wala23912570", 23912570);
        /*  User p2 = new User("Chaima", "sassi", "choochoo@gmail.com", "wala23912570", 23912570);
        User p3 = new User("Oumaima", "sami", "moomoo@gmail.com", "wala23912570", 23912570);*/
        //User p1 = new User(18, "wala", "sassi", "looloo@gmail.com", "wala23912570", "BebJdid", 0, 23912570);
        ServiceUser s = new ServiceUser();

     //  s.ajouter(p1);
       //s.login("lopo@gmail.com", "Wala23912570");
      User u= s.getUser("65");
        System.out.println("roleee"+s.afficherAdmin());
        /*  s.add(p2)s;
       s.add(p3);*/
        // s.update(p1);
        //  s.delete(24);
        //  s.show();
        /*  s.updateRole(29, "ROLE_ADMIN");
        s.updateRole(30, "ROLE_ADMIN");
        s.trierUsersParNom();*/
        //System.out.println(s.rechercherUser("Wala"));

    }

}
