package ar.edu.unju.edm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unju.edm.model.Producto;

@Repository

public interface IProductoDAO extends CrudRepository<Producto, Integer>{

}
