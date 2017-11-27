package aldus.dataBaseActions.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO<K,T> {
    protected Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract List<T> findAll() throws SQLException;

    public abstract T findById(K id) throws SQLException;

    public abstract T update(K id,T entity) throws SQLException;

    public abstract boolean deleteById(K id) throws SQLException;

    public abstract boolean deleteByEntity(T entity) throws SQLException;

    public abstract boolean create(T entity) throws SQLException;

    public void closeStatement(Statement statement){
        if(statement!=null){
            try{
                statement.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public void closeResultSet(ResultSet resultSet) throws SQLException {
        if(resultSet!=null){
            try {
                resultSet.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
