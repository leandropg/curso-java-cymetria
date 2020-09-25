package com.supermercado.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Sentencias SQL del Supermercado
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
public class SupermercadoSQL {

	/**
	 * Inserta la nueva seccion en la base de datos
	 * @param nombreSeccion Nombre de la seccion
	 * @param descripcionSeccion Descripcion de la seccion
	 * @param numeroSeccion Numero de la seccion
	 */
	public static void guardarSeccionBaseDatos(String nombreSeccion, String descripcionSeccion, int numeroSeccion)
	{
		Connection conexion = null;
		PreparedStatement preparedStatement;
		String SQL = null;
		
		try
		{
			// Carga el driver de MySQL
			Class.forName("com.mysql.jdbc.Driver");
			
			// Inicia una conexion con la base de datos
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/supermercado", "root", "root");
			
			// Configura la sentencia SQL de la Tabla productos
			SQL = "INSERT INTO secciones (nombreSeccion, descripcionSeccion, numeroSeccion) VALUES (?,?,?)";
			
			// Crea una sentencia Prepared Statement
			preparedStatement = conexion.prepareStatement(SQL);
			preparedStatement.setString(1, nombreSeccion);
			preparedStatement.setString(2, descripcionSeccion);
			preparedStatement.setInt(3, numeroSeccion);

			// Ejecuta la sentencia
			preparedStatement.execute();
			
			// Cierra la conexion
			conexion.close();
			
			System.out.println("Seccion Adicionada Exitosamente");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("No se encontro el driver de la base de datos MySQL. " + e.getMessage());
		}
		catch (SQLException e)
		{
			System.out.println("Se produjo un error de SQL. " + e.getMessage());
		}		
	}

	/**
	 * Inserta el nuevo producto en la base de datos
	 * @param nombreProducto Nombre del producto
	 * @param descripcionProducto Descripcion del producto
	 * @param numeroSeccion Seccion del producto
	 * @param precioProducto Precio del producto
	 */
	public static void guardarProductoBaseDatos(String nombreProducto, String descripcionProducto, int numeroSeccion, int precioProducto)
	{
		int idSeccion = 0;
		Connection conexion = null;
		PreparedStatement preparedStatement;
		ResultSet resultado = null;
		String SQL = null;
		
		try
		{
			// Carga el driver de MySQL
			Class.forName("com.mysql.jdbc.Driver");
			
			// Inicia una conexion con la base de datos
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/supermercado", "root", "root");
			
			// Configura la sentencia SQL de la Tabla secciones
			SQL = "SELECT idSeccion FROM secciones WHERE numeroSeccion = ?";

			// Crea una sentencia Prepared Statement
			preparedStatement = conexion.prepareStatement(SQL);
			preparedStatement.setInt(1, numeroSeccion);

			// Execute Query
			resultado = preparedStatement.executeQuery();
			
			// Procesa el resultado
			while( resultado.next() )
			{
				// Crea el stock con los datos de la base de datos
				idSeccion = resultado.getInt("idSeccion");
			}
			
			// Configura la sentencia SQL de la Tabla productos
			SQL = "INSERT INTO productos (nombreProducto, descripcionProducto, idSeccion, precioProducto) VALUES (?,?,?,?)";
			
			// Crea una sentencia Prepared Statement
			preparedStatement = conexion.prepareStatement(SQL);
			preparedStatement.setString(1, nombreProducto);
			preparedStatement.setString(2, descripcionProducto);
			preparedStatement.setInt(3, idSeccion);
			preparedStatement.setInt(4, precioProducto);

			// Ejecuta la sentencia
			preparedStatement.execute();
			
			// Cierra la conexion
			conexion.close();
			
			System.out.println("Producto Adicionado Exitosamente");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("No se encontro el driver de la base de datos MySQL. " + e.getMessage());
		}
		catch (SQLException e)
		{
			System.out.println("Se produjo un error de SQL. " + e.getMessage());
		}		
	}
	
	/**
	 * Inserta el nuevo stock en la base de datos
	 * @param nombreProducto Nombre del Producto
	 * @param cantidadProducto Cantidad del Producto
	 */
	public static void guardarStockBaseDatos(String nombreProducto, int cantidadProducto)
	{
		int idProducto = 0;
		Connection conexion = null;
		PreparedStatement preparedStatement;
		ResultSet resultado = null;
		String SQL = null;
		
		try
		{
			// Carga el driver de MySQL
			Class.forName("com.mysql.jdbc.Driver");
			
			// Inicia una conexion con la base de datos
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/supermercado", "root", "root");
			
			// Configura la sentencia SQL de la Tabla secciones
			SQL = "SELECT idProducto FROM productos WHERE nombreProducto = ?";

			// Crea una sentencia Prepared Statement
			preparedStatement = conexion.prepareStatement(SQL);
			preparedStatement.setString(1, nombreProducto);

			// Execute Query
			resultado = preparedStatement.executeQuery();
			
			// Procesa el resultado
			while( resultado.next() )
			{
				// Crea el stock con los datos de la base de datos
				idProducto = resultado.getInt("idProducto");
			}
			
			// Configura la sentencia SQL de la Tabla productos
			SQL = "INSERT INTO stock (idProducto, cantidadProducto) VALUES (?,?)";
			
			// Crea una sentencia Prepared Statement
			preparedStatement = conexion.prepareStatement(SQL);
			preparedStatement.setInt(1, idProducto);
			preparedStatement.setInt(2, cantidadProducto);

			// Ejecuta la sentencia
			preparedStatement.execute();
			
			// Cierra la conexion
			conexion.close();
			
			System.out.println("Stock Adicionado Exitosamente");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("No se encontro el driver de la base de datos MySQL. " + e.getMessage());
		}
		catch (SQLException e)
		{
			System.out.println("Se produjo un error de SQL. " + e.getMessage());
		}		
	}
	
	/**
	 * Inserta el nuevo stock en la base de datos
	 * @param nombreProducto Nombre del Producto
	 * @param cantidadProducto Cantidad del Producto
	 */
	public static void actualizarStockBaseDatos(String nombreProducto, int cantidadProducto)
	{
		int idProducto = 0;
		Connection conexion = null;
		PreparedStatement preparedStatement;
		ResultSet resultado = null;
		String SQL = null;
		
		try
		{
			// Carga el driver de MySQL
			Class.forName("com.mysql.jdbc.Driver");
			
			// Inicia una conexion con la base de datos
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/supermercado", "root", "root");
			
			// Configura la sentencia SQL de la Tabla secciones
			SQL = "SELECT idProducto FROM productos WHERE nombreProducto = ?";

			// Crea una sentencia Prepared Statement
			preparedStatement = conexion.prepareStatement(SQL);
			preparedStatement.setString(1, nombreProducto);

			// Execute Query
			resultado = preparedStatement.executeQuery();
			
			// Procesa el resultado
			while( resultado.next() )
			{
				// Crea el stock con los datos de la base de datos
				idProducto = resultado.getInt("idProducto");
			}
			
			// Configura la sentencia SQL de la Tabla productos
			SQL = "UPDATE stock SET cantidadProducto = ? WHERE idProducto = ?";
			
			// Crea una sentencia Prepared Statement
			preparedStatement = conexion.prepareStatement(SQL);
			preparedStatement.setInt(1, cantidadProducto);
			preparedStatement.setInt(2, idProducto);

			// Ejecuta la sentencia
			preparedStatement.executeUpdate();
			
			// Cierra la conexion
			conexion.close();
			
			System.out.println("Stock Actualizado Exitosamente");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("No se encontro el driver de la base de datos MySQL. " + e.getMessage());
		}
		catch (SQLException e)
		{
			System.out.println("Se produjo un error de SQL. " + e.getMessage());
		}		
	}
}