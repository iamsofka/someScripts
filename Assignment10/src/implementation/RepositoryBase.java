package implementation;

import dtos.DTOBase;
import dtos.GroupDTO;
import dtos.UserDTO;
import repositories.Connector;
import repositories.IRepository;

import java.sql.*;

public abstract class RepositoryBase <TEntity extends DTOBase> implements IRepository<TEntity> {
    private static final String TABLE_GU = "groups_users";
    private static final String COLUMN_USER_ID = UserRepository.getPK();
    private static final String COLUMN_GROUP_ID = GroupRepository.getPK();

    private Connection connection;
    private Savepoint savepoint;

    public RepositoryBase()
    {
        getConnection();
    }

    public Connection getConnection()
    {
        if (connection != null) {
            return connection;
        } else {
            connection = Connector.connect();
            return connection;
        }
    }

    public void add(UserDTO u_dto, GroupDTO g_dto)
    {
        try
        {
            final String query = "INSERT INTO "+TABLE_GU+" ("+
                    COLUMN_USER_ID+","+COLUMN_GROUP_ID+
                    ")  VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, u_dto.getId());
            statement.setInt(2, g_dto.getId());
            statement.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void delete(UserDTO u_dto, GroupDTO g_dto)
    {
        try
        {
            final String query = "DELETE FROM "+TABLE_GU+" WHERE " +
                    COLUMN_USER_ID +" = ? AND " +
                    COLUMN_GROUP_ID+" = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, u_dto.getId());
            statement.setInt(2, g_dto.getId());
            statement.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void beginTransaction()
    {
        try
        {
            connection.setAutoCommit(false);
            savepoint = getConnection().setSavepoint();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void commitTransaction()
    {
        try
        {
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void rollbackTransaction()
    {
        try
        {
            connection.rollback(savepoint);
            connection.setAutoCommit(true);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public int getCount()
    {
        try
        {
            final String query = "SELECT COUNT (*) FROM "+TABLE_GU;
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                return resultSet.getInt(1);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean exists(UserDTO dto)
    {
        return exists(dto,COLUMN_USER_ID);
    }

    public boolean exists(GroupDTO dto)
    {
        return exists(dto,COLUMN_GROUP_ID);
    }

    private <TEntity extends DTOBase> boolean exists (TEntity dto, String columnSpecifier){
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM "+TABLE_GU + " WHERE " + columnSpecifier+"= ?");
            statement.setInt(1, dto.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
