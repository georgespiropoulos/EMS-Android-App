package ihuiee.webservices.DB;

import androidx.lifecycle.LiveData;
import androidx.room.*;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM users WHERE user_name = :name")
    LiveData<User> getUserByName(String name);

    @Insert
    void insertAll(User user);

    @Delete
    void delete(User user);
}
