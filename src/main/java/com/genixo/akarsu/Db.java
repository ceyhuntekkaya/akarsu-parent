package com.genixo.akarsu;

public class Db {
    public static class PersonellerTableAdapter {
        String delete = "DELETE FROM [personeller] WHERE (([personelId] = @Original_personelId))";
        String insert = "INSERT INTO [personeller] ([personelAd], [birim], [kullanici], [sifre], [durum], [yetki]) VALUES (@personelAd, @birim, @kullanici, @sifre, @durum, @yetki)";
        String select = "SELECT * FROM personeller WHERE (durum = 1) ORDER BY personelAd";
        String update = "UPDATE [personeller] SET [personelAd] = @personelAd, [birim] = @birim, [kullanici] = @kullanici, [sifre] = @sifre, [durum] = @durum, [yetki] = @yetki WHERE (([personelId] = @Original_personelId))";
        String ekle = "INSERT INTO [personeller] ([personelAd], [birim], [kullanici], [sifre], [durum], [yetki]) VALUES (@personelAd, @birim, @kullanici, @sifre, @durum, @yetki)";
        String guncelle = "UPDATE [personeller] SET [personelAd] = @personelAd, [birim] = @birim, [kullanici] = @kullanici, [sifre] = @sifre, [durum] = @durum, [yetki] = @yetki WHERE (([personelId] = @Original_personelId))";
        String login = "SELECT  personelId, personelAd, birim, kullanici, sifre, durum, yetki FROM personeller WHERE (durum = 1) AND (kullanici = @kullanici) AND (sifre = @sifre) ORDER BY personelAd";
        String sifre = "UPDATE personeller SET sifre = @sifre WHERE (personelId = @Original_personelId)";
        String sil = "DELETE FROM [personeller] WHERE (([personelId] = @Original_personelId))";
    }


}
