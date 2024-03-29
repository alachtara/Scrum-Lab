package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.model.BCrypt;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    //-----------------Queries TO SQL-------------------------------

    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins (first_name, last_name, email, password, superadmin,enable) VALUES (?,?,?,?,?,?);";
    private static final String READ_ADMIN_QUERY="SELECT * FROM admins where email=?;";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins where id = ?;";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name = ? , last_name = ?, email = ? WHERE id = ?;";
    private static final String FIND_ALL_ADMINS_QUERY = "SELECT * FROM admins;";
    private static final String CHECK_IF_ADMIN_EXIST = "SELECT * FROM admins WHERE email = ? LIMIT 1";

//----------------------CRUD for Admin --------------------------------------

//------------------create Admin--------------------------------

    public static Admin create(Admin admin) {

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_IF_ADMIN_EXIST)) {
            String email = admin.getEmail();
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return null;
                } else {
                    try (PreparedStatement executeStatement = connection.prepareStatement(CREATE_ADMIN_QUERY,
                            PreparedStatement.RETURN_GENERATED_KEYS)) {
                        executeStatement.setString(1, admin.getFirstName());
                        executeStatement.setString(2, admin.getLastName());
                        executeStatement.setString(3, admin.getEmail());
                        executeStatement.setString(4, BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt()));
                        executeStatement.setInt(5, 0);
                        executeStatement.setInt(6, 1);

                        int result = executeStatement.executeUpdate();

                        if (result != 1) {
                            throw new RuntimeException("Execute update returned " + result);
                        }
                        try (ResultSet generatedKeys = executeStatement.getGeneratedKeys()) {
                            if (generatedKeys.first()) {
                                admin.setId(generatedKeys.getInt(1));
                                return admin;
                            } else {
                                throw new RuntimeException("Generated key was not found");
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

        //-----------------------read Admin---------------------------------ok
        public static Admin read(String email) {
            Admin admin= new Admin();
            try (Connection connection = DbUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(READ_ADMIN_QUERY)
            ) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        admin.setId(resultSet.getInt("id"));
                        admin.setFirstName(resultSet.getString("first_name"));
                        admin.setLastName(resultSet.getString("last_name"));
                        admin.setEmail(resultSet.getString("email"));
                        admin.setPassword(resultSet.getString("password"));
                        admin.setSuperadmin(resultSet.getInt("superadmin"));
                        admin.setEnable(resultSet.getInt("enable"));

                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return admin;
        }

        //---------------------------delete Admin----------------------------------ok

        public static void delete(int adminId) {
            try (Connection connection = DbUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_QUERY)) {
                statement.setInt(1, adminId);
                statement.executeUpdate();

                boolean deleted = statement.execute();
                if (!deleted) {
                    throw new NotFoundException("User not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//----------------------------update Admin----------------------------------ok
    public static boolean update(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_IF_ADMIN_EXIST)) {
                String email = admin.getEmail();
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return false;
                    } else {
                        try (PreparedStatement updateStatement = connection.prepareStatement(UPDATE_ADMIN_QUERY)) {
                            updateStatement.setInt(4, admin.getId());
                            updateStatement.setString(1, admin.getFirstName());
                            updateStatement.setString(2, admin.getLastName());
                            updateStatement.setString(3, admin.getEmail());

                            updateStatement.executeUpdate();
                            return true;
                        }
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
//--------------------------------find All------------------------ ok
    public static List<Admin> findAll() {

        List<Admin> adminsList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMINS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Admin newAdmin = new Admin();
                newAdmin.setId(resultSet.getInt("id"));
                newAdmin.setFirstName(resultSet.getString("first_name"));
                newAdmin.setLastName(resultSet.getString("last_name"));
                newAdmin.setEmail(resultSet.getString("email"));
                newAdmin.setPassword(resultSet.getString("password"));
                newAdmin.setSuperadmin(resultSet.getInt("superadmin"));
                newAdmin.setEnable(resultSet.getInt("enable"));

                adminsList.add(newAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminsList;
    }

    public static boolean checkIfEmailExist(String email) {
        try (Connection connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(CHECK_IF_ADMIN_EXIST)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}