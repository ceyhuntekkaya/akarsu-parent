personellerTableAdapter
DELETE FROM [personeller] WHERE (([personelId] = @Original_personelId))
INSERT INTO [personeller] ([personelAd], [birim], [kullanici], [sifre], [durum], [yetki]) VALUES (@personelAd, @birim, @kullanici, @sifre, @durum, @yetki)
SELECT * FROM personeller WHERE (durum = 1) ORDER BY personelAd
UPDATE [personeller] SET [personelAd] = @personelAd, [birim] = @birim, [kullanici] = @kullanici, [sifre] = @sifre, [durum] = @durum, [yetki] = @yetki WHERE (([personelId] = @Original_personelId))
    ekle
INSERT INTO [personeller] ([personelAd], [birim], [kullanici], [sifre], [durum], [yetki]) VALUES (@personelAd, @birim, @kullanici, @sifre, @durum, @yetki)
    guncelle
UPDATE [personeller] SET [personelAd] = @personelAd, [birim] = @birim, [kullanici] = @kullanici, [sifre] = @sifre, [durum] = @durum, [yetki] = @yetki WHERE (([personelId] = @Original_personelId))
    login
SELECT  personelId, personelAd, birim, kullanici, sifre, durum, yetki FROM personeller WHERE (durum = 1) AND (kullanici = @kullanici) AND (sifre = @sifre) ORDER BY personelAd
    sifre
UPDATE personeller SET sifre = @sifre WHERE (personelId = @Original_personelId)
    sil
DELETE FROM [personeller] WHERE (([personelId] = @Original_personelId))
    ________________________________
    evraklarTableAdapter
DELETE FROM [evraklar] WHERE (([evrakId] = @Original_evrakId))
INSERT INTO [evraklar] ([tur], [grup], [proje], [tarih], [sayi], [konu], [yetkiDuzeyi], [kayitTarih], [kaydeden], [arsiv], [evrakAdres], [bagli], [sahip], [ocr]) VALUES (@tur, @grup, @proje, @tarih, @sayi, @konu, @yetkiDuzeyi, @kayitTarih, @kaydeden, @arsiv, @evrakAdres, @bagli, @sahip, @ocr)
SELECT evraklar.* FROM evraklar
                           UPDATE [evraklar] SET [tur] = @tur, [grup] = @grup, [proje] = @proje, [tarih] = @tarih, [sayi] = @sayi, [konu] = @konu, [yetkiDuzeyi] = @yetkiDuzeyi, [kayitTarih] = @kayitTarih, [kaydeden] = @kaydeden, [arsiv] = @arsiv, [evrakAdres] = @evrakAdres, [bagli] = @bagli, [sahip] = @sahip, [ocr] = @ocr WHERE (([evrakId] = @Original_evrakId))
    arsivle
UPDATE evraklar SET arsiv = 1 WHERE (evrakId = @Original_evrakId)
    guncelle
UPDATE evraklar SET tur = @tur, grup = @grup, proje = @proje, tarih = @tarih, sayi = @sayi, konu = @konu, yetkiDuzeyi = @yetkiDuzeyi, sahip = @sahip WHERE (evrakId = @Original_evrakId)
    proje
SELECT *  FROM evraklar WHERE (proje = @proje)
    projeSayiVarmi
SELECT evrakId, tur, grup, proje, tarih, sayi, konu, yetkiDuzeyi, kayitTarih, kaydeden, arsiv, evrakAdres, bagli, sahip, ocr FROM evraklar WHERE (proje = @proje) AND (sayi = @sayi)
    sil
DELETE FROM [evraklar] WHERE (([evrakId] = @Original_evrakId))
    ________________________________
    projelerTableAdapter
DELETE FROM [projeler] WHERE (([projeId] = @Original_projeId))
INSERT INTO [projeler] ([projeAdi], [aciklama], [aktif], [yetkiDuzeyi], [acan], [tarih]) VALUES (@projeAdi, @aciklama, @aktif, @yetkiDuzeyi, @acan, @tarih)
SELECT projeId, projeAdi, aciklama, aktif, yetkiDuzeyi, acan, tarih FROM projeler ORDER BY projeAdi
UPDATE [projeler] SET [projeAdi] = @projeAdi, [aciklama] = @aciklama, [aktif] = @aktif, [yetkiDuzeyi] = @yetkiDuzeyi, [acan] = @acan, [tarih] = @tarih WHERE (([projeId] = @Original_projeId))
    aktif
SELECT projeId, projeAdi, aciklama, aktif, yetkiDuzeyi, acan, tarih FROM projeler WHERE (aktif = 1) ORDER BY projeAdi
    arsiv
SELECT projeler.* FROM projeler WHERE (aktif = 0)
    arsivle
UPDATE projeler SET aktif = 0 WHERE (projeId = @Original_projeId)
    ekle
INSERT INTO [projeler] ([projeAdi], [aciklama], [aktif], [yetkiDuzeyi], [acan], [tarih]) VALUES (@projeAdi, @aciklama, @aktif, @yetkiDuzeyi, @acan, @tarih)
    geriArsivle
UPDATE projeler SET aktif = 1 WHERE (projeId = @Original_projeId)
    guncelle
UPDATE projeler SET projeAdi = @projeAdi, aciklama = @aciklama, yetkiDuzeyi = @yetkiDuzeyi WHERE (projeId = @Original_projeId)
    sil
DELETE FROM [projeler] WHERE (([projeId] = @Original_projeId))
    ________________________________
    docsTableAdapter
DELETE FROM [docs] WHERE (([docId] = @Original_docId))
INSERT INTO [docs] ([kayitId], [ad], [tur]) VALUES (@kayitId, @ad, @tur)
SELECT docs.* FROM docs WHERE (kayitId = @kayitId)
UPDATE [docs] SET [kayitId] = @kayitId, [ad] = @ad, [tur] = @tur WHERE (([docId] = @Original_docId))
    ekle
INSERT INTO [docs] ([kayitId], [ad], [tur]) VALUES (@kayitId, @ad, @tur)
    sil
DELETE FROM [docs] WHERE (([docId] = @Original_docId))
    ________________________________
    islemlerimTableAdapter
DELETE FROM [islemlerim] WHERE (([kayitId] = @Original_kayitId))
INSERT INTO [islemlerim] ([evrakNo], [gonderen], [personel], [tarih], [okunma], [notu], [gonderilmeTarihi]) VALUES (@evrakNo, @gonderen, @personel, @tarih, @okunma, @notu, @gonderilmeTarihi)
SELECT kayitId, evrakNo, gonderen, personel, tarih, okunma, notu, gonderilmeTarihi FROM islemlerim WHERE (evrakNo = @evrakNo)
UPDATE [islemlerim] SET [evrakNo] = @evrakNo, [gonderen] = @gonderen, [personel] = @personel, [tarih] = @tarih, [okunma] = @okunma, [notu] = @notu, [gonderilmeTarihi] = @gonderilmeTarihi WHERE (([kayitId] = @Original_kayitId))
    evraklarim
SELECT kayitId, evrakNo, gonderen, personel, tarih, okunma, notu, gonderilmeTarihi FROM islemlerim WHERE (personel = @personel) AND (gonderilmeTarihi IS NULL)
    gonder
INSERT INTO islemlerim  (evrakNo, gonderen, personel, tarih, notu) VALUES (@evrakNo,@gonderen,@personel, GETDATE(), @notu)
    gonderilmeTarihi
UPDATE islemlerim SET gonderilmeTarihi = GETDATE() WHERE (kayitId = @Original_kayitId)
    okundu
UPDATE islemlerim SET okunma = getDate() WHERE (kayitId = @Original_kayitId)
    personeldeVarMi
SELECT kayitId, evrakNo, gonderen, personel, tarih, okunma, notu, gonderilmeTarihi FROM islemlerim WHERE (evrakNo = @evrakNo) AND (personel = @personel) AND (gonderilmeTarihi IS NULL)
    tamamla
UPDATE islemlerim SET personel = @personel, gonderilmeTarihi = GETDATE() WHERE (kayitId = @Original_kayitId)
    ________________________________
evraklarimTableAdapter
evraklarim
SELECT islemlerim.kayitId, islemlerim.evrakNo, islemlerim.gonderen, islemlerim.personel, islemlerim.okunma, islemlerim.notu, islemlerim.gonderilmeTarihi,  evraklar.evrakId, evraklar.tur, evraklar.grup, evraklar.proje, evraklar.tarih AS evrakTarih, evraklar.sayi, evraklar.konu, evraklar.yetkiDuzeyi, evraklar.kayitTarih,  evraklar.kaydeden, evraklar.arsiv, evraklar.evrakAdres, evraklar.bagli, evraklar.sahip, evraklar.ocr, islemlerim.tarih, projeler.projeId, projeler.projeAdi,  personeller.personelAd FROM islemlerim INNER JOIN evraklar ON islemlerim.evrakNo = evraklar.evrakId INNER JOIN projeler ON evraklar.proje = projeler.projeId INNER JOIN personeller ON evraklar.sahip = personeller.personelId WHERE (islemlerim.personel = @personel) AND (islemlerim.gonderilmeTarihi IS NULL)
    evraklarimProje
SELECT islemlerim.kayitId, islemlerim.evrakNo, islemlerim.gonderen, islemlerim.personel, islemlerim.okunma, islemlerim.notu, islemlerim.gonderilmeTarihi,  evraklar.evrakId, evraklar.tur, evraklar.grup, evraklar.proje, evraklar.tarih AS evrakTarih, evraklar.sayi, evraklar.konu, evraklar.yetkiDuzeyi, evraklar.kayitTarih,  evraklar.kaydeden, evraklar.arsiv, evraklar.evrakAdres, evraklar.bagli, evraklar.sahip, evraklar.ocr, islemlerim.tarih, projeler.projeId, projeler.projeAdi FROM islemlerim INNER JOIN evraklar ON islemlerim.evrakNo = evraklar.evrakId INNER JOIN projeler ON evraklar.proje = projeler.projeId WHERE (islemlerim.personel = @personel) AND (evraklar.proje = @proje)  AND (islemlerim.gonderilmeTarihi IS NULL)
    tek
SELECT islemlerim.kayitId, islemlerim.evrakNo, islemlerim.gonderen, islemlerim.personel, islemlerim.okunma, islemlerim.notu, islemlerim.gonderilmeTarihi,  evraklar.evrakId, evraklar.tur, evraklar.grup, evraklar.proje, evraklar.tarih AS evrakTarih, evraklar.sayi, evraklar.konu, evraklar.yetkiDuzeyi, evraklar.kayitTarih,  evraklar.kaydeden, evraklar.arsiv, evraklar.evrakAdres, evraklar.bagli, evraklar.sahip, evraklar.ocr, islemlerim.tarih, projeler.projeId, projeler.projeAdi FROM islemlerim INNER JOIN evraklar ON islemlerim.evrakNo = evraklar.evrakId INNER JOIN projeler ON evraklar.proje = projeler.projeId WHERE (islemlerim.kayitId = @kayitId)
    ________________________________
evrakDolasimTableAdapter
SELECT islemlerim.kayitId, islemlerim.evrakNo, islemlerim.gonderen, islemlerim.personel, islemlerim.tarih, islemlerim.okunma, islemlerim.notu,  islemlerim.gonderilmeTarihi, personeller_1.personelAd AS gonderenPersonel, personeller.personelAd FROM islemlerim INNER JOIN personeller AS personeller_1 ON islemlerim.gonderen = personeller_1.personelId INNER JOIN personeller ON islemlerim.personel = personeller.personelId WHERE (islemlerim.evrakNo = @evrakNo)
    ________________________________
evrakAramaTableAdapter
Fill
SELECT evraklar.evrakId, evraklar.tur, evraklar.grup, evraklar.proje, evraklar.tarih, evraklar.sayi, evraklar.konu, evraklar.yetkiDuzeyi, evraklar.kayitTarih, evraklar.kaydeden, evraklar.arsiv, evraklar.evrakAdres, evraklar.bagli, evraklar.sahip, evraklar.ocr, projeler.projeAdi, personeller.personelAd FROM evraklar INNER JOIN projeler ON evraklar.proje = projeler.projeId INNER JOIN personeller ON evraklar.sahip = personeller.personelId WHERE (evraklar.sayi LIKE N'%' + @sayi + N'%') AND (evraklar.konu LIKE N'%' + @konu + N'%') AND (evraklar.ocr LIKE N'%' + @kelime + N'%') AND (evraklar.tur LIKE N'%' + @tur + N'%') AND (evraklar.grup LIKE N'%' + @grup + N'%') AND (evraklar.yetkiDuzeyi &lt;= @yetki) AND (evraklar.arsiv = 0)
proje
SELECT evraklar.arsiv, evraklar.bagli, evraklar.evrakAdres, evraklar.evrakId, evraklar.grup, evraklar.kaydeden, evraklar.kayitTarih, evraklar.konu, evraklar.ocr, evraklar.proje, evraklar.sahip, evraklar.sayi, evraklar.tarih, evraklar.tur, evraklar.yetkiDuzeyi, projeler.projeAdi, personeller.personelAd FROM evraklar INNER JOIN projeler ON evraklar.proje = projeler.projeId INNER JOIN personeller ON evraklar.sahip = personeller.personelId WHERE (evraklar.sayi LIKE N'%' + @sayi + N'%') AND (evraklar.konu LIKE N'%' + @konu + N'%') AND (evraklar.ocr LIKE N'%' + @kelime + N'%') AND (evraklar.tur LIKE N'%' + @tur + N'%') AND (evraklar.grup LIKE N'%' + @grup + N'%') AND (evraklar.proje = @proje) AND (evraklar.yetkiDuzeyi &lt; @yetki) AND (evraklar.arsiv = 0)
projeTarih
SELECT evraklar.arsiv, evraklar.bagli, evraklar.evrakAdres, evraklar.evrakId, evraklar.grup, evraklar.kaydeden, evraklar.kayitTarih, evraklar.konu, evraklar.ocr, evraklar.proje, evraklar.sahip, evraklar.sayi, evraklar.tarih, evraklar.tur, evraklar.yetkiDuzeyi, projeler.projeAdi, personeller.personelAd FROM evraklar INNER JOIN projeler ON evraklar.proje = projeler.projeId INNER JOIN personeller ON evraklar.sahip = personeller.personelId WHERE (evraklar.sayi LIKE N'%' + @sayi + N'%') AND (evraklar.konu LIKE N'%' + @konu + N'%') AND (evraklar.ocr LIKE N'%' + @kelime + N'%') AND (evraklar.tur LIKE N'%' + @tur + N'%') AND (evraklar.grup LIKE N'%' + @grup + N'%') AND (evraklar.tarih &gt;= @tarih1) AND (evraklar.tarih &lt;= @tarih2) AND (evraklar.proje = @proje) AND (evraklar.yetkiDuzeyi &lt; @yetki) AND (evraklar.arsiv = 0)
tarih
SELECT evraklar.arsiv, evraklar.bagli, evraklar.evrakAdres, evraklar.evrakId, evraklar.grup, evraklar.kaydeden, evraklar.kayitTarih, evraklar.konu, evraklar.ocr,  evraklar.proje, evraklar.sahip, evraklar.sayi, evraklar.tarih, evraklar.tur, evraklar.yetkiDuzeyi, projeler.projeAdi, personeller.personelAd FROM evraklar INNER JOIN projeler ON evraklar.proje = projeler.projeId INNER JOIN personeller ON evraklar.sahip = personeller.personelId WHERE (evraklar.sayi LIKE N'%' + @sayi + N'%') AND (evraklar.konu LIKE N'%' + @konu + N'%') AND (evraklar.ocr LIKE N'%' + @kelime + N'%') AND (evraklar.tur LIKE N'%' + @tur + N'%') AND (evraklar.grup LIKE N'%' + @grup + N'%') AND (evraklar.tarih &gt;= @tarih1) AND (evraklar.tarih &lt;= @tarih2) AND (evraklar.yetkiDuzeyi &lt; @yetki) AND (evraklar.arsiv = 0)
________________________________
evrakAramaArsivTableAdapter
DELETE FROM [evraklar] WHERE (([evrakId] = @Original_evrakId))
INSERT INTO [evraklar] ([tur], [grup], [proje], [tarih], [sayi], [konu], [yetkiDuzeyi], [kayitTarih], [kaydeden], [arsiv], [evrakAdres], [bagli], [sahip], [ocr]) VALUES (@tur, @grup, @proje, @tarih, @sayi, @konu, @yetkiDuzeyi, @kayitTarih, @kaydeden, @arsiv, @evrakAdres, @bagli, @sahip, @ocr)
SELECT evrakId, tur, grup, proje, tarih, sayi, konu, yetkiDuzeyi, kayitTarih, kaydeden, arsiv, evrakAdres, bagli, sahip, ocr FROM evraklar WHERE (sayi LIKE N'%' + @sayi + N'%') AND (konu LIKE N'%' + @konu + N'%') AND (ocr LIKE N'%' + @kelime + N'%') AND (tur LIKE N'%' + @tur + N'%') AND  (grup LIKE N'%' + @grup + N'%') AND (yetkiDuzeyi &lt; @yetki) AND (arsiv = 1)
UPDATE [evraklar] SET [tur] = @tur, [grup] = @grup, [proje] = @proje, [tarih] = @tarih, [sayi] = @sayi, [konu] = @konu, [yetkiDuzeyi] = @yetkiDuzeyi, [kayitTarih] = @kayitTarih, [kaydeden] = @kaydeden, [arsiv] = @arsiv, [evrakAdres] = @evrakAdres, [bagli] = @bagli, [sahip] = @sahip, [ocr] = @ocr WHERE (([evrakId] = @Original_evrakId))
proje
SELECT arsiv, bagli, evrakAdres, evrakId, grup, kaydeden, kayitTarih, konu, ocr, proje, sahip, sayi, tarih, tur, yetkiDuzeyi FROM evraklar WHERE (sayi LIKE N'%' + @sayi + N'%') AND (konu LIKE N'%' + @konu + N'%') AND (ocr LIKE N'%' + @kelime + N'%') AND (tur LIKE N'%' + @tur + N'%') AND (grup LIKE N'%' + @grup + N'%') AND (proje = @proje) AND (yetkiDuzeyi &lt; @yetki) AND (arsiv = 1)
projeTarih
SELECT arsiv, bagli, evrakAdres, evrakId, grup, kaydeden, kayitTarih, konu, ocr, proje, sahip, sayi, tarih, tur, yetkiDuzeyi FROM evraklar WHERE (sayi LIKE N'%' + @sayi + N'%') AND (konu LIKE N'%' + @konu + N'%') AND (ocr LIKE N'%' + @kelime + N'%') AND (tur LIKE N'%' + @tur + N'%') AND (grup LIKE N'%' + @grup + N'%') AND (tarih &lt;= @tarih1) AND (tarih &gt;= @tarih2) AND (proje = @proje) AND (yetkiDuzeyi &lt; @yetki) AND (arsiv = 1)
tarih
SELECT arsiv, bagli, evrakAdres, evrakId, grup, kaydeden, kayitTarih, konu, ocr, proje, sahip, sayi, tarih, tur, yetkiDuzeyi FROM evraklar WHERE (sayi LIKE N'%' + @sayi + N'%') AND (konu LIKE N'%' + @konu + N'%') AND (ocr LIKE N'%' + @kelime + N'%') AND (tur LIKE N'%' + @tur + N'%') AND (grup LIKE N'%' + @grup + N'%') AND (tarih &lt;= @tarih1) AND (tarih &gt;= @tarih2) AND (yetkiDuzeyi &lt; @yetki) AND (arsiv = 1)
________________________________
logTableAdapter
SELECT [log].logId, [log].evrakId, [log].personel, [log].islem, [log].tarih, [log].ip, personeller.personelAd FROM [log] INNER JOIN personeller ON [log].personel = personeller.personelId WHERE ([log].evrakId = @evrakId)
INSERT INTO [log] ([evrakId], [personel], [islem], [tarih], [ip]) VALUES (@evrakId, @personel, @islem, @tarih, @ip)
    ________________________________
    metinlerTableAdapter
DELETE FROM [metinler] WHERE (([metinId] = @Original_metinId))
INSERT INTO [metinler] ([baslik], [metin], [tarih], [yazan], [durum]) VALUES (@baslik, @metin, @tarih, @yazan, @durum)
SELECT metinler.* FROM metinler WHERE (durum = 1) ORDER BY metinId DESC
UPDATE [metinler] SET [baslik] = @baslik, [metin] = @metin, [tarih] = @tarih, [yazan] = @yazan, [durum] = @durum WHERE (([metinId] = @Original_metinId))
    ekle
INSERT INTO [metinler] ([baslik], [metin], [tarih], [yazan], [durum]) VALUES (@baslik, @metin, @tarih, @yazan, @durum)
    guncelle
UPDATE metinler SET baslik = @baslik, metin = @metin WHERE (metinId = @Original_metinId)
    sil
DELETE FROM [metinler] WHERE (([metinId] = @Original_metinId))
    tamamla
UPDATE metinler SET durum = @durum WHERE (metinId = @Original_metinId)
    tek
SELECT metinId, baslik, metin, tarih, yazan, durum FROM metinler WHERE (metinId = @metinId)
    tumu
SELECT metinler.* FROM metinler ORDER BY metinId DESC
    ________________________________
yetkilerTableAdapter
DELETE FROM [yetkiler] WHERE (([yetkiId] = @Original_yetkiId))
INSERT INTO [yetkiler] ([personelId], [evrakArama], [arsiv], [tarama], [admin], [metin], [proje], [silme]) VALUES (@personelId, @evrakArama, @arsiv, @tarama, @admin, @metin, @proje, @silme)
SELECT yetkiler.* FROM yetkiler WHERE (personelId = @personel)
UPDATE [yetkiler] SET [personelId] = @personelId, [evrakArama] = @evrakArama, [arsiv] = @arsiv, [tarama] = @tarama, [admin] = @admin, [metin] = @metin, [proje] = @proje, [silme] = @silme WHERE (([yetkiId] = @Original_yetkiId))
    ekle
INSERT INTO [yetkiler] ([personelId], [evrakArama], [arsiv], [tarama], [admin], [metin], [proje], [silme]) VALUES (@personelId, @evrakArama, @arsiv, @tarama, @admin, @metin, @proje, @silme)
    guncelle
UPDATE yetkiler SET evrakArama = @evrakArama, arsiv = @arsiv, tarama = @tarama, admin = @admin, metin = @metin, proje = @proje, silme = @silme WHERE (personelId = @personelId)