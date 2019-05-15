package nightmarethreatreis.com.github.mvp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	EntityManagerFactory fact = Persistence.createEntityManagerFactory("mvp");
    	
    	EntityManager em = fact.createEntityManager();
    	
    	em.createQuery("SELECT t FROM Test t").getResultList().forEach(System.out::println);
    	
    	em.close();
    }
}
