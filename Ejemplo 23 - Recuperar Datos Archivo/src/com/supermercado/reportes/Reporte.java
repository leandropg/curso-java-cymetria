package com.supermercado.reportes;

import com.supermercado.Supermercado;

/**
 * Genera un reporte del supermercado
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
public abstract class Reporte
{
	/**
	 * Instancia del Supermercado
	 */
	protected Supermercado supermercado;
	
	/**
	 * Crea una instancia de un reporte
	 */
	public Reporte(Supermercado supermercado)
	{
		this.supermercado = supermercado;
	}
	
	/**
	 * Imprime el encabezado del reporte
	 */
	protected void imprimirEncabezado()
	{
		// Imprime las secciones de un supermercado
		System.out.println("\nSUPERMERCADO " + supermercado.getNombre());
	}
	
	/**
	 * Metodo abstracto para imprimir el detalle de los reportes
	 */
	public abstract void imprimirDetalle();
}
