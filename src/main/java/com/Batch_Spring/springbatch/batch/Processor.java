package com.Batch_Spring.springbatch.batch;

import com.Batch_Spring.springbatch.model.Producto;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<Producto, Producto> {

    @Override
    public Producto process(Producto producto) throws Exception {
        return producto;
    }
}
