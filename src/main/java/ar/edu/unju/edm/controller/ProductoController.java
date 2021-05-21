package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.service.IProductoService;

@Controller
public class ProductoController {
	

	@Qualifier("implementacionMYSQLProducto")
	@Autowired
	
	IProductoService productoService;
	
	@GetMapping("/producto/mostrar")
	public String cargarProducto(Model model) {
		model.addAttribute("unProducto", productoService.obtenerProductoNuevo());
		model.addAttribute("productos", productoService.obtenerTodosProductos());
		return("producto");
	}
	

	
	@PostMapping("/producto/guardar")
	public String guardarNuevoProducto(@ModelAttribute("unProducto") Producto nuevoProducto, Model model) {
		productoService.guardarProducto(nuevoProducto);
		System.out.println(productoService.obtenerTodosProductos().get(0).getMarca()); //linea de control para saber si se mando bien el producto 
		
		
		return "redirect:/producto/mostrar";
		
	}
	
	@GetMapping("/volver")
	public String cargarNuevoProducto(Model model) {
		
		return("redirect:/producto");
	}
}
