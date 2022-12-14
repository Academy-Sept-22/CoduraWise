package com.codurance.codurawise.repos.mysql;

import com.codurance.codurawise.domain.models.Resource;
import com.codurance.codurawise.repos.SearchRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static com.codurance.codurawise.repos.mysql.util.PreparedStatementExecutor.executeResourceQuery;

public class SearchMySQLRepository implements SearchRepository {
  private final Connection connection;

  public SearchMySQLRepository(Connection connection) {
    this.connection = connection;
  }

  @Override
  public List<Resource> queryBothByTitleAndTag(String title, String tag) {
    try {
      String sql = ("SELECT" +
        " Resource.* " +
        "FROM Resource " +
        "INNER JOIN Resource_Tag " +
        "USING (Resource_ID) " +
        "WHERE Resource_Tag.Tag = ? " +
        " OR Title LIKE ? " +
        " ORDER BY Resource.Average_Rating DESC, Resource.Creation_Date DESC;");

      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, tag);
      preparedStatement.setString(2, anyMatch(title));

      return runQuery(preparedStatement);
    } catch (SQLException sqlException) {
      throw new RuntimeException("Error searching resources", sqlException);
    }
  }

  @Override
  public List<Resource> queryByTitle(String title) {
    try {
      String sql = ("SELECT" +
        " Resource.* " +
        "FROM Resource " +
        "LEFT JOIN Resource_Tag " +
        "USING (Resource_ID) " +
        "WHERE Title LIKE ? " +
        "ORDER BY Resource.Average_Rating DESC, Resource.Creation_Date DESC;");

      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, anyMatch(title));

      return runQuery(preparedStatement);
    } catch (SQLException sqlException) {
      throw new RuntimeException("Error getting resources", sqlException);
    }
  }

  @Override
  public List<Resource> queryByTag(String tag) {
    try {
      String sql = ("SELECT" +
        " Resource.* " +
        "FROM Resource " +
        "INNER JOIN Resource_Tag " +
        "USING (Resource_ID) " +
        "WHERE Resource_Tag.Tag = ? " +
        "ORDER BY Resource.Average_Rating DESC, Resource.Creation_Date DESC;");

      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, tag);

      return runQuery(preparedStatement);
    } catch (SQLException sqlException) {
      throw new RuntimeException("Error getting resources", sqlException);
    }
  }

  @Override
  public List<Resource> search(String... terms) {

    try {
      String sqlRegexpForTerms = createRegexpForTerms(terms);

      String sql = ("SELECT " +
        "DISTINCT Resource.Resource_ID, " +
        "Resource.Title, Resource.Link, Resource.Creation_Date, " +
        "Resource.Media_Type, Resource.Average_Rating, Resource.Email " +
        "FROM Resource " +
        "LEFT JOIN Resource_Tag " +
        "USING (Resource_ID) " +
        "WHERE Title REGEXP " + sqlRegexpForTerms + " " +
        "OR Resource_Tag.Tag REGEXP " + sqlRegexpForTerms + " " +
        "ORDER BY Resource.Average_Rating DESC, Resource.Creation_Date DESC;");

      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      setTermsOnStatement(preparedStatement, terms, 1);
      setTermsOnStatement(preparedStatement, terms, terms.length + 1);

      return runQuery(preparedStatement);
    } catch (SQLException sqlException) {
      throw new RuntimeException("Error getting resources", sqlException);
    }
  }

  private void setTermsOnStatement(PreparedStatement preparedStatement, String[] terms,
                                          int startStatementIndex) throws SQLException {
    for (int index = 0; index < terms.length; index++) {
      preparedStatement.setString(index + startStatementIndex, terms[index]);
    }
  }

  private String createRegexpForTerms(String[] terms) {
    String[] questionMarks = new String[terms.length];
    Arrays.fill(questionMarks, "?");
    return String.join(" || '|' || ", questionMarks);
  }

  private List<Resource> runQuery(PreparedStatement preparedStatement) throws SQLException {
    return executeResourceQuery(preparedStatement);
  }

  private static String anyMatch(String title) {
    return "%" + title + "%";
  }

}
