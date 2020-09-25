/**
 * Define la seccion de un supermercado 
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
public class Seccion {

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
	 * @param nombre
	 * @param descripcion
	 * @param numero
	 */
	public Seccion(String nombre, String descripcion, int numero)
	{
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.numero = numero;
	}

	/**
	 * Obtiene el nombre de la seccion 
	 * @return
	 */
	public String getNombre()
	{
		return nombre;
	}

	/**
	 * Configura el nombre de la seccion 
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	/**
	 * Obtiene la descripcion de la seccion 
	 * @return
	 */
	public String getDescripcion()
	{
		return descripcion;
	}

	/**
	 * Configura la descripcion de la seccion 
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}

	/**
	 * Obtiene el numero de la seccion
	 * @return
	 */
	public int getNumero()
	{
		return numero;
	}

	/**
	 * Configura el numero de la seccion 
	 * @param numero
	 */
	public void setNumero(int numero)
	{
		this.numero = numero;
	}
}
