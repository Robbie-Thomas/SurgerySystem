import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.List;

public class Test {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {



        SessionManager sessionManager = new SessionManager();
        sessionManager.setUpFactory();
        System.out.println(sessionManager.getPatient(1).getFirstName());




    }

}
