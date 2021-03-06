package com.supermercado;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.supermercado.modelo.Producto;
import com.supermercado.modelo.Seccion;
import com.supermercado.reportes.ReporteInventario;

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
	 * Crea una instancia de la clase supermercado
	 * @param nombreSupermercado Nombre del supemercado
	 */
	public Supermercado()
	{
		// Inicializa la lista de secciones del supermercado
		listaSecciones = new ArrayList<Seccion>();

		// Inicializa la lista de productos
		listaProductos = new ArrayList<Producto>();		
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
	}
		
	/**
	 * Este es el punto de entrada a la aplicacion Supermercado 
	 * @param args No requiere argumentos
	 */
	public static void main(String[] args)
	{
		// Variable local de la opcion del menu
		int opcion = 0;

		// Crea un supermercado
		Supermercado supermercado = new Supermercado();

		// Crea el objeto Scanner para interactuar con la consola
		Scanner scanner = new Scanner(System.in);
		
		// Ciclo de opciones del menu
		while( opcion != 6 )
		{			
			try
			{				
				System.out.println("\nMENU DE OPCIONES SUPEMERCADO " + supermercado.getNombre());
				System.out.println("\n1. Ingresar el nombre del supermercado");
				System.out.println("2. Ingresar una seccion");
				System.out.println("3. Ingresar un producto");
				System.out.println("4. Imprimir secciones");
				System.out.println("5. Imprimir productos");
				System.out.println("6. Salir");
				
				System.out.println("\nDigite una opcion: ");				
				opcion = scanner.nextInt();
				scanner.nextLine();

				// Selecciona las opciones del menu
				switch( opcion )
				{
					case 1:
						// Cambia el nombre del supemercado
						supermercado.cambiarNombreSupermercado();
						break;

					case 2:
						// Crea una nueva seccion
						supermercado.crearNuevaSeccion();
						break;

					case 3:
						// Crea un nuevo producto
						supermercado.crearNuevoProducto();
						break;
						
					case 4:
						// Imprime la secciones del supemercado
						ReporteInventario.imprimirSecciones(supermercado);
						break;

					case 5:
						// Imprime los productos del supemercado
						ReporteInventario.imprimirProductos(supermercado);
						break;

					case 6:
						// Imprime los productos del supemercado
						System.out.println("Gracias por usar nuestros servicios.");
						break;

					default:
						// Indica al usuario de que digito una opcion invalida
						System.out.println("\nElija una opcion entre 1 y 6.");
						break;
				}			
			}
			catch( InputMismatchException e )
			{
				System.out.println("\nError. " + e.getMessage() + ". Por favor digite un numero.");
				opcion = 0;
				scanner.nextLine();
			}			
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
}