package com.goit.crud.repository;

import com.goit.crud.Datasource;
import lombok.RequiredArgsConstructor;
import org.intellij.lang.annotations.Language;

import java.util.List;

@RequiredArgsConstructor
public abstract class JDBCRepository<T> {
    @Language("SQL")
    private final String GET_BY_ID_QUERY = "SELECT * FROM %s WHERE %s = ?;";
    @Language("SQL")
    private final String FIND_ALL_QUERY = "SELECT * FROM %s;";

    @Language("SQL")
    private static final String DELETE_BY_ID_QUERY = """
            DELETE FROM %s WHERE %s = ?;
            """;

    protected final Datasource datasource;

    public String getByIdReadyQuery(String tableName, String id) {
        return String.format(GET_BY_ID_QUERY, tableName, id);
    }

    public String getDeleteByIdReadyQuery(String tableName, String id) {
        return String.format(DELETE_BY_ID_QUERY, tableName, id);
    }

    public String getListAllReadyQuery(String tableName) {
        return String.format(FIND_ALL_QUERY, tableName);
    }

    public abstract String getById(Long id);

    public abstract List<T> listAll();

    public abstract void deleteById(Long id);

    public abstract long create(String name);

    public abstract void setName(long id, String name);

}
