package ar.edu.unju.edm.controller;

import java.time.LocalDate;
import java.time.Period;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.service.IClienteService;

@Controller
public class ClienteController{
	private static final Log LOGGER = LogFactory.getLog(ClienteController.class);
	
	@Autowired
	@Qualifier("implementacionMYSQL")
	
	IClienteService clienteService;

	@GetMapping("/cliente/mostrar")
	public String cargarCliente(Model model) {
		LOGGER.info("METHOD: ingresando el metodo cargar");
		model.addAttribute("unCliente", clienteService.crearCliente());
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		return("cliente");
	}

	@GetMapping("/cliente/editar/{idDocumento}")
	public String editarCliente(Model model, @PathVariable(name="idDocumento") int id) throws Exception {
		try {
			Cliente clienteEncontrado = clienteService.encontrarUnCliente(id);

			model.addAttribute("unCliente", clienteEncontrado);	
			model.addAttribute("editMode", "true");
			
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unCliente", clienteService.crearCliente());
			model.addAttribute("editMode", "false");
		}
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		return("cliente");
	}
	
	@PostMapping("/cliente/guardar")
	public String guardarNuevoProducto(@Valid @ModelAttribute("unCliente") Cliente nuevoCliente, BindingResult resultado ,Model model) {
		
		if (resultado.hasErrors()) 
		{
			model.addAttribute("unCliente", nuevoCliente);
			model.addAttribute("clientes", clienteService.obtenerTodosClientes());
			return("cliente");
		}
		else 
		{
			//deberia tener un try por si ocurre algun error
			LOGGER.info("METHOD: ingresando el metodo Guardar");
			clienteService.guardarCliente(nuevoCliente);		
			LOGGER.info("TamaÃ±o del Listado: "+ clienteService.obtenerTodosClientes().size());
			trabajarConFechas();
			return "redirect:/cliente/mostrar";
		}
	}
	
	@PostMapping("/cliente/modificar")
	public String modificarCliente(@ModelAttribute("unCliente") Cliente clienteModificado, Model model) {
		try {
			clienteService.modificarCliente(clienteModificado);
			model.addAttribute("unCliente", new Cliente());				
			model.addAttribute("editMode", "false");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unCliente", clienteModificado);			
			model.addAttribute("clientes", clienteService.obtenerTodosClientes());
			model.addAttribute("editMode", "true");
		}
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		
		return("cliente");
	}
	
	@GetMapping("/cliente/eliminarCliente/{id}")
	public String eliminarCliente(Model model, @PathVariable(name="id") int id) {
		LOGGER.info("METHOD: ingresando el metodo Eliminar");
		try {
			clienteService.eliminarCliente(id);			
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/cliente/mostrar";
	}
	
	public void trabajarConFechas() {
		//algunas cosas con fecha;
		//obtengo tres fechas
		LocalDate fecha1 = clienteService.obtenerTodosClientes().get(0).getFechaNacimiento();
		LocalDate fecha2 = LocalDate.now();
		@SuppressWarnings("unused")
		LocalDate fecha3 = LocalDate.of(2021, 4, 22);
		
		//calculo el período entre dos de ellas
		Period periodo = Period.between(fecha1,fecha2);
		int dias = periodo.getDays();		
		System.out.println("dias: "+dias);
	}
}
