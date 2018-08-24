/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.elpoeta.bitfeather.repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author elpoeta
 */
public interface CrudRepository<T,K> {
    
    T buscarPorId(K id) throws ClassNotFoundException,IOException, SQLException;
    List<T> buscarTodos() throws ClassNotFoundException,IOException, SQLException;
    void insertar(T obj) throws ClassNotFoundException,IOException, SQLException;
    void modificar(T obj) throws ClassNotFoundException,IOException, SQLException;
    void borrar(K id) throws ClassNotFoundException,IOException, SQLException;
    
}
