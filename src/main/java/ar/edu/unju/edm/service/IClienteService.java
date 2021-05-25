
package ar.edu.unju.edm.service;

import java.util.List;

import ar.edu.unju.edm.model.Cliente;

public interface IClienteService {
	public void guardarCliente(Cliente unCliente);
	public Cliente crearCliente();
	public List<Cliente> obtenerTodosClientes();
	public Cliente encontrarUnCliente(int dni) throws Exception;
	public void modificarCliente(Cliente clienteModificado) throws Exception;
	public void eliminarCliente(int id) throws Exception;

}