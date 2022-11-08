# MusicService
## Описание проекта
### Окно авторизации
Пользователь попадает на окно авторизации где может либо авторизироваться, либо зарегистрироваться.

После регистрации пользователю приходит сообщение либо об успешной регистрации, либо об ошибке(вместе с ее описанием).

После регистрации пользователь может авторизироваться и перейти непосредственно к ресурсу.

### Основное окно ресурса
Если авторизовался пользователь с ролью "User" он может: прослушивать музыку, искать песни по названию, искать песни и альбомы исполнителя, добавлять песни в свой плейлист, купить или улучшить свою подписку. Без подписки прослушивание музыки запрещено.

У пользователя с ролью "User" из доступных вкладок есть "Муызка" где он может искать музыку и добавлять себе в плейлист, вкладка "Плейлист" где пользователь может слушать музыку из своего плейлиста и удалить треки из него, "вкладка "Подписка" где пользователь может посмотреть до какого числа у него еще есть подписка и если же подписки нет, то может купить ее.

Музыку которую может слушать пользователь обязательно должна быть проверена Админом на цензуру.

У пользователя с ролью "Artist" добавляется дополнительные вкладки "Добавить песню" и "Организация".Во вкладке "Добавить песню" он отправляет песню и дожидается пока админ ее проверит, после чего, сможет выгрузить ее на площадку.Во вкладке "Организация" по дефолту он не состоит в организации, но может выбрать существующие организации и всупить в нее.

У пользователя с ролью "Admin" добавляется дополнительные вкладки "Проверить песни","Создать юзера" и "Создать Организацию".В "Проверить песню" админ проверяет песни артистов на цензуру.Во вкладке "Создать юзера" админ может создать юзеров со всеми возможными ролями: "Admin","Artist","User". Во вклдаке "Создать организацию" админ создает организацию в которую может вступить пользователь с ролью "Artist".

## Запросы к серверу
### 1)Регистрация пользователя
requestType : POST   
url : http://localhost:8080/api/auth/signup 
request :  
{  
    "name" : "Name",  
    "surname" : "Surname",  
    "login" : "Login",  
    "password" : "password",  
    "role" : "admin",  
    "countryId" : "Россия",  
    "profileImage" : "profileImage"  
}  
### 2)Авторизация пользователя
requestType : POST   
url : http://localhost:8080/api/auth/signin  
request :  
{  
    "login" : "Login",  
    "password" : "password"  
}  
### 3)Проверка токена
requestType : POST   
url : http://localhost:8080/api/auth/checkToken  
request :  
{  
    "token" : "token"  
}  
### 4)Добавить организацию(Создать организацию может только Admin)
requestType : POST  
url : http://localhost:8080/api/admin/addOrganisation  
header : Authorization Bearer + token  
request :  
{  
    "description" : "описание организации",  
    "name" : "orgName",  
    "countryName" : "Россия"  
}  
### 5)Получить песни на провекру(Получить может только Admin)
requestType : GET  
url : http://localhost:8080/api/admin/getSongsForAdmin  
header : Authorization Bearer + token  
### 6)Проверить песню(Проверить может только Admin)
requestType : POST  
url : http://localhost:8080/api/admin/checkSong  
header : Authorization Bearer + token  
request :  
{  
    "userId" : userId,  
    "songId" : songId  
}  
### 7)Удалить проверяемую песню(Проверить может только Admin)
requestType : POST  
url : http://localhost:8080/api/admin/checkSongReject  
header : Authorization Bearer + token  
request :  
{  
    "userId" : userId,  
    "songId" : songId  
}  
### 8)Добавление артиста в базу(может только Admin)
requestType : POST    
url : http://localhost:8080/api/artist/addArtist  
header : Authorization Bearer + token 
request :  
{  
    "description" : "Описание артиста!",  
    "login" : "login",  
    "name" : "nickname"  
}  
### 9)Получить все организации(Получить список организация может Artist и Admin)
requestType : GET  
url : http://localhost:8080/api/artist/getOrganisation  
header : Authorization Bearer + token  
### 10)Добавить альбом(Создать альбом может только Artist)
requestType : POST  
url : http://localhost:8080/api/artist/addAlbum  
header : Authorization Bearer + token  
request :  
{  
    "artistNames" : ["names","names"],  
    "name" : "name",  
    "description" : "description",  
    "link" : "link"  
}  
### 11)Вступить в организацию(Вступить может только Artist)
requestType : POST  
url : http://localhost:8080/api/artist/setOragnisationToArtist  
header : Authorization Bearer + token  
request :  
{  
    "orgId" : orgId,  
    "userId" : userId     
}  
### 12)Выйти из организации(Выйти может только Artist)
requestType : POST  
url : http://localhost:8080/api/artist/quitFromOrganisation  
header : Authorization Bearer + token  
request :  
{   
    "userId" : userId     
}  
### 13)Добавить песню(Добавить может только Artist)
requestType : POST  
url : http://localhost:8080/api/artist/addSong  
header : Authorization Bearer + token  
request :  
{   
    "artistNames" : ["names","names"],  
    "featuresName" : ["names","names"],  
    "name" : "name",  
    "duration" : duration,  
    "albumName" : "albumName",  
    "genre" : "genre",  
    "link" : "link"  
}  
### 14)Получить все альбомы по artistId(Может только Artist)
requestType : POST  
url : http://localhost:8080/api/artist/getAllAlbumsByArtistId  
header : Authorization Bearer + token  
request :  
{   
    "artistId" : artistId  
}  
### 15)Добавление подписки пользователю
requestType : POST   
url : http://localhost:8080/api/user/addSub  
request :  
{  
    "login" : "login",  
    "sub" : "Студенческая"  
}  
### 16)Поиск песен
requestType : POST  
url : http://localhost:8080/api/user/findSongs  
request :  
{  
    "name" : "name"  
}  
### 17)Получить песню по Id
requestType : POST  
url : http://localhost:8080/api/user/getSongById  
request :  
{  
    "songId" : songId  
}  
### 18)Получить песни по AlbumId
requestType : POST  
url : http://localhost:8080/api/user/getSongByAlbumId  
request :  
{  
    "albumId" : albumId  
}  
### 19)Получить последние n альбомов
requestType : POST  
url : http://localhost:8080/api/user/getLastAlbums  
request :  
{  
    "count" : count  
}  
### 20)Получить альбом по id
requestType : POST  
url : http://localhost:8080/api/user/getAlbumById  
request :  
{  
    "albumId" : albumId  
}  
### 21)Получить альбом по song_id
requestType : POST  
url : http://localhost:8080/api/user/getAlbumBySongId  
request :  
{  
    "songId" : songId  
}  
### 22)Получить все жанры песен
requestType : GET  
url : http://localhost:8080/api/user/getAllGenres  
### 23)Получить все страны
requestType : GET  
url : http://localhost:8080/api/user/getAllCountries  
### 24)Проверить есть ли у пользователя песня в плейлисте 
requestType : POST  
url : http://localhost:8080/api/user/checkSongInPlaylist  
request :  
{  
    "userId" : userId,  
    "songId" : songId  
}  
### 25)Проверить есть ли у пользователя подписка
requestType : POST  
url : http://localhost:8080/api/user/checkSub  
request :  
{  
    "userId" : userId  
}  
### 26)Получить n альбомов по жанру 
requestType : POST  
url : http://localhost:8080/api/user/getAlbumsByGenre  
request :  
{  
    "count" : count,  
    "genre" : "genreName"  
}  
### 27)Создать плэйлист
requestType : POST  
url : http://localhost:8080/api/user/createUserAlbum  
request :  
{  
    "imageLink" : "imageLink,  
    "name" : "name",  
    "userId" : userId  
}  
### 28)Получить плэйлист
requestType : POST  
url : http://localhost:8080/api/user/getUserAlbum  
request :  
{  
    "userId" : userId  
}  
### 29)Добавить песню в плэйлист
requestType : POST  
url : http://localhost:8080/api/user/addSongToUserAlbum  
request :  
{  
    "userId" : userId,  
    "songId" : songId  
}  
### 30)Получить песни из плэйлиста 
requestType : POST  
url : http://localhost:8080/api/user/getUserAlbumSongs  
request :  
{  
    "userId" : userId  
}  
### 31)Получить n последних плэйлистов
requestType : POST  
url : http://localhost:8080/api/user/getLastUserAlbums  
request :  
{  
    "count" : count  
}  
### 32)Удалить песню из плэйлиста
requestType : POST  
url : http://localhost:8080/api/user/deleteSongFromUserAlbums  
request :  
{  
    "userId" : userId,  
    "songId" : songId  
}  
### 33)Получить артиста по UserId
requestType : POST  
url : http://localhost:8080/api/user/getArtistByUserId  
request :  
{  
    "userId" : userId   
}  
### 34)Получить все виды подписок
requestType : GET  
url : http://localhost:8080/api/user/getAllSubscriptions  
### 35)Получить подписку по Id
requestType : POST  
url : http://localhost:8080/api/user/getSubscriptionBySubId  
request :  
{  
    "subId" : subId   
}  
## Нурик сделай (не удаляй сделанное а отметь чтоб удобнее было)
### Cписок в порядке от срочных до менее срочных
- createSong не работает. всегда возвращает ошибку, но песню создаёт, но лишь с одним артистом (от которого реквест пришёл)(сделал)
- Когда получаю альбом, где несколько исполнителей (на треке не проверял но подозреваю тоже самое) - имена артистов выдаются в одном порядке. При повторном получении в другом порядке. Сделай так чтобы порядок был чётко как при создании.(сделал)
- в getArtistByUserId помимо orgId выдай ещё orgName(сделал)
- Сделай так чтобы юзер никак не смог получить доступ до непроверенных треков (т.е. только админ мог получить). Будет заебись если сделаешь так, чтобы юзер не получил доступ до альбома, если ВСЕ треки там не проверенны.(сделал)
- Подобавляй сюда запросов, которых нет а то заебался лазить по бэку(сделал)
- Админ может подтвердить песню. Сделай запрос чтобы он мог отклонить песню. Тогда она просто удалится из бдшек.(сделал)
