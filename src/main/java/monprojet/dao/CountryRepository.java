package monprojet.dao;

import java.util.List;

import monprojet.projection.PopulationByCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import monprojet.entity.City;
import monprojet.entity.Country;

// This will be AUTO IMPLEMENTED by Spring 

public interface CountryRepository extends JpaRepository<Country, Integer> {

    /**
     * Calcul la population d'un pays
     * @param id l'id du pays à vérifier
     * @return la population du pays
     */
    @Query(value = "SELECT SUM(population) as population " +
            "FROM Country co " +
            "INNER JOIN City ci ON co.id = ci.country_id " +
            "WHERE co.id = :id"
            , nativeQuery = true)
    public Integer getPopulationById(Integer id);

    /**
     * Calcul la population de chaque pays
     * @return les pays et leure population
     */
    @Query(value = "SELECT co.id as id, SUM(population) as population " +
            "FROM Country co " +
            "INNER JOIN City ci ON co.id = ci.country_id " +
            "GROUP BY id"
            , nativeQuery = true)
    public List<PopulationByCountry> getPopulation();
}
