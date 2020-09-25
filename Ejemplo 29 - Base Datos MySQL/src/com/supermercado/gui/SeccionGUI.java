package com.supermercado.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.supermercado.Supermercado;

/**
 * Esta clase define una JDialog para los datos de Seccion
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
public class SeccionGUI implements ActionListener
{
	/**
	 * Referencia al supermercado
	 */
	private Supermercado supermercado;
	
	/**
	 * Ventana de dialogo de Secciones
	 */
	private JDialog jDialog;
	
	/**
	 * Nombre de la seccion
	 */
	private JTextField nombreSeccion;

	/**
	 * Descripcion de la seccion
	 */
	private JTextField descripcionSeccion;

	/**
	 * Numero de la seccion
	 */
	private JTextField numeroSeccion;

	/**
	 * Crea una instancia de las Secciones
	 * @param jFrame
	 */
	public SeccionGUI(Supermercado supermercado, JFrame jFrame)
	{
		// Almacena la referencia al supermercado
		this.supermercado = supermercado;
		
		// Crea el dialogo de secciones
		jDialog = new JDialog(jFrame, "", true);
		jDialog.setTitle("Agregar Seccion");
		jDialog.setSize(300,100);
		jDialog.setLayout(new GridLayout(4,2));
		jDialog.setLocationRelativeTo(jFrame);
		 
		jDialog.add(new JLabel("Nombre"));
		nombreSeccion = new JTextField();
		jDialog.add(nombreSeccion);
		jDialog.add(new JLabel("Descripcion"));
		descripcionSeccion = new JTextField();
		jDialog.add(descripcionSeccion);
		jDialog.add(new JLabel("Numero"));
		numeroSeccion = new JTextField();
		jDialog.add(numeroSeccion);
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener( this );
		botonAceptar.setActionCommand("Aceptar");
		jDialog.add(botonAceptar);
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setActionCommand("Cancelar");
		botonCancelar.addActionListener( this );
		jDialog.add(botonCancelar);
			
		// Desplegar la ventana
		jDialog.setVisible(true);
	}
	
	/**
	 * Procesa la accion sobre los botones
	 */
	public void actionPerformed(ActionEvent actionEvent)
	{
		String accion = actionEvent.getActionCommand();
		
		// Procesa la opcion Aceptar
		if( accion.equals("Aceptar"))
		{			
			int numeroNuevaSeccion;
			
			try
			{
				// Obtiene la conversion de String a numero del numero de la seccion
				numeroNuevaSeccion = Integer.parseInt(numeroSeccion.getText());
				
				// Adiciciona una seccion al supermercado
				supermercado.adicionarSeccion( nombreSeccion.getText(), descripcionSeccion.getText(), numeroNuevaSeccion);

				// Cierra el cuadro de dialogo
				jDialog.dispose();
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "Error. Ingrese un numero para la Seccion.");
			}			
		}

		// Procesa la opcion Cancelar
		if( accion.equals("Cancelar"))
		{
			// Cierra el cuadro de dialogo
			jDialog.dispose();
		}		
	}
}
