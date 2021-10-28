package com.Batch_Spring.springbatch.batch;

import java.util.List;

import com.Batch_Spring.springbatch.model.Producto;
import com.Batch_Spring.springbatch.repository.ProductoRepository;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBWriter implements ItemWriter<Producto> {

	@Autowired
	private ProductoRepository productRepository;

	@Override
	public void write(List<? extends Producto> products) throws Exception {

		System.out.println("Insertando elementos");

		for (Producto p : products) {

			if (isFullFilledColumns(p)) {
				if (isInDb(p)) {
					System.out.println("Ya existe: " + p.getMarca() + " " + p.getNombre());
				} else {
					System.out.println(p.getMarca() + " " + p.getNombre() + " Insertado...");
					productRepository.saveAndFlush(p);
					// System.out.println(p.getBrand()+" "+p.getName()+" Insertado...");
				}
			} else {
				System.out.println("Registro incompleto: " + p.toString());
			}
		}

	}

	public boolean isInDb(Producto p) {

		if (productRepository.existsProductByNombreAndMarca(p.getNombre(), p.getMarca())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isFullFilledColumns(Producto p) {

		if (p.getMarca() == null || p.getMarca().isEmpty() || p.getMarca().isBlank())
			return false;
		else if (p.getNombre() == null || p.getNombre().isEmpty() || p.getNombre().isBlank())
			return false;
		else if (p.getDescuento() == null)
			return false;
		else if (p.getPrecio() == null)
			return false;
		else if (p.getEstado() == null || p.getEstado().isEmpty() || p.getEstado().isBlank())
			return false;
		else if (p.getStock() == null)
			return false;

		return true;
	}

}
