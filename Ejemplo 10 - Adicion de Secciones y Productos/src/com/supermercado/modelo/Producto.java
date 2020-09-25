package com.supermercado.modelo;

/**
 * Define al objeto producto de un supermercado
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
public class Producto
{
    /**
     * Nombre del producto
     */
    private String nombre;

    /**
     * Descripcion del producto
     */
    private String descripcion;

    /**
     * Seccion del producto
     */
    private Seccion seccion;

    /**
     * Precio del producto
     */
    private int precio;

    /**
     * Crea una instancia de la clase Producto
     * @param nombre Nombre del producto
     * @param descripcion Descripcion del producto
     * @param seccion Seccion del producto
     * @param precio Precio del producto
     */
    public Producto(String nombre, String descripcion, Seccion seccion, int precio)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.seccion = seccion;
        this.precio = precio;
    }

    /**
     * Retorna el nombre del producto
     * @return Nombre del producto
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Configura el atributo Nombre
     * @param nombre Nombre del producto
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
        
    /**
     * Retorna el atributo Descripcion
     * @return Descripcion del producto
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * Configura el atributo Descripcion
     * @param descripcion Descripcion del producto
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * Retorna el atributo Seccion
     * @return Seccion del producto
     */
    public Seccion getSeccion()
    {
        return seccion;
    }

    /**
     * Configura el atributo Seccion
     * @param seccion Seccion del producto
     */
    public void setSeccion(Seccion seccion)
    {
        this.seccion = seccion;
    }
    
    /**
     * Retorna el atributo Precio
     * @return Precio del producto
     */
    public int getPrecio()
    {
        return precio;
    }

    /**
     * Configura el precio del producto y su valor debe ser mayor que cero.
     * Si el valor suministrado es negativo, el valor asignado sera de cero.
     * @param precioProducto Precio del producto
     */
    public void setPrecio(int precio)
    {
        if (precio > 0)
        {
            this.precio = precio;
        }
        else
        {
            precio = 0;
        }
    }

	/**
	 * Retorna una cadena con los valores del producto 
	 */
	public String toString()
	{
		return "Producto: " + nombre +
		       " Descripcion: " + descripcion +
		       " " + seccion +
		       " Precio: " + precio;
	}    
}
