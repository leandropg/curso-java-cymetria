package com.supermercado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import com.supermercado.excepcion.ProductoNoDefinidoExcepcion;
import com.supermercado.modelo.Producto;
import com.supermercado.modelo.Seccion;
import com.supermercado.sql.SupermercadoSQL;

/**
 * Define un Supermercado con secciones y productos 
 * @author Leandro Perez Guatibonza
 *
 * Copyright 2016-2020 Leandro Perez Guatibonza
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */
public class Supermercado
{
	/**
	 * Nombre del supermercado
	 */
	private String nombre;

	/**
	 * Secciones del supermercado
	 */
	private ArrayList<Seccion> listaSecciones;
	
	/**
	 * Inventario de productos del supermercado
	 */
	private ArrayList<Producto> listaProductos;
	
	/**
	 * Manejo del Stock del supermercado
	 */
	private HashMap<String, Integer> stockSupermercado;
	
	/**
	 * Crea una instancia de la clase supermercado
	 * @param nombreSupermercado Nombre del supemercado
	 */
	public Supermercado()
	{
		// Inicializa la lista de secciones del supermercado
		listaSecciones = new ArrayList<Seccion>();

		// Inicializa la lista de productos
		listaProductos = new ArrayList<Producto>();
		
		// Inicializa el stock del supermercado
		stockSupermercado = new HashMap<String, Integer>();
		
		// Inicializacion del nombre del supermercado
		nombre = "ALMACEN DE VIVERES BOGOTA";
		
		// Cargar Datos desde la base de datos
		cargarBaseDatos();
	}
	
	/**
	 * Carga los datos de la base de datos
	 */
	public void cargarBaseDatos()
	{
		Connection conexion = null;
		Statement instruccion = null;
		ResultSet resultado = null;
		String SQL = null;
		
		try
		{
			// Carga el driver de MySQL
			Class.forName("com.mysql.jdbc.Driver");
			
			// Inicia una conexion con la base de datos
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/supermercado", "root", "root");
			
			// Crea el objeto para realizar las consultas a la base de datos			
			instruccion = conexion.createStatement();
			
			// Configura la sentencia SQL de la Tabla configuracion
			SQL = "SELECT * FROM configuracion";
			
			// Realiza la consulta y obtiene el resultado
			resultado = instruccion.executeQuery(SQL);
			
			// Procesa el resultado
			while( resultado.next() )
			{
				nombre = resultado.getString("nombreSupermercado");									
			}
			
			// Configura la sentencia SQL de la Tabla secciones
			SQL = "SELECT * FROM secciones";
			
			// Realiza la consulta y obtiene el resultado
			resultado = instruccion.executeQuery(SQL);
			
			// Procesa el resultado
			while( resultado.next() )
			{
				// Crea la seccion y la almacena en la lista
				listaSecciones.add(new Seccion(resultado.getString("nombreSeccion"), resultado.getString("descripcionSeccion"), resultado.getInt("numeroSeccion")));				
			}
			
			// Ordena la lista de secciones
			Collections.sort(listaSecciones);
			
			// Configura la sentencia SQL de la Tabla productos
			SQL = "SELECT nombreProducto, descripcionProducto, numeroSeccion, precioProducto FROM productos p, secciones s WHERE p.idSeccion = s.idSeccion";
			
			// Realiza la consulta y obtiene el /resultado
			resultado = instruccion.executeQuery(SQL);
			
			// Procesa el resultado
			while( resultado.next() )
			{
				// Crea el producto y la almacena en el arreglo
				listaProductos.add(new Producto(resultado.getString("nombreProducto"), resultado.getString("descripcionProducto"), buscarSeccionPorNumero(resultado.getInt("numeroSeccion")), resultado.getInt("precioProducto")));
			}

			// Ordena la lista de productos
			Collections.sort(listaProductos);
			
			// Configura la sentencia SQL de la Tabla stock
			SQL = "SELECT nombreProducto, cantidadProducto FROM stock s, productos p WHERE s.idProducto = p.idProducto";
			
			// Realiza la consulta y obtiene el resultado
			resultado = instruccion.executeQuery(SQL);
			
			// Procesa el resultado
			while( resultado.next() )
			{
				// Crea el stock con los datos de la base de datos
				stockSupermercado.put(resultado.getString("nombreProducto"), resultado.getInt("cantidadProducto"));
			}
			
			// Cierra la conexion
			conexion.close();
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("No se encontro el driver de la base de datos MySQL. " + e.getMessage());
		}
		catch (SQLException e)
		{
			System.out.println("Se produjo un error de SQL. " + e.getMessage());
		}
	}
	
	/**
	 * Cambia el nombre del supermercado
	 */
	public void cambiarNombreSupermercado()
	{
		// Crea el objeto Scanner para interactuar con la consola
		Scanner scanner = new Scanner(System.in);

		// Obtiene el nombre del supermercado
		System.out.print("Ingrese el nombre del Supermercado: ");
		nombre = scanner.nextLine();
	}
	
	/**
	 * Adiciona una seccion al supermercado
	 * @param nombre Nombre de la seccion
	 * @param descripcion Descripcion de la seccion
	 * @param numero Numero de la seccion
	 */
	public void adicionarSeccion(String nombre, String descripcion, int numero)
	{
		// Crea la seccion y la almacena en la lista
		listaSecciones.add(new Seccion(nombre, descripcion, numero));
		
		// Ordena la lista de secciones
		Collections.sort(listaSecciones);
		
		// Guarda la seccion en la base de datos
		SupermercadoSQL.guardarSeccionBaseDatos(nombre, descripcion, numero);
	}

	/**
	 * Busca una seccion en el grupo de secciones por el numero de seccion
	 * @param numero Numero de la seccion a buscar
	 * @return Seccion encontrada 
	 */
	public Seccion buscarSeccionPorNumero( int numero )
	{
		Seccion seccionEncontrada = null;
		
		for(Seccion seccion : listaSecciones )
		{
			if( seccion.getNumero() == numero )
			{
				seccionEncontrada = seccion;
			}
		}
		return seccionEncontrada;
	}
	
	/**
	 * Adiciona un producto al supermercado
	 * @param nombre Nombre del producto
	 * @param descripcion Descripcion del producto
	 * @param seccion Seccion del producto
	 * @param precio Precio del producto
	 */
	public void adicionarProducto(String nombre, String descripcion, Seccion seccion, int precio)
	{
		// Crea el producto y la almacena en el arreglo
		listaProductos.add(new Producto(nombre, descripcion, seccion, precio));
		
		// Ordena la lista de productos
		Collections.sort(listaProductos);
		
		// Guarda el producto en la base de datos
		SupermercadoSQL.guardarProductoBaseDatos(nombre, descripcion, seccion.getNumero(), precio);
	}

	/**
	 * Verifica si el producto esta definido
	 * @param nombre Nombre del producto
	 * @return Verdadero si esta definido y falso si no lo esta
	 */
	public boolean verificarDefinicionProducto( String nombre )
	{		
		for(Producto producto : listaProductos )
		{
			if( producto.getNombre().equals(nombre) )
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adiciona stock al supermercado 
	 * @param nombreProducto Nombre del Producto
	 * @param cantidadProducto Cantida del Producto
	 * @throws ProductoNoDefinidoExcepcion ProductoNoDefinidoExcepcion Excepcion de Producto No Definido
	 */
	public void adicionarStock(String nombreProducto, int cantidadProducto) throws ProductoNoDefinidoExcepcion{

		// Variables Locales de Adicionar Stock
		boolean productoDefinido = false; 

		// Busca si el producto esta definido
		productoDefinido = verificarDefinicionProducto(nombreProducto);

		if(productoDefinido)
		{
			// Verifica si el producto ya existe
			if(stockSupermercado.containsKey(nombreProducto)) {
				
				// Obtiene el valor del stock actual
				int valorStockActual = stockSupermercado.get(nombreProducto); 
				
				// Adiciona la cantidad de productos deseada
				valorStockActual += cantidadProducto;

				// Actualiza el valor en el stock
				stockSupermercado.put(nombreProducto, new Integer(valorStockActual));
				
				// Actualiza el stock en la base de datos
				SupermercadoSQL.actualizarStockBaseDatos(nombreProducto, valorStockActual);
				
			} else {
				
				// Ingresa el valor en el stock
				stockSupermercado.put(nombreProducto, new Integer(cantidadProducto));
				
				// Guarda el stock en la base de datos
				SupermercadoSQL.guardarStockBaseDatos(nombreProducto, cantidadProducto);
			}
		}
		else
		{
			throw new ProductoNoDefinidoExcepcion("El producto a ingresar no ha sido definido!!!");
		}
	}
	
	/**
	 * Retira stock del supermercado
	 * @param nombreProducto Nombre del Producto
	 * @param cantidadProducto Cantidad del Producto
	 * @throws ProductoNoDefinidoExcepcion Excepcion de Producto No Definido
	 */
	public void retirarStock(String nombreProducto, int cantidadProducto) throws ProductoNoDefinidoExcepcion{

		// Variables Locales de Retirar Stock
		boolean productoDefinido = false; 

		// Busca si el producto esta definido
		productoDefinido = verificarDefinicionProducto(nombreProducto);

		if(productoDefinido)
		{
			// Verifica si el producto ya existe
			if(stockSupermercado.containsKey(nombreProducto)) {
				
				// Obtiene el valor del stock actual
				int valorStockActual = stockSupermercado.get(nombreProducto); 
				
				// Verifica que la cantidad solicitada exista
				if(valorStockActual >= cantidadProducto) {

					// Resta la cantidad de productos deseada
					valorStockActual -= cantidadProducto;

					// Actualiza el valor en el stock
					stockSupermercado.put(nombreProducto, new Integer(valorStockActual));

					// Actualiza el stock en la base de datos
					SupermercadoSQL.actualizarStockBaseDatos(nombreProducto, valorStockActual);
					
				} else {

					throw new ProductoNoDefinidoExcepcion("No existe la cantidad solicitada del producto!!!");
				}
				
			} else {

				throw new ProductoNoDefinidoExcepcion("No hay existencias del producto a retirar!!!");	
			}
		}
		else
		{
			throw new ProductoNoDefinidoExcepcion("El producto a retirar no ha sido definido!!!");	
		}
	}	

	/**
	 * Retorna el nombre del supermercado
	 * @return Nombre del supermercado
	 */
	public String getNombre()
	{
		return nombre;
	}

	/**
	 * Configura el nombre del supermercado
	 * @param nombre Nombre del supemercado
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	/**
	 * Retorna las secciones del supermercado
	 * @return Secciones del supemercado
	 */
	public ArrayList<Seccion> getListaSecciones()
	{
		return listaSecciones;
	}

	/**
	 * Configura la secciones del supermercado
	 * @param grupoSecciones Secciones del supemercado
	 */
	public void setListaSecciones(ArrayList<Seccion> listaSecciones)
	{
		this.listaSecciones = listaSecciones;
	}

	/**
	 * Retorna el inventario de productos del supermercado
	 * @return Inventario de productos
	 */
	public ArrayList<Producto> getListaProductos()
	{
		return listaProductos;
	}

	/**
	 * Configura el inventario de productos del supermercado
	 * @param inventarioProductos Inventario de productos
	 */
	public void setListaProductos(ArrayList<Producto> listaProductos)
	{
		this.listaProductos = listaProductos;
	}

	/**
	 * Retorna el HashMap del Stock del supemercado
	 * @return
	 */
	public HashMap<String, Integer> getStockSupermercado() {
		return stockSupermercado;
	}

	/**
	 * Configura el HashMap del Stock del supemercado
	 * @param stockSupermercado
	 */
	public void setStockSupermercado(HashMap<String, Integer> stockSupermercado) {
		this.stockSupermercado = stockSupermercado;
	}
}