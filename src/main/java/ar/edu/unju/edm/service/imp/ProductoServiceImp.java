package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.service.IProductoService;

@Service

public class ProductoServiceImp implements IProductoService {
	//como se hace la solucion del problema
	//guardar en Array
	//guardar en una BD MySQL
	//guardar en una DB Oracle
	
	@Autowired
	Producto unProducto;
	
	//creando una estructura que guardará todos los productos
	ArrayList<Producto> listaDeProductos = new ArrayList<Producto>();
	
	@Override
	public void guardarProducto(Producto unProducto) {
		// TODO Auto-generated method stub

		System.out.println(unProducto.getNombre());
		listaDeProductos.add(unProducto);

		System.out.println(listaDeProductos.size());
	}

	@Override
	public void modificarProducto(Producto productoAModificar) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Producto obtenerUnProducto(String nombreProducto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Producto> obtenerTodosProductos() {
		// TODO Auto-generated method stub
		return listaDeProductos;
	}

	@Override
	public Producto obtenerProductoNuevo() {
		// TODO Auto-generated method stub
		return unProducto;
	}

	@Override
	public Producto obtenerUltimoProducto() {
		// TODO Auto-generated method stub
		int i = listaDeProductos.size() - 1;
		return listaDeProductos.get(i);
	}

	@Override
	public Producto encontrarUnProducto(int cod) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarProducto(int id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object crearProducto() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
