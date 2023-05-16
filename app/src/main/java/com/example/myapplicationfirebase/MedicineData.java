package com.example.myapplicationfirebase;

public class MedicineData {

    String MedName, MedDescription, MedMFD, MedEXP, MedPrice;

    public MedicineData() {
    }

    public MedicineData(String medName, String medDescription, String medMFD, String medEXP, String medPrice) {
        MedName = medName;
        MedDescription = medDescription;
        MedMFD = medMFD;
        MedEXP = medEXP;
        MedPrice = medPrice;
    }

    public String getMedName() {
        return MedName;
    }

    public void setMedName(String medName) {
        MedName = medName;
    }

    public String getMedDescription() {
        return MedDescription;
    }

    public void setMedDescription(String medDescription) {
        MedDescription = medDescription;
    }

    public String getMedMFD() {
        return MedMFD;
    }

    public void setMedMFD(String medMFD) {
        MedMFD = medMFD;
    }

    public String getMedEXP() {
        return MedEXP;
    }

    public void setMedEXP(String medEXP) {
        MedEXP = medEXP;
    }

    public String getMedPrice() {
        return MedPrice;
    }

    public void setMedPrice(String medPrice) {
        MedPrice = medPrice;
    }
}
