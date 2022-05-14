package ihuiee.webservices.DB;

import androidx.annotation.NonNull;
import androidx.room.*;

@Entity(tableName = "hospitals")
public class Hospitals {
    @PrimaryKey
    @ColumnInfo(name = "name_hospital") @NonNull
    public String nameOfHospital;

    @ColumnInfo(name = "clinics")
    public String clinicsOfHospital;

    @ColumnInfo(name = "address")
    public String hospitalAddress;
}