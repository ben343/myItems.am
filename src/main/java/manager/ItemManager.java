package manager;

import DB.DBConnectionProvider;
import model.Category;
import model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final UserManager userManager = new UserManager();
    private final CategoryManager categoryManager = new CategoryManager();


    public void addItem(Item item) {
        String sql = "insert into item(title,price,category_id,pic_url,user_id) values (?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getTitle());
            statement.setDouble(2, item.getPrice());
            statement.setInt(3, item.getCategory_id().getId());
            statement.setString(4, item.getPic_url());
            statement.setInt(5, item.getUser_id().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                item.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public List<Item> getLastItemsByCategory(Category category) {
        String sql = "SELECT * FROM item WHERE category_id = ? ORDER BY id DESC LIMIT 20";
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, category.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(getItemsFromResultSet(resultSet));
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    private Item getItemsFromResultSet(ResultSet resultSet) {
        try {
            return Item.builder()
                    .id(resultSet.getInt(1))
                    .title(resultSet.getString(2))
                    .price(resultSet.getDouble(3))
                    .category_id(categoryManager.getCategoryById(resultSet.getInt(4)))
                    .user_id(userManager.getUserById(resultSet.getInt(5)))
                    .pic_url(resultSet.getString(6))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Item> getUserAds(int userId){
        String sql = "SELECT * FROM item WHERE user_id = ?";
        List<Item> items = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,userId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                items.add(getItemsFromResultSet(resultSet));
            }
            return items;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}

















