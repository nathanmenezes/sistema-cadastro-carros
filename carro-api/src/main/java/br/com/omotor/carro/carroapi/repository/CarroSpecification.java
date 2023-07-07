package br.com.omotor.carro.carroapi.repository;

import br.com.omotor.carro.carroapi.model.CarroModel;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.function.Predicate;

public class CarroSpecification {

    public static Specification<CarroModel> marca(String marca){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("marca"), marca));
    }

    public static Specification<CarroModel> modelo(String modelo){
        String likePattern = "%" + modelo.toLowerCase() + "%";
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("modelo"), likePattern));
    }

    public static Specification<CarroModel> ano(String ano){
        return (((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("ano"), ano)));
    }
}
