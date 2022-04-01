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

    private static final String FIND_ALL_DN_QUERY = "SELECT * FROM day_name;";

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
