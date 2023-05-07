package com.goit.crud.repository;

import com.goit.crud.Datasource;
import com.goit.crud.entity.ClientEntity;
import com.goit.crud.exception.DatasourceException;
import com.goit.crud.exception.DeleteClientException;
import com.goit.crud.exception.InsertNameException;
import org.intellij.lang.annotations.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService extends JDBCRepository<ClientEntity> {
    private static final String TABLE_NAME = "client";
    private static final String ID_LABEL = "id";
    private static final Logger LOG = LoggerFactory.getLogger(ClientService.class);


    @Language("SQL")
    private static final String INSERT_CLIENT = """
            INSERT INTO client (NAME) VALUES (?);
            """;

    @Language("SQL")
    private static final String UPDATE_CLIENT = """
            UPDATE client SET NAME = ? WHERE id = ?;
            """;


    public ClientService(Datasource datasource) {
        super(datasource);
        LOG.info("Created CClientService");
    }

    @Override
    public String getById(Long id) {
        try {
            PreparedStatement preparedStatement = datasource.prepareStatement(getByIdReadyQuery(TABLE_NAME, ID_LABEL));
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean next = resultSet.next();
            if (!next) {
                System.out.println("There is no client with such id");
                return null;
            }
            ClientEntity clientEntity = parseClientRow(resultSet);
            datasource.close();
            return clientEntity.getClientName();
        } catch (Exception e) {
            String message = "findById";
            LOG.error(message, e);
            throw new DatasourceException("findById", e);
        }
    }

    @Override
    public List<ClientEntity> listAll() {
        LOG.info("loading all customers");
        try {
            PreparedStatement preparedStatement = datasource.prepareStatement(getListAllReadyQuery(TABLE_NAME));
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ClientEntity> list = new ArrayList<>();
            while(resultSet.next()) {
                ClientEntity clientEntity = parseClientRow(resultSet);
                list.add(clientEntity);
            }
            datasource.close();
            LOG.info("all customers loaded");
            return list;
        } catch (Exception e) {
            String message = "listAll";
            LOG.error(message, e);
            throw new DatasourceException(message, e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            PreparedStatement preparedStatement = datasource.prepareStatement(getDeleteByIdReadyQuery(TABLE_NAME, ID_LABEL));
            preparedStatement.setLong(1, id);
            if (!preparedStatement.execute()) LOG.info("Client wasn't deleted");
            datasource.close();
            LOG.info("Client was deleted");
        } catch (Exception e) {
            String message = "deleteById error";
            LOG.error(message, e);
            throw new DeleteClientException(message, e);
        }
    }

    @Override
    public long create(String name) {
        if (name.length() < 2 || name.length() > 1000) throw new InsertNameException("Client name has to have" +
                " minimum 2 and maximum 1000 symbols");
        try {
            PreparedStatement preparedStatement = datasource.prepareStatement(INSERT_CLIENT);
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            PreparedStatement preparedStatementId = datasource.prepareStatement(getListAllReadyQuery(TABLE_NAME));
            ResultSet resultSetId = preparedStatementId.executeQuery();
            resultSetId.last();
            long lastId = resultSetId.getLong("id");
            LOG.info("Client was added");
            datasource.close();
            return lastId;
        } catch (Exception e) {
            String message = "create";
            LOG.error(message, e);
            throw new DatasourceException(message, e);
        }
    }

    @Override
    public void setName(long id, String name) {
        if (name.length() < 2 || name.length() > 1000) throw new InsertNameException("Client name has to have" +
                " minimum 2 and maximum 1000 symbols");
        try {
            PreparedStatement preparedStatement = datasource.prepareStatement(UPDATE_CLIENT);
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            if (!preparedStatement.execute()) System.out.println("There is no client with such id");
            datasource.close();
        } catch (Exception e) {
            String message = "setName error";
            throw new InsertNameException(message, e);
        }
    }

    private ClientEntity parseClientRow(ResultSet resultSet) throws SQLException {
        Long clientId = resultSet.getLong("ID");
        String clientName = resultSet.getString("NAME");
        return ClientEntity.of(clientId, clientName);
    }
}
