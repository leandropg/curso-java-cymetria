package com.supermercado.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.supermercado.Supermercado;
import com.supermercado.excepcion.ProductoNoDefinidoExcepcion;
import com.supermercado.modelo.Producto;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Esta clase define una JDialog para los datos de Stock
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
public class StockGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCantidadProducto;

	/**
	 * Create the dialog.
	 */
	public StockGUI(Supermercado supermercado, JFrame jFrame) {
		setTitle("Agregar/Retirar Stock");
		setBounds(100, 100, 350, 190);
		setLocationRelativeTo(jFrame);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JComboBox comboProductos = new JComboBox();
		for(Producto producto : supermercado.getListaProductos())
		{
			comboProductos.addItem(producto.getNombre());
		}
		
		txtCantidadProducto = new JTextField();
		txtCantidadProducto.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Obtiene los datos del usuario
				String nombreProductoElegido = (String) comboProductos.getSelectedItem();
				int cantidadProducto = Integer.parseInt(txtCantidadProducto.getText());
				
				// A�ade stock al supermercado
				try {
				
					supermercado.adicionarStock(nombreProductoElegido, cantidadProducto);
				
				} catch (ProductoNoDefinidoExcepcion ex) {
				
					System.out.println("Error al Adicionar Stock. " + ex.getMessage());
				}
				
				// Cierra el cuadro de dialogo
				dispose();
			}
		});
		btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnRetirar = new JButton("Retirar");
		btnRetirar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Obtiene los datos del usuario
				String nombreProductoElegido = (String)comboProductos.getSelectedItem();
				int cantidadProducto = Integer.parseInt(txtCantidadProducto.getText());

				// Retira stock del supermercado
				try {

					supermercado.retirarStock(nombreProductoElegido, cantidadProducto);
				
				} catch (ProductoNoDefinidoExcepcion ex) {
					
					System.out.println("Error al Retirar Stock.");
					ex.printStackTrace();
				}
				
				// Cierra el cuadro de dialogo
				dispose();
			}
		});
		btnRetirar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Cierra el dialogo
				dispose();
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(57)
							.addComponent(btnAdicionar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRetirar)
							.addGap(18)
							.addComponent(btnNewButton))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(44)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblCantidad, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
									.addGap(37)
									.addComponent(txtCantidadProducto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblProducto)
									.addGap(37)
									.addComponent(comboProductos, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProducto)
						.addComponent(comboProductos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCantidad, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCantidadProducto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdicionar)
						.addComponent(btnRetirar)
						.addComponent(btnNewButton))
					.addContainerGap(121, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		setVisible(true);
	}
}
