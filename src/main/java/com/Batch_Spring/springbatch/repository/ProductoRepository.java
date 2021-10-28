package com.Batch_Spring.springbatch.repository;

import com.Batch_Spring.springbatch.model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepository extends JpaRepository<Producto, Long> {

    boolean existsProductByNombreAndMarca(String nombre, String marca);

}
