/**
 * Esta clase define al objeto producto
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
    private String seccion;

    /**
     * Precio del producto
     */
    private int precio;

    /**
     * Crea una instancia de la clase Producto
     * @param nombreProducto
     * @param descripcionProducto
     * @param seccionProducto
     * @param precioProducto
     */
    public Producto(String nombre, String descripcion, String seccion, int precio)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.seccion = seccion;
        this.precio = precio;
    }

    /**
     * Obtiene el atributo Nombre
     * @return
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Configura el atributo Nombre
     * @param nombre
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }    
    
    /**
     * Obtiene el atributo Descripcion
     * @return
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * Configura el atributo Descripcion
     * @param descripcion
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el atributo Seccion
     * @return
     */
    public String getSeccion()
    {
        return seccion;
    }

    /**
     * Configura el atributo Seccion
     * @param seccion
     */
    public void setSeccionProducto(String seccion)
    {
        this.seccion = seccion;
    }
    
    /**
     * Obtiene el atributo Precio
     * @return
     */
    public int getPrecio()
    {
        return precio;
    }

    /**
     * Configura el atributo Precio
     * @param precio
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
}
