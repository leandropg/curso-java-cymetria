package com.supermercado.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import com.supermercado.Supermercado;
import com.supermercado.excepcion.ProductoNoDefinidoExcepcion;
import com.supermercado.reportes.IReporteImpresion;
import com.supermercado.reportes.ReporteProductos;
import com.supermercado.reportes.ReporteSecciones;
import com.supermercado.reportes.ReporteStock;

/**
 * Esta clase define la interfaz grafica del supermercado
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
public class SupermercadoGUI
{
	/**
	 * Instancia del supemercado
	 */
	private Supermercado supermercado;

	/**
	 * Frame Principal de la aplicacion
	 */
	private JFrame framePrincipal;
		
	/**
	 * Crea una instancia del Supermercado GUI
	 */
	public SupermercadoGUI()
	{
		// Crea el supermercado
		supermercado = new Supermercado();
		
		// Crea el frame
		framePrincipal = new JFrame(supermercado.getNombre());
		framePrincipal.setSize(600,300);
		framePrincipal.setLayout(null);
		framePrincipal.setLocationRelativeTo(null);

		// Barra de Menus
		JMenuBar barraMenus = new JMenuBar();
		framePrincipal.setJMenuBar(barraMenus);
		
		// Menus
		JMenu menuArchivo = new JMenu("Archivo");
		JMenu menuSeccion = new JMenu("Seccion");
		JMenu menuProducto = new JMenu("Producto");
		JMenu menuStock = new JMenu("Stock");
		barraMenus.add(menuArchivo);
		barraMenus.add(menuSeccion);
		barraMenus.add(menuProducto);
		barraMenus.add(menuStock);

		JMenuItem guardarEstado = new JMenuItem("Guardar Datos");
		guardarEstado.setActionCommand("GuardarDatos");
		guardarEstado.addActionListener(new ManejadorEventos());
		menuArchivo.add(guardarEstado);
		menuArchivo.add(new JSeparator());
		
		JMenuItem salir = new JMenuItem("Salir");
		salir.setActionCommand("Salir");
		salir.addActionListener(new ManejadorEventos());
		menuArchivo.add(salir);

		// Items Menu Seccion
		JMenuItem agregarSeccion = new JMenuItem("Agregar");
		agregarSeccion.setActionCommand("AgregarSeccion");
		agregarSeccion.addActionListener(new ManejadorEventos());
		menuSeccion.add(agregarSeccion);
		
		JMenuItem reporteSeccion = new JMenuItem("Reporte");
		reporteSeccion.setActionCommand("ReporteSeccion");
		reporteSeccion.addActionListener(new ManejadorEventos());
		menuSeccion.add(new JSeparator());
		menuSeccion.add(reporteSeccion);

		// Items Menu Producto
		JMenuItem agregarProducto = new JMenuItem("Agregar");
		agregarProducto.setActionCommand("AgregarProducto");
		agregarProducto.addActionListener(new ManejadorEventos());
		menuProducto.add(agregarProducto);
		
		JMenuItem reporteProducto = new JMenuItem("Reporte");
		reporteProducto.setActionCommand("ReporteProducto");
		reporteProducto.addActionListener(new ManejadorEventos());
		menuProducto.add(new JSeparator());
		menuProducto.add(reporteProducto);

		// Items Menu Stock
		JMenuItem adicionarStock = new JMenuItem("Adicionar");
		adicionarStock.setActionCommand("AdicionarStock");
		adicionarStock.addActionListener(new ManejadorEventos());
		menuStock.add(adicionarStock);

		JMenuItem retirarStock = new JMenuItem("Retirar");
		retirarStock.setActionCommand("RetirarStock");
		retirarStock.addActionListener(new ManejadorEventos());
		menuStock.add(retirarStock);
		menuStock.add(new JSeparator());
		
		JMenuItem reporteStock = new JMenuItem("Reporte");
		reporteStock.setActionCommand("ReporteStock");
		reporteStock.addActionListener(new ManejadorEventos());
		menuStock.add(reporteStock);
		
		// Desplegar la ventana
		framePrincipal.setVisible(true);
	}
	
	/**
	 * Este es el punto de entrada a la aplicacion SupermercadoGUI
	 * @param args
	 */
	public static void main(String[] args)
	{
		new SupermercadoGUI();
	}
	
	/**
	 * Clase que maneja los eventos del JFrame
	 * @author Leandro
	 */
	private class ManejadorEventos implements ActionListener
	{
		/**
		 * Metodo que responde a los eventos del JFrame
		 */
		public void actionPerformed(ActionEvent actionEvent)
		{
			String accion = actionEvent.getActionCommand();
			
			// Procesa la opcion de guardar datos
			if( accion.equals("GuardarDatos"))
			{
				supermercado.guardarDatosArchivo();
			}
			
			// Procesa la opcion de salir
			if( accion.equals("Salir"))
			{
				System.exit(0);
			}

			// Procesa la opcion de agregar una seccion
			if( accion.equals("AgregarSeccion"))
			{
				supermercado.crearNuevaSeccion();
			}

			// Procesa la opcion de reporte de las secciones 
			if( accion.equals("ReporteSeccion"))
			{
				IReporteImpresion r = new ReporteSecciones(supermercado);
				r.imprimirDetalle();
			}
			
			// Procesa la opcion de agregar un producto
			if( accion.equals("AgregarProducto"))
			{
				supermercado.crearNuevoProducto();	
			}
			
			// Procesa la opcion de reporte de los productos
			if( accion.equals("ReporteProducto"))
			{
				IReporteImpresion r = new ReporteProductos(supermercado);
				r.imprimirDetalle();	
			}
			
			// Procesa la opcion de adicionar stock
			if( accion.equals("AdicionarStock"))
			{
				// A�ade stock al supermercado
				try {
				
					supermercado.adicionarStock();
				
				} catch (ProductoNoDefinidoExcepcion e) {
				
					System.out.println("Error al Adicionar Stock. " + e.getMessage());
				}
			}
			
			// Procesa la opcion de retirar stock
			if( accion.equals("RetirarStock"))
			{
				// Retira stock del supermercado
				try {

					supermercado.retirarStock();
				
				} catch (ProductoNoDefinidoExcepcion e) {
					
					System.out.println("Error al Retirar Stock.");
					e.printStackTrace();
				}
			}

			// Procesa la opcion de reporte del stock
			if( accion.equals("ReporteStock"))
			{
				IReporteImpresion r = new ReporteStock(supermercado);
				r.imprimirDetalle();
			}
		}
	}
}

