package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import model.AggData;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AggDataRepository implements PanacheRepositoryBase<AggData, Integer> {
}
