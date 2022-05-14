package ihuiee.webservices.DB;

import androidx.annotation.NonNull;
import androidx.room.*;

@Entity(tableName = "hospitals", primaryKeys = {"name_hospital"})
public class Hospitals {
    @ColumnInfo(name = "name_hospital") @NonNull
    public String nameOfHospital;

    @ColumnInfo(name = "clinics")
    public String clinicsOfHospital;

    @ColumnInfo(name = "address")
    public String hospitalAddress;

    public Hospitals(@NonNull String nameOfHospital, String clinicsOfHospital, String hospitalAddress) {
        this.nameOfHospital = nameOfHospital;
        this.clinicsOfHospital = clinicsOfHospital;
        this.hospitalAddress = hospitalAddress;
    }
}