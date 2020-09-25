/**
 * Esta clase demuestra como ocultar atributos y utilizar los metodos set y get
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
public class ReporteInventario
{
    /**
     * Este es el punto de entrada a la aplicacion
     * @param args
     */
    public static void main(String[] args)
    {
        // Crea un tomate
        Producto tomate = new Producto();
        tomate.setNombre("Tomate");
        tomate.setDescripcion("Tomate Chonto para guiso");
        tomate.setSeccion("Verduras");
        tomate.setPrecio(100);

        // Crea una manzana
        Producto manzana = new Producto();
        manzana.setNombre("Manzana");
        manzana.setDescripcion("Manzana Verde Chilena");
        manzana.setSeccion("Frutas");
        manzana.setPrecio(400);
        
        // Imprime los datos del tomate
        System.out.println("Producto: " + tomate.getNombre() +
        		           " Descripcion: " + tomate.getDescripcion() +
        		           " Seccion: " + tomate.getSeccion() +
        		           " Precio: " + tomate.getPrecio());

        // Imprime los datos de la manzana
        System.out.println("Producto: " + manzana.getNombre() +
        		           " Descripcion: " + manzana.getDescripcion() +
        		           " Seccion: " + manzana.getSeccion() +
        		           " Precio: " + manzana.getPrecio());
    }
}
