package com.goit;

import com.goit.conf.FlywayConfigurations;
import com.goit.conf.LoggingConfiguration;
import com.goit.crud.Datasource;
import com.goit.crud.entity.ClientEntity;
import com.goit.crud.repository.ClientService;
import com.goit.crud.repository.JDBCRepository;

import java.io.IOException;
import java.util.List;

public class CrudApplication {
    public static void main(String[] args) throws IOException {
        new LoggingConfiguration();
        new FlywayConfigurations().setup().migrate();
        JDBCRepository<ClientEntity> repository = new ClientService(new Datasource());

        System.out.println("New Client id = " + repository.create("One more Client"));
        repository.deleteById(3L);
        System.out.println("New Client id = " + repository.create("New Client"));
        repository.setName(4L, "Replaced Client");
        System.out.println(repository.getById(1L));

        List<ClientEntity> list = repository.listAll();
        System.out.println("list = " + list);
    }
}