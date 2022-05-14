package ihuiee.webservices.DB;

import androidx.room.*;

@Entity(tableName = "hospitals", primaryKeys = {"name_hospital"})
public class Hospitals {
    @ColumnInfo(name = "name_hospital")
    public String nameOfHospital;

    @ColumnInfo(name = "clinics")
    public String clinicsOfHospital;

    @ColumnInfo(name = "address")
    public String hospitalAddress;

    public Hospitals(String nameOfHospital, String clinicsOfHospital, String hospitalAddress) {
        this.nameOfHospital = nameOfHospital;
        this.clinicsOfHospital = clinicsOfHospital;
        this.hospitalAddress = hospitalAddress;
    }
}