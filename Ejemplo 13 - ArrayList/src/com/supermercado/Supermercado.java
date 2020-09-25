package com.supermercado;

import java.util.ArrayList;
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
	private ArrayList listaSecciones;
	
	/**
	 * Inventario de productos del supermercado
	 */
	private ArrayList listaProductos;
	
	/**
	 * Crea una instancia de la clase supermercado
	 * @param nombreSupermercado Nombre del supemercado
	 */
	public Supermercado()
	{
		// Variable de numero de secciones y de productos a configurar
		int numeroSecciones = 0;
		int numeroProductos = 0;
		
		// Variables Locales de Seccion
		String nombreSeccion = null;
		String descripcionSeccion = null;
		int numeroSeccion = 0;

		// Variables Locales de Producto
		String nombreProducto = null;
		String descripcionProducto = null;
		int numeroSeccionProducto = 0;
		int precioProducto = 0;

		// Crea el objeto Scanner para interactuar con la consola
		Scanner scanner = new Scanner(System.in);
		
		// Obtiene el nombre del supermercado
		System.out.print("Ingrese el nombre del Supermercado: ");
		nombre = scanner.nextLine();

		// Obtiene el numero de secciones
		System.out.print("\nIngrese el numero de secciones: ");
		numeroSecciones = scanner.nextInt();
		scanner.nextLine();
		
		// Inicializa la lista de secciones del supermercado
		listaSecciones = new ArrayList();

		// Ciclo de ingreso de las secciones 
		for(int i = 0; i < numeroSecciones; i++ )
		{ 
			// Obtiene los datos de la seccion 
			System.out.print("Ingrese el nombre de la Seccion " + i + ": ");
			nombreSeccion = scanner.nextLine();
			System.out.print("Ingrese la descripcion de la Seccion " + i + ": ");
			descripcionSeccion = scanner.nextLine();
			System.out.print("Ingrese el numero de la Seccion " + i + ": ");
			numeroSeccion = scanner.nextInt();
			scanner.nextLine();
			
	    	// Adiciona la seccion
			adicionarSeccion(nombreSeccion, descripcionSeccion, numeroSeccion);
		}
			
		// Obtiene el numero de productos
		System.out.print("\nIngrese el numero de productos: ");
		numeroProductos = scanner.nextInt();
		scanner.nextLine();
		
		// Inicializa la lista de productos
		listaProductos = new ArrayList();
		
		// Inicializa el apuntador
		int i = 0;

		// Ciclo de ingreso de los productos 
		while( i < numeroProductos)
		{
			// Obtiene los datos del producto
			System.out.print("Ingrese el nombre del Producto " + i + ": ");
			nombreProducto = scanner.nextLine();
			System.out.print("Ingrese la descripcion del Producto " + i + ": ");
			descripcionProducto = scanner.nextLine();
			System.out.print("Ingrese la posicion de la lista de la Seccion del Producto " + i + ": ");
			numeroSeccionProducto = scanner.nextInt();
			scanner.nextLine();
			System.out.print("Ingrese el precio del Producto " + i + ": ");
			precioProducto = scanner.nextInt();
			scanner.nextLine();

	        // Adiciona el producto
	        adicionarProducto(nombreProducto, descripcionProducto, (Seccion) listaSecciones.get(numeroSeccionProducto), precioProducto);
	        
			// Incrementa el apuntador
			i++;
		}		
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
		// Crea un supermercado
		Supermercado supermercado = new Supermercado();
		
		// Imprime el reporte del inventario
		ReporteInventario.generarReporte( supermercado);
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
	public ArrayList getListaSecciones()
	{
		return listaSecciones;
	}

	/**
	 * Configura la secciones del supermercado
	 * @param grupoSecciones Secciones del supemercado
	 */
	public void setListaSecciones(ArrayList listaSecciones)
	{
		this.listaSecciones = listaSecciones;
	}

	/**
	 * Retorna el inventario de productos del supermercado
	 * @return Inventario de productos
	 */
	public ArrayList getListaProductos()
	{
		return listaProductos;
	}

	/**
	 * Configura el inventario de productos del supermercado
	 * @param inventarioProductos Inventario de productos
	 */
	public void setListaProductos(ArrayList listaProductos)
	{
		this.listaProductos = listaProductos;
	}	
}
