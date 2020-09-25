package com.supermercado;

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
	private Seccion[] grupoSecciones;
	
	/**
	 * Inventario de productos del supermercado
	 */
	private Producto[] inventarioProductos;
	
	/**
	 * Crea una instancia de la clase supermercado
	 * @param nombreSupermercado Nombre del supemercado
	 */
	public Supermercado()
	{
		// Configura el nombre del supermercado
		nombre = "Mi Supemercado";

		// Inicializa el grupo de secciones del supermercado
		grupoSecciones = new Seccion[2];
		
    	// Crea la seccion verduras
		Seccion seccionVerduras = new Seccion("Verduras", "Verduras frescas", 1);

		// Crea la seccion frutas
		Seccion seccionFrutas = new Seccion("Frutas", "Frutas frescas", 2);
		
		// Asigna valores al grupo de secciones
		grupoSecciones[0] = seccionVerduras;
		grupoSecciones[1] = seccionFrutas;

		// Inicializa el inventario de productos
		inventarioProductos = new Producto[2];
		
        // Crea un tomate
        Producto tomate = new Producto("Tomate", "Tomate Chonto para guiso", grupoSecciones[0], 100);

        // Agrega el tomate a los productos
        inventarioProductos[0] = tomate;

        // Crea una manzana
        Producto manzana = new Producto("Manzana", "Manzana Verde Chilena", grupoSecciones[1], 400);

        // Agrega la manzana a los productos
        inventarioProductos[1] = manzana;
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
	public Seccion[] getGrupoSecciones()
	{
		return grupoSecciones;
	}

	/**
	 * Configura la secciones del supermercado
	 * @param grupoSecciones Secciones del supemercado
	 */
	public void setGrupoSecciones(Seccion[] grupoSecciones)
	{
		this.grupoSecciones = grupoSecciones;
	}

	/**
	 * Retorna el inventario de productos del supermercado
	 * @return Inventario de productos
	 */
	public Producto[] getInventarioProductos()
	{
		return inventarioProductos;
	}

	/**
	 * Configura el inventario de productos del supermercado
	 * @param inventarioProductos Inventario de productos
	 */
	public void setInventarioProductos(Producto[] inventarioProductos)
	{
		this.inventarioProductos = inventarioProductos;
	}	
}
