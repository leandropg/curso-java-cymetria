package com.supermercado.gui;

import java.awt.BorderLayout;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.supermercado.Supermercado;
import javax.swing.JTable;

/**
 * Esta clase define una JDialog para el reporte de Stock
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
public class ReporteStockGUI extends JDialog {
	
	/**
	 * Create the dialog.
	 */
	public ReporteStockGUI(Supermercado supermercado, JFrame jFrame) {
		
		int cantidadProductosStock;
		int posicion = 0;
		JPanel jPanel;
		JScrollPane jScrollPane;
		JTable jTable;
		String nombreColumnas[];
		String valoresColumnas[][];
		
		// Configura caracteristicas generales del dialogo
		setTitle("Reporte de Stock");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(jFrame);
		setModal(true);
		
		// Crea un nuevo Panel
		jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());		
		getContentPane().add(jPanel);

		// Obtiene los nombres de las columnas
		nombreColumnas = new String[2];
		nombreColumnas[0] = "Producto";
		nombreColumnas[1] = "Cantidad";

		// Obtiene la cantidad de productos del stock
		cantidadProductosStock = supermercado.getStockSupermercado().size();
		
		// Obtiene los datos de las columnas
		valoresColumnas = new String[cantidadProductosStock][2];

		// Obtiene el listado de productos
		Set<String> keysStock = supermercado.getStockSupermercado().keySet();
		
		// Llena la tabla con los datos
		for(String producto : keysStock) {

			valoresColumnas[posicion][0] = producto;
			valoresColumnas[posicion][1] = String.valueOf(supermercado.getStockSupermercado().get(producto));
			posicion++;
		}
		
		// Crea la nueva tabla
		jTable = new JTable(valoresColumnas, nombreColumnas);		
		jScrollPane = new JScrollPane(jTable);
		jPanel.add(jScrollPane, BorderLayout.CENTER);
		
		// Se hace visible el dialogo
		setVisible(true);
	}
}