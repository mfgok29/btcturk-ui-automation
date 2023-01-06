## **Açıklama**
POM kullanılarak google ve yandex arama motorlarında `keyword` aratılarak ilk 10 sonucu excele yazıp bu iki arama motorunda da bulduğumuzu aynı sonuçları Title, Description ve Url e göre ayırarak listelenmesi.



## __Nasıl Çalışır__

Öncelikle hangi browser da çalıştıracağımızı belirtmemiz gerek,
`configuration.properties` dosyasında browser fieldını `"chrome", "firefox", "edge"` vb. olarak girmemiz gerekiyor. Driver class ında değerleri görebilirsiniz.

`src/test/resources/suites/testng.xml` bu xml dosyasında aratılacak `keyword` ü girerek run ettiğimizde testimiz çalışacaktır.


video anlatım linki -> https://drive.google.com/file/d/1GYwrX0I1bmlHvto8VBtUyzWV6r5mtYgE/view?usp=share_link