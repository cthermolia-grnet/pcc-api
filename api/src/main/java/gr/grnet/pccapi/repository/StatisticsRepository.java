package gr.grnet.pccapi.repository;

import gr.grnet.connectors.mysql.HRLSConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StatisticsRepository {

  public int getPIDCountByPrefixID(String prefix) throws SQLException {
    int total = 0;
    String query = "SELECT handles_count FROM prefixes WHERE prefix=?";

    try (Connection connection = HRLSConnector.getHRLSConnector().getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
      ps.setString(1, prefix);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          total = rs.getInt(1);
          return total;
        } else {
          throw new IllegalArgumentException(String.format("Prefix %s not found", prefix));
        }
      }
    } catch (SQLException e) {
      throw new SQLException(e);
    }
  }

  public int getResolvablePIDCountByPrefixID(String prefix) throws SQLException {

    int total = 0;
    String query = "SELECT resolvable_count FROM prefixes WHERE prefix=?";
    String connectionUrl = null;

    try (Connection connection = HRLSConnector.getHRLSConnector().getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
      ps.setString(1, prefix);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          total = rs.getInt(1);
          return total;
        } else {
          throw new IllegalArgumentException(String.format("Prefix %s not found", prefix));
        }
      }
    } catch (SQLException e) {
      throw new SQLException(e);
    }
  }
}
