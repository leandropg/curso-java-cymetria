package com.supermercado.modelo;

/**
 * Define una seccion de un supermercado
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
public class Seccion implements Comparable
{
	/**
	 * Nombre de la seccion
	 */
	private String nombre;

	/**
	 * Descripcion de la seccion
	 */
	private String descripcion;
	
	/**
	 * Numero de la seccion
	 */
	private int numero;

	/**
	 * Crea una instancia de la clase Seccion 
	 * @param nombre Nombre de la seccion
	 * @param descripcion Descripcion de la seccion
	 * @param numero Numero de la seccion
	 */
	public Seccion(String nombre, String descripcion, int numero)
	{
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.numero = numero;
	}

	/**
	 * Retorna el nombre de la seccion 
	 * @return Nombre de la seccion
	 */
	public String getNombre()
	{
		return nombre;
	}

	/**
	 * Configura el nombre de la seccion
	 * @param nombre Nombre de la seccion 
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}	

	/**
	 * Retorna la descripcion de la seccion 
	 * @return Descripcion de la seccion
	 */
	public String getDescripcion()
	{
		return descripcion;
	}

	/**
	 * Configura la descripcion de la seccion 
	 * @param descripcion Descripcion de la seccion
	 */
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	
	/**
	 * Retorna el numero de la seccion 
	 * @return Numero de la seccion
	 */
	public int getNumero()
	{
		return numero;
	}

	/**
	 * Configura el numero de la seccion 
	 * @param numero Numero de la seccion
	 */
	public void setNumero(int numero)
	{
		this.numero = numero;
	}
	
	/**
	 * Retorna una cadena con los valores de la seccion 
	 */
	public String toString()
	{
		return "Seccion: " + nombre +
		       " Descripcion Seccion: " + descripcion +
		       " Numero Seccion: " + numero;
	}
	
	/**
	 * Metodo que compara dos secciones
	 */
	public int compareTo(Object object)
	{
		Seccion seccion = (Seccion)object; 
		
		return nombre.compareTo(seccion.getNombre());
	}
}