package com.supermercado;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.supermercado.excepcion.ProductoNoDefinidoExcepcion;
import com.supermercado.modelo.Producto;
import com.supermercado.modelo.Seccion;

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
		
		// Cargar Datos desde Archivo
		cargarDatosArchivo();
	}
	
	/**
	 * Inicializa el stock por defecto
	 */
	public void cargarDatosArchivo()
	{
		File file = null;
		BufferedReader bufferReader = null;
		
		try
		{
			// Crea un apuntador File
			file = new File( "C:\\Users\\Leandro\\supermercado.txt" );
			
			if (file.exists())
			{			    
				// Crea un buffer de entrada
				bufferReader = new BufferedReader( new FileReader( file ) );
				
				// Variable de recepcion
				String linea;
				
				// Lee todo el archivo
				while((linea = bufferReader.readLine()) != null)
			    {
					String tipoRegistro = linea.substring(0, 3);
					String datosRegistro = linea.substring(4);
										
					if( tipoRegistro.equals("NOM"))
					{
						nombre = datosRegistro;
					}
					
					if( tipoRegistro.equals("SEC"))
					{
						// Crea la seccion con los datos del archivo 
						String [] datosSeccion = datosRegistro.split(",");
						adicionarSeccion(datosSeccion[0], datosSeccion[1], Integer.parseInt(datosSeccion[2]));
					}
					
					if( tipoRegistro.equals("PRD"))
					{
						// Crea el producto con los datos del archivo 
						String [] datosProducto = datosRegistro.split(",");
						adicionarProducto(datosProducto[0], datosProducto[1], buscarSeccionPorNumero(Integer.parseInt(datosProducto[2])),Integer.parseInt(datosProducto[3]));	
					}

					if( tipoRegistro.equals("STK"))
					{
						// Crea el stock con los datos del archivo 
						String [] datosStock = datosRegistro.split(",");
						stockSupermercado.put(datosStock[0], new Integer(Integer.parseInt(datosStock[1])));
					}					
			    }
			}
			else
			{
				System.out.println("No existe el archivo " + file.getName() + " para cargar los datos.");
			}			
		}
		catch(IOException e)
		{
			System.out.println("ERROR. Se presento un error al leer el archivo de datos. " + e.getMessage());
		}
		finally
		{
			try
			{
				if( bufferReader!= null )
				{
					// Cierra el buffer de lectura
					bufferReader.close();
				}
			}
			catch(IOException e)
			{
				System.out.println("ERROR. Se presento un error al cerrar el buffer de lectura del archivo. " + e.getMessage());
			}
		}
	}

	/**
	 * Inicializa el stock por defecto
	 */
	public void guardarDatosArchivo()
	{
		File file = null;
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		
		try
		{
			// Crea un apuntador File
			file = new File( "C:\\Users\\Leandro\\supermercado.txt" );
		
			// Pregunta si el archivo existe
			if(file.exists())
			{
				// Borra el archivo anterior
				if(file.delete())
				{
					// Crea el nuevo archivo
					file.createNewFile();
				}
				else
				{
					System.out.println("No se pudo borrar el archivo.");
				}
			}

			// Crea un fileWriter 
			fileWriter = new FileWriter( file );
			
			// Crea un buffer de entrada
			
			// Crea un PrintWriter para imprimir lineas
			printWriter = new PrintWriter(fileWriter);
			
			// Guarda el nombre del supemercado
			printWriter.println("NOM," + nombre);
			
			// Guarda las secciones
			for(Seccion seccion : listaSecciones)
			{
				printWriter.println("SEC," + seccion.getNombre() + "," + seccion.getDescripcion() + "," + seccion.getNumero() );
			}
			
			// Guarda los productos
			for(Producto producto : listaProductos)
			{
				printWriter.println("PRD," + producto.getNombre() + "," + producto.getDescripcion() + "," + producto.getSeccion().getNumero() + "," + producto.getPrecio() );
			}
			
			// Guarda el Stock
			for(String producto : stockSupermercado.keySet())
			{
				printWriter.println("STK," + producto + "," + stockSupermercado.get(producto));
			}		

			System.out.println("Datos Almacenados Exitosamente");
		}
		catch(IOException e)
		{
			System.out.println("ERROR. Se presento un error al escribir el archivo de datos. " + e.getMessage());
		}
		finally
		{
			try
			{
				if( fileWriter != null )
				{
					// Cierra el fileWriter
					fileWriter.close();
				}
								
				if( printWriter != null )
				{
					// Cierra el printWriter
					printWriter.close();
				}
			}
			catch(IOException e)
			{
				System.out.println("ERROR. Se presento un error al cerrar el buffer de escritura del archivo. " + e.getMessage());
			}
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
	 * Crea una nueva seccion del supermercado
	 * @param scanner
	 */
	public void crearNuevaSeccion()
	{
		// Variables Locales de Seccion
		String nombreSeccion = null;
		String descripcionSeccion = null;
		int numeroSeccion = 0;
		boolean usuarioDigitoNumero = false;

		// Crea el objeto Scanner para interactuar con la consola
		Scanner scanner = new Scanner(System.in);
		
		// Obtiene los datos de la seccion 
		System.out.print("Ingrese el nombre de la Seccion: ");
		nombreSeccion = scanner.nextLine();
		System.out.print("Ingrese la descripcion de la Seccion: ");
		descripcionSeccion = scanner.nextLine();

		// Desactiva la bandera de validacion
		usuarioDigitoNumero = false;
		
		// Ciclo de obtencion de la seccion del producto
		while(!usuarioDigitoNumero)
		{
			try
			{
				System.out.print("Ingrese el numero de la Seccion: ");
				numeroSeccion = scanner.nextInt();
				scanner.nextLine();
				usuarioDigitoNumero = true;
			}
			catch(InputMismatchException e)
			{
				System.out.print("Error. Ingrese un numero para la Seccion del Producto. ");
				usuarioDigitoNumero = false;
				scanner.nextLine();
			}
		}

    	// Adiciona la seccion
		adicionarSeccion(nombreSeccion, descripcionSeccion, numeroSeccion);		
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
	 * Crea una nuevo producto del supermercado
	 * @param scanner
	 */
	public void crearNuevoProducto()
	{
		// Variables Locales de Producto
		String nombreProducto = null;
		String descripcionProducto = null;
		int numeroSeccionProducto = 0;
		Seccion seccionProducto = null;
		int precioProducto = 0;
		boolean validacionNumero = false;

		// Crea el objeto Scanner para interactuar con la consola
		Scanner scanner = new Scanner(System.in);
		
		// Obtiene los datos del producto
		System.out.print("Ingrese el nombre del Producto: ");
		nombreProducto = scanner.nextLine();
		System.out.print("Ingrese la descripcion del Producto: ");
		descripcionProducto = scanner.nextLine();
		
		// Desactiva la bandera de validacion
		validacionNumero = false;
		
		// Ciclo de obtencion de la seccion del producto
		while(!validacionNumero)
		{
			try
			{
				System.out.print("Ingrese el numero de la Seccion del Producto: ");
				numeroSeccionProducto = scanner.nextInt();
				scanner.nextLine();
				
				// Busca la seccion del producto
				seccionProducto = buscarSeccionPorNumero(numeroSeccionProducto);
				
				if(seccionProducto != null)
				{
					validacionNumero = true;
				}
				else
				{
					System.out.print("Error. Ingrese un numero de Seccion que exista. ");
					validacionNumero = false;
				}
			}
			catch(InputMismatchException e)
			{
				System.out.print("Error. Ingrese un numero para la Seccion del Producto. ");
				validacionNumero = false;
				scanner.nextLine();
			}
		}

		// Desactiva la bandera de validacion
		validacionNumero = false;

		// Ciclo de obtencion del precio del producto
		while(!validacionNumero)
		{
			try
			{
				System.out.print("Ingrese el precio del Producto: ");
				precioProducto = scanner.nextInt();
				scanner.nextLine();
				validacionNumero = true;
			}
			catch(InputMismatchException e)
			{
				System.out.print("Error. Ingrese un numero para el precio del Producto. ");
				validacionNumero = false;
				scanner.nextLine();
			}
		}
				
        // Adiciona el producto
        adicionarProducto(nombreProducto, descripcionProducto, seccionProducto, precioProducto);		
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
				
			} else {
				
				// Ingresa el valor en el stock
				stockSupermercado.put(nombreProducto, new Integer(cantidadProducto));
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