package manager;

import DB.DBConnectionProvider;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    Connection connection = DBConnectionProvider.getInstance().getConnection();

    public Category getCategoryByName(String catName) {
        String sql = "SELECT * FROM category WHERE name = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, catName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getCatFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }return  null;


    }
    private Category getCatFromResultSet(ResultSet resultSet) {
        try{
            return Category.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .build();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Category> getAllCategories(){
        String sql = "SELECT * FROM category";
        List<Category> categories = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                categories.add(getCatFromResultSet(resultSet));
            }
            return categories;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Category getCategoryById(int id){
        String sql = "SELECT * FROM category WHERE id = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return getCatFromResultSet(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
