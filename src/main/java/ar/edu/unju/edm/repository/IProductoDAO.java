package ar.edu.unju.edm.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unju.edm.model.Producto;

@Repository

public interface IProductoDAO extends CrudRepository<Producto, Integer>{
	public Optional<Producto> findByCodProducto(int codProducto);

}
