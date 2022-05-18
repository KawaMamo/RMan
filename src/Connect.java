import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Map;

public class Connect {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    Config config;

    public Connect() throws IOException {

        config = new Config();
        try {
            connect();
        } catch (Exception e) {
            e.printStackTrace();
            Notifications error = Notifications.create().text(e.getMessage()).title("unable to connect").position(Pos.BASELINE_LEFT).hideAfter(Duration.hours(1));
            error.showError();
        }
    }

    public void connect() throws Exception{
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver" );
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://"+config.getProp().get("host")+"/"+config.getProp().get("dBName")+"?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&"
                            + "user="+config.getProp().get("dBUser")+"&password="+config.getProp().get("dBPassword")+"");
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
        }catch (Exception e){
            throw e;
        }
    }

    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

    public int addCat(String cat) throws Exception{

        int insertedId = 0;
        try {
            preparedStatement = connect
                    .prepareStatement("insert into categories values (default, ?, default, default)", statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cat);
            int rowAffected = preparedStatement.executeUpdate();
            ResultSet rs = null;
            if(rowAffected == 1)
            {
                rs = preparedStatement.getGeneratedKeys();
                if(rs.next())
                    insertedId = rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {

        }

        return insertedId;
    }

    public int addSubCat(String cat, int catId) throws Exception{

        int insertedId = 0;
        try {
            preparedStatement = connect
                    .prepareStatement("insert into subCat values (default, ?, ?, default)", statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, catId);
            preparedStatement.setString(2, cat);
            int rowAffected = preparedStatement.executeUpdate();
            ResultSet rs = null;
            if(rowAffected == 1)
            {
                rs = preparedStatement.getGeneratedKeys();
                if(rs.next())
                    insertedId = rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {

        }

        return insertedId;
    }

    public int addReport(LocalDate reportDate, int catId, int subCatId, String reportText, String title) throws Exception{

        int insertedId = 0;
        try {
            preparedStatement = connect
                    .prepareStatement("insert into report values (default, ?, ?, ?, ?, default, ?)", statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, reportDate.toString());
            preparedStatement.setInt(2, catId);
            preparedStatement.setInt(3, subCatId);
            preparedStatement.setString(4, reportText);
            preparedStatement.setString(5, title);
            int rowAffected = preparedStatement.executeUpdate();
            ResultSet rs = null;
            if(rowAffected == 1)
            {
                rs = preparedStatement.getGeneratedKeys();
                if(rs.next())
                    insertedId = rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {

        }

        return insertedId;
    }

    public int addSuggestion(int reportId, String text) throws Exception{

        int insertedId = 0;
        try {
            preparedStatement = connect
                    .prepareStatement("insert into suggestions values (default, ?, ?, default)", statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, reportId);
            preparedStatement.setString(2, text);
            int rowAffected = preparedStatement.executeUpdate();
            ResultSet rs = null;
            if(rowAffected == 1)
            {
                rs = preparedStatement.getGeneratedKeys();
                if(rs.next())
                    insertedId = rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {

        }

        return insertedId;
    }

    public int addProject(int reportId, String text) throws Exception{

        int insertedId = 0;
        try {
            preparedStatement = connect
                    .prepareStatement("insert into projects values (default, ?, ?, default)", statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, reportId);
            preparedStatement.setString(2, text);
            int rowAffected = preparedStatement.executeUpdate();
            ResultSet rs = null;
            if(rowAffected == 1)
            {
                rs = preparedStatement.getGeneratedKeys();
                if(rs.next())
                    insertedId = rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {

        }

        return insertedId;
    }

    public ResultSet getCatList() throws Exception {
        ResultSet resultSet = null;

        String query = "SELECT * FROM categories";
        PreparedStatement preparedStatement = connect.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        return resultSet;

    }

    public int getCatCount() throws Exception {
        ResultSet resultSet = null;
        int counter = 0;
        String query = "SELECT COUNT(id) AS counter FROM categories";
        PreparedStatement preparedStatement = connect.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            counter = resultSet.getInt("counter");
        }
        return counter;

    }

    public int getSubCatCount(int catId) throws Exception {
        ResultSet resultSet = null;
        int counter = 0;
        String query = "SELECT COUNT(id) AS counter FROM subCat WHERE catId = ? ";
        PreparedStatement preparedStatement = connect.prepareStatement(query);
        preparedStatement.setInt(1, catId);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            counter = resultSet.getInt("counter");
        }
        return counter;

    }

    public ResultSet getSubCats() throws Exception {
        ResultSet resultSet = null;

        String query = "SELECT *, s.id AS sid FROM subcat s LEFT JOIN categories c ON c.id = s.catId";
        PreparedStatement preparedStatement = connect.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        return resultSet;

    }


    public ResultSet getSubCats(int catId) throws Exception {
        ResultSet resultSet = null;

        String query = "SELECT * FROM subcat s WHERE catId = ? ";
        PreparedStatement preparedStatement = connect.prepareStatement(query);
        preparedStatement.setInt(1, catId);
        resultSet = preparedStatement.executeQuery();

        return resultSet;

    }

    public ResultSet getSuggestion(int reportId) throws Exception {
        ResultSet resultSet = null;

        String query = "SELECT * FROM suggestions s WHERE reportId = ? ";
        PreparedStatement preparedStatement = connect.prepareStatement(query);
        preparedStatement.setInt(1, reportId);
        resultSet = preparedStatement.executeQuery();

        return resultSet;

    }

    public ResultSet getProjects(int reportId) throws Exception {
        ResultSet resultSet = null;

        String query = "SELECT * FROM projects p WHERE reportId = ? ";
        PreparedStatement preparedStatement = connect.prepareStatement(query);
        preparedStatement.setInt(1, reportId);
        resultSet = preparedStatement.executeQuery();

        return resultSet;

    }

    public int deleteCat(int[] id) {
        int rowAffected = -1;
        for(int idNumber: id){
            String query = "DELETE FROM categories WHERE id = ? ";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connect.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, idNumber);
                rowAffected = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return rowAffected;
    }

    public int deleteSubCat(int[] id) {
        int rowAffected = -1;
        for(int idNumber: id){
            String query = "DELETE FROM subcat WHERE id = ? ";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connect.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, idNumber);
                rowAffected = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return rowAffected;
    }

    public int updateCat(String text, int selectedId) throws SQLException {

        String query = "UPDATE categories SET categoryName = ? WHERE id = ? ";
        PreparedStatement preparedStatement = connect.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, text);
        preparedStatement.setInt(2, selectedId);
        int rowAffected = preparedStatement.executeUpdate();

        return rowAffected;
    }

    public int updateSubCat(String text, int selectedId) throws SQLException {

        String query = "UPDATE subcat SET subCatName = ? WHERE id = ? ";
        PreparedStatement preparedStatement = connect.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, text);
        preparedStatement.setInt(2, selectedId);
        int rowAffected = preparedStatement.executeUpdate();

        return rowAffected;
    }


    public ResultSet getReports(Map<String, String> whereClause) throws SQLException {
        ResultSet resultSet = null;
        String where = " ";
        int j = 0;
        for (Map.Entry<String, String> entry: whereClause.entrySet()){
            if(j == 0){
                where += " WHERE ";
            }
            if(j != 0){
                where += " AND ";
            }
            if(entry.getKey()== "reportText"){
                where += "r."+entry.getKey()+" LIKE ? ";
            }else {
                where += "r."+entry.getKey()+" = ? ";
            }

            j++;
        }

        String query = "SELECT * FROM report r" +
                " LEFT JOIN categories c ON c.id = r.catId " +
                " LEFT JOIN subcat s ON s.id = r.subCatId"+where;
        PreparedStatement preparedStatement = connect.prepareStatement(query);
        int i = 1;
        for (Map.Entry<String, String> entry: whereClause.entrySet()){
            if(entry.getKey() == "reportText"){
                preparedStatement.setString(i, "%"+entry.getValue()+"%");
            }else {
                preparedStatement.setString(i, entry.getValue());
            }

            i++;
        }
        resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    public int addImage(int reportId, String imageName, String newName) throws SQLException {
        int insertedId = 0;
        try {
            preparedStatement = connect
                    .prepareStatement("insert into uploadedimages values (default, ?, ?, ?, default)", statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, reportId);
            preparedStatement.setString(2, imageName);
            preparedStatement.setString(3, newName);
            int rowAffected = preparedStatement.executeUpdate();
            ResultSet rs = null;
            if(rowAffected == 1)
            {
                rs = preparedStatement.getGeneratedKeys();
                if(rs.next())
                    insertedId = rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {

        }

        return insertedId;
    }
}
