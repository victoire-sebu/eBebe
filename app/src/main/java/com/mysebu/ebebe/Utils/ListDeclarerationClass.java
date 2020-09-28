package com.mysebu.ebebe.Utils;

public class ListDeclarerationClass {
    private String noms_enfant,sexe,date_naissance,date_register,nom_hopital,lieu_naissance,nom_pere,nom_mere,telephon_respo,mail_respo;
    private  String age_pere,age_mere,nationalite_pere,nationalite_mere,adresse_pere,adresse_mere,profession_pere,profession_mere;
    public ListDeclarerationClass(){

    }
    public ListDeclarerationClass(String noms_enfant, String sexe, String date_naissance, String date_register, String nom_hopital,
                                  String lieu_naissance, String nom_pere, String nom_mere, String telephon_respo, String mail_respo,
                                  String age_pere, String age_mere,String nationalite_pere,String nationalite_mere,String adresse_pere,
                                  String adresse_mere,String profession_pere,String profession_mere) {
        this.noms_enfant = noms_enfant;
        this.sexe = sexe;
        this.date_naissance = date_naissance;
        this.date_register = date_register;
        this.nom_hopital = nom_hopital;
        this.lieu_naissance = lieu_naissance;
        this.nom_pere = nom_pere;
        this.nom_mere = nom_mere;
        this.telephon_respo = telephon_respo;
        this.mail_respo = mail_respo;
        this.age_pere=age_pere;
        this.age_mere=age_mere;
        this.nationalite_pere=nationalite_pere;
        this.nationalite_mere=nationalite_mere;
        this.adresse_pere=adresse_pere;
        this.adresse_mere=adresse_mere;
        this.profession_pere=profession_pere;
        this.profession_mere=profession_mere;
    }

    public String getNoms_enfant() {
        return noms_enfant;
    }

    public void setNoms_enfant(String noms_enfant) {
        this.noms_enfant = noms_enfant;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getDate_register() {
        return date_register;
    }

    public void setDate_register(String date_register) {
        this.date_register = date_register;
    }

    public String getNom_hopital() {
        return nom_hopital;
    }

    public void setNom_hopital(String nom_hopital) {
        this.nom_hopital = nom_hopital;
    }

    public String getLieu_naissance() {
        return lieu_naissance;
    }

    public void setLieu_naissance(String lieu_naissance) {
        this.lieu_naissance = lieu_naissance;
    }

    public String getNom_pere() {
        return nom_pere;
    }

    public void setNom_pere(String nom_pere) {
        this.nom_pere = nom_pere;
    }

    public String getNom_mere() {
        return nom_mere;
    }

    public void setNom_mere(String nom_mere) {
        this.nom_mere = nom_mere;
    }

    public String getTelephon_respo() {
        return telephon_respo;
    }

    public void setTelephon_respo(String telephon_respo) {
        this.telephon_respo = telephon_respo;
    }

    public String getMail_respo() {
        return mail_respo;
    }

    public void setMail_respo(String mail_respo) {
        this.mail_respo = mail_respo;
    }

    public String getAge_pere() {
        return age_pere;
    }

    public void setAge_pere(String age_pere) {
        this.age_pere = age_pere;
    }

    public String getAge_mere() {
        return age_mere;
    }

    public void setAge_mere(String age_mere) {
        this.age_mere = age_mere;
    }

    public String getNationalite_pere() {
        return nationalite_pere;
    }

    public void setNationalite_pere(String nationalite_pere) {
        this.nationalite_pere = nationalite_pere;
    }

    public String getNationalite_mere() {
        return nationalite_mere;
    }

    public void setNationalite_mere(String nationalite_mere) {
        this.nationalite_mere = nationalite_mere;
    }

    public String getAdresse_pere() {
        return adresse_pere;
    }

    public void setAdresse_pere(String adresse_pere) {
        this.adresse_pere = adresse_pere;
    }

    public String getAdresse_mere() {
        return adresse_mere;
    }

    public void setAdresse_mere(String adresse_mere) {
        this.adresse_mere = adresse_mere;
    }

    public String getProfession_pere() {
        return profession_pere;
    }

    public void setProfession_pere(String profession_pere) {
        this.profession_pere = profession_pere;
    }

    public String getProfession_mere() {
        return profession_mere;
    }

    public void setProfession_mere(String profession_mere) {
        this.profession_mere = profession_mere;
    }
}
