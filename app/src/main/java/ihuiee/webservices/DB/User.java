package ihuiee.webservices.DB;

import androidx.room.*;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    public int userId;

    @ColumnInfo(name = "user_name")
    public String userName;
}
