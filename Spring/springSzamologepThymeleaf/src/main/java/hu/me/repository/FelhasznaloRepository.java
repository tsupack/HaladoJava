package hu.me.repository;

import hu.me.entity.Felhasznalo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FelhasznaloRepository extends CrudRepository<Felhasznalo, Integer> {
}

