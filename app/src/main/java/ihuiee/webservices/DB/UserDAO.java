package ihuiee.webservices.DB;

import androidx.room.*;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM users WHERE user_name = :name")
    User getUserByName(String name);

    @Insert
    void insertAll(User user);

    @Delete
    void delete(User user);
}
