/**
 * A classe Hibernate contém os métodos básicos de integração do programa
 * com o banco de dados.
 * 
 */

package database;
/*
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * 
 */
public class Hibernate {
  /*  private Session session;
    private AnnotationConfiguration ac = new AnnotationConfiguration();

    private final SessionFactory sf = ac.configure().buildSessionFactory();

    public Session getSession() {
        return session;
    }


    /*
     * Abre a sessÃ£o do Hibernate para fazer as transaÃ§Ãµes.
     */
    /*public void beginTransaction()
    {
        session = sf.getCurrentSession();
        session.beginTransaction();
        session.setFlushMode(FlushMode.COMMIT);     //consultas SQL serao executadas no momento do commit
    }

    /*
     * Encerra a sessÃ£o ao fim das transaÃ§Ãµes.
     */
    /*public void endTransaction()
    {
        Transaction transaction = session.getTransaction();
        transaction.commit();
    }

    /*
     * Insere o objeto no banco de dados.
     */
    /*public void saveOnly(Object o)
    {
        session.save(o);
    }

    //cuidado ao usar, para deletar eh necessario o getObject
    public void delete (Object o)
    {
        session.delete(o);
    }

    /*
     *retorna o objeto do banco de dados, o load puxa do banco tendo como parametro o id da linha
     *obrigatorio usar antes de updateOnly ou delete
     */
    /*public Object getObject(Class classObj, int id)
    {
        Object obj = session.load(classObj, id);
        return obj;
    }

    /*
     * Atualiza o campo baseado no  id do metodo getObject.
     */
//    public void updateOnly(Object o)
//    {
//        session.update(o);
//    }
    /*
    public void merge(Object o)
    {
        session.merge(o);        
    }

    public List list(Class classObj)
    {
        Criteria cri = (Criteria) session.createCriteria(classObj);
        List<Object> l = cri.list();
        return l;
    }
*/
}
