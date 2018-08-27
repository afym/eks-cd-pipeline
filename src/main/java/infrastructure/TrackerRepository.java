package infrastructure;

import logger.TrackerEntity;
import repository.TrackerRepositoryInterface;

import java.sql.*;

public class TrackerRepository implements TrackerRepositoryInterface {
    private DataConnector dataConnector;
    private Connection connection;

    public TrackerRepository(DataConnector dataConnector) {
        this.dataConnector = dataConnector;
        this.connection = this.dataConnector.getConnection();
    }

    @Override
    public void insert(TrackerEntity trackerEntity) {
        String sql = "INSERT INTO tracker" + "(ip, date, hour) VALUES"  + "(?,?,?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, trackerEntity.getIpAddress());
            preparedStatement.setString(2, trackerEntity.getDate());
            preparedStatement.setString(3, trackerEntity.getHour());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO : logger
        } finally {
            // TODO : logger
        }
    }
}
