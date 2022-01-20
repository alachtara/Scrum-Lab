package pl.coderslab.dao;


import pl.coderslab.model.DayName;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DayNameDao {


    private static final String CREATE_DN_QUERY = "INSERT INTO day_name(id, name, display_order) VALUES (?,?,?);";
    private static final String DELETE_DN_QUERY = "DELETE FROM day_name where id = ?;";
    private static final String FIND_ALL_DN_QUERY = "SELECT * FROM day_name;";
    private static final String READ_DN_QUERY = "SELECT * day_name where id = ?;";
    private static final String UPDATE_DN_QUERY = "UPDATE day_name SET name = ?, display_order = ?, WHERE	id = ?;";


    public List<DayName> findAll() {
        List<DayName> dayList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_DN_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                DayName oneDay = new DayName();
                oneDay.setId(resultSet.getInt("id"));
                oneDay.setDayName(resultSet.getString("name"));
                oneDay.setDisplayOrder(resultSet.getInt("display_order"));
                dayList.add(oneDay);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dayList;

    }

}
