package ru.geekbrains.spring.one.repositories;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.one.model.Product;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByPriceBetween(Integer min, Integer max, Pageable pageable);
    Page<Product> findAllByTitleLike(String title, Pageable pageable);

//    Optional<Product> findOneByTitle(String title);
//    List<Product> findAllByPriceLessThan(int price);
//    List<Product> findAllByPriceBetween(int min, int max);
//    List<Product> findAllByIdLessThanAndPriceGreaterThan(Long maxId, int minPrice);
//    @Query("select p from Product p where p.id = :id")
//    List<Product> hqlFindById(Long id);
}
