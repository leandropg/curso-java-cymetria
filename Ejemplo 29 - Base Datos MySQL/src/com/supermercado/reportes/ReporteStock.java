package com.supermercado.reportes;

import java.util.HashMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.supermercado.Supermercado;

/**
 * Genera un reporte del stock del supermercado
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
public class ReporteStock extends Reporte implements IReporteImpresion
{
	/**
	 * Crea una instancia del reporte de secciones
	 */
	public ReporteStock(Supermercado supermercado)
	{
		super(supermercado);
	}
	
	/**
	 * Imprime las secciones del supermercado
	 * @param supermercado
	 */
	public void imprimirDetalle()
	{
		// Imprime el Stock del supermercado
		System.out.println("\nSTOCK SUPERMERCADO " + supermercado.getNombre());
		
		// Obtiene el stock del supermercado
		HashMap<String,Integer> stockSupermercado = supermercado.getStockSupermercado();
		
		// Obtiene un set de las llaves del HashMap
		Set<String> listaLlaves = supermercado.getStockSupermercado().keySet();
		
		// Crea un set ordenado de las llaves
		SortedSet<String> listaLlavesOrdenada = new TreeSet<String>(listaLlaves);
				
		for(String producto : listaLlavesOrdenada)
		{
			System.out.println(producto + " " + stockSupermercado.get(producto) + " unidades");
		}		
	}
}