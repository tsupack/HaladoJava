package hu.me.repository;

import hu.me.entity.Szamolas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SzamolasRepository extends CrudRepository<Szamolas, Integer> {
    List<Szamolas> findByMuvelet(String bemenet);
}
