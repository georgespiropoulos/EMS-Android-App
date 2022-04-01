package ihuiee.webservices.DB;

import androidx.room.*;

@Entity(tableName = "hospitals")
public class Hospitals {
    @PrimaryKey
    @ColumnInfo(name = "name_hospital")
    public String nameOfHospital;

    @ColumnInfo(name = "clinics")
    public String clinicsOfHospital;

    @ColumnInfo(name = "address")
    public String hospitalAddress;
}