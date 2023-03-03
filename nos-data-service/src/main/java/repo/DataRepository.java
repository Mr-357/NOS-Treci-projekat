package repo;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import model.Data;

import javax.enterprise.context.ApplicationScoped;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class DataRepository implements PanacheRepositoryBase<Data, UUID> {
    public List<Data> findByTime(Date timestamp) {
        return list("timestamp", timestamp);
    }
    public List<Data> getRange(Date startTime, Date endTime){
        System.out.println(startTime+" "+endTime);
        return getEntityManager().createQuery("FROM Data as d WHERE d.timestamp BETWEEN :startTime AND :endTime")
                .setParameter("startTime",startTime)
                .setParameter("endTime",endTime)
                .getResultList();
    }
}
