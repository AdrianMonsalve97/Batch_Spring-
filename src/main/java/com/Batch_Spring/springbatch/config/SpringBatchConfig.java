package com.Batch_Spring.springbatch.config;

import java.sql.SQLException;

import com.Batch_Spring.springbatch.model.Producto;

import org.h2.tools.Server;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseaServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "8081");
    }

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
            ItemReader<Producto> itemReader, ItemProcessor<Producto, Producto> itemProcessor,
            ItemWriter<Producto> itemWriter) {

        Step step = stepBuilderFactory.get("ETL-file-load").<Producto, Producto>chunk(100).reader(itemReader)
                .processor(itemProcessor).writer(itemWriter).build();

        return jobBuilderFactory.get("ETL-Load").incrementer(new RunIdIncrementer()).start(step).build();
    }

    @Bean
    public FlatFileItemReader<Producto> itemReader(@Value("${input}") Resource resource) {

        FlatFileItemReader<Producto> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(resource);
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<Producto> lineMapper() {

        DefaultLineMapper<Producto> defaultLineMapper = new DefaultLineMapper<Producto>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[] { "nombre", "marca", "precio", "stock", "estado", "descuento" });

        BeanWrapperFieldSetMapper<Producto> fieldSetMapper = new BeanWrapperFieldSetMapper<Producto>();
        fieldSetMapper.setTargetType(Producto.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;

    }

}
