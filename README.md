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
### Регистрация пользователя
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
### Авторизация пользователя
requestType : POST   
url : http://localhost:8080/api/auth/signin  
request :  
{  
    "login" : "Login",  
    "password" : "password"  
}  
### Добавление подписки пользователю
requestType : POST   
url : http://localhost:8080/api/user/addSub  
request :  
{  
    "login" : "nik23",  
    "sub" : "Студенческая"  
}  
### Добавление артиста в базу(может только Admin)
requestType : POST    
url : http://localhost:8080/api/artist/addArtist  
header : Authorization Bearer + token 
request :  
{  
    "description" : "Описание артиста!",  
    "login" : "login",  
    "name" : "nickname"  
}
### Получить все организации(Получить список организация может Artist и Admin)
requestType : GET  
url : http://localhost:8080/api/artist/getOrganisation  
header : Authorization Bearer + token  
### Добавить организацию(Создать организацию может только Admin)
requestType : POST  
url : http://localhost:8080/api/admin/addOrganisation  
header : Authorization Bearer + token  
request :  
{  
    "description" : "описание организации",  
    "name" : "orgName",  
    "countryName" : "Россия"  
}  
### Добавить альбом(Создать альбом может только Artist)
requestType : POST  
url : http://localhost:8080/api/artist/addAlbum  
header : Authorization Bearer + token  
request :  
{  
    "userId" : Id,  
    "name" : "name",  
    "description" : "description",  
    "link" : "link"  
}  
### Вступить в организацию(Вступить может только Artist)
requestType : POST  
url : http://localhost:8080/api/artist/setOragnisationToArtist  
header : Authorization Bearer + token  
request :  
{  
    "orgId" : orgId,  
    "userId" : userId     
}  
### Выйти из организации(Выйти может только Artist)
requestType : POST  
url : http://localhost:8080/api/artist/quitFromOrganisation  
header : Authorization Bearer + token  
request :  
{   
    "userId" : userId     
}  
### Добавить песню(Добавить может только Artist)
requestType : POST  
url : http://localhost:8080/api/artist/addSong  
header : Authorization Bearer + token  
request :  
{   
    "userId" : userId,  
    "name" : "name",  
    "duration" : duration,  
    "albumName" : "albumName",  
    "genre" : "genre",  
    "link" : "link"  
}  
### Получить песни на провекру(Получить может только Admin)
requestType : GET  
url : http://localhost:8080/api/admin/getSongsForAdmin  
header : Authorization Bearer + token  
### Проверить песню(Проверить может только Admin)
requestType : POST  
url : http://localhost:8080/api/admin/checkSong  
header : Authorization Bearer + token  
request :  
{  
    "userId" : userId,  
    "songId" : songId  
}  
~~### Добавить песню в плэйлист
requestType : POST  
url : http://localhost:8080/api/user/addSongToPlaylist  
request :  
{  
    "userId" : userId,  
    "songId" : songId  
}~~

~~### Получить плэйлист
requestType : POST  
url : http://localhost:8080/api/user/getPlayList  
request :  
{  
    "userId" : userId  
}~~

### Поиск песен
requestType : POST  
url : http://localhost:8080/api/user/findSongs  
request :  
{  
    "name" : "name"  
}  
### Получить песню
requestType : POST  
url : http://localhost:8080/api/user/getSongById  
request :  
{  
    "songId" : songId  
}  
### Получить песни по album_id
requestType : POST  
url : http://localhost:8080/api/user/getSongByAlbumId  
request :  
{  
    "albumId" : albumId  
} 
### Получить последние n альбомов
requestType : POST  
url : http://localhost:8080/api/user/getLastAlbums  
request :  
{  
    "count" : count  
}  
### Получить альбом по song_id
requestType : POST  
url : http://localhost:8080/api/user/getAlbumBySongId  
request :  
{  
    "songId" : songId  
}  
### Проверить токен
requestType : POST  
url : http://localhost:8080/api/auth/checkToken  
request :  
{  
    "token" : "token"  
}  
### Получить все жанры песен
requestType : GET  
url : http://localhost:8080/api/user/getAllGenres  
### Получить все страны
requestType : GET  
url : http://localhost:8080/api/user/getAllCountries  
### Проверить есть ли у пользователя песня в плейлисте 
requestType : POST  
url : http://localhost:8080/api/user/checkSongInPlaylist  
request :  
{  
    "userId" : userId,  
    "songId" : songId  
}  
### Проверить есть ли у пользователя подписка
requestType : POST  
url : http://localhost:8080/api/user/checkSub  
request :  
{  
    "userId" : userId  
}  
### Получить n альбомов по жанру 
requestType : POST  
url : http://localhost:8080/api/user/getAlbumsByGenre  
request :  
{  
    "count" : count,  
    "genre" : "genreName"  
}  
### Создать пользовательский альбом
requestType : POST  
url : http://localhost:8080/api/user/createUserAlbum  
request :  
{  
    "imageLink" : "imageLink,  
    "name" : "name",  
    "userId" : userId  
}  
### Получить пользовательский альбом
requestType : POST  
url : http://localhost:8080/api/user/getUserAlbum  
request :  
{  
    "userId" : userId  
}  
### Добавить песню в пользовательский альбом
requestType : POST  
url : http://localhost:8080/api/user/addSongToUserAlbum  
request :  
{  
    "userId" : userId,  
    "songId" : songId  
}  
### Получить песни из пользовательско альбома 
requestType : POST  
url : http://localhost:8080/api/user/getUserAlbumSongs  
request :  
{  
    "userId" : userId  
}  
### Получить n последних пользовательских альбома 
requestType : POST  
url : http://localhost:8080/api/user/getLastUserAlbums  
request :  
{  
    "count" : count  
}  
### Удалить песню из пользовательского альбома
requestType : POST  
url : http://localhost:8080/api/user/deleteSongFromUserAlbums  
request :  
{  
    "userId" : userId,  
    "songId" : songId  
}  
## Нурик сделай (не удаляй сделанное а отметь чтоб удобнее было)
### Cписок в порядке от срочных до менее срочных
- ~~я кидаю сонг айди и юзер айди а ты в ответ есть ли у этого пользователя в плейлисте этот трек~~(сделал)
- ~~Получить n последних плейлистов (тоже самое что и с альбомами)~~(сделал)
- ~~я кидаю n и жанр а ты кидаешь н последних альбомов в этом жанре~~(сделал)
- ~~Проверка подписки~~(сделал)

- ~~Удаление трека из плейлиста~~(сделал)
- ~~Всё таки сделай проверку если можешь когда чел создает плейлист чтобы была проверка что у него его ещё нет. На фронте это проверяется но на будущее так лучше ж~~(сделал)

- Получить имя артиста и его artist_id по user_id.(сделал)

- При добавлении альбома я тебе кидаю имена артистов, которым принадлежит этот альбом. Возвращай id альбома:(сделал)
    request :  
        {  
            "artistsNames" : artistsNames(массив),  
            "name" : "name",  
            "description" : "description",  
            "link" : "link"  
        }  
- При добавлении трека я кидаю имена артистов, которые на фите (по дефолту на этом треке те, чей альбом. featuresIds может быть и [] если на треке нет фита). Возвращай айди трека:(сделал)  
    request :  
        {   
            "featuresNames" : featuresNames(массив),  
            "name" : "name",  
            "duration" : duration,  
            "albumId" : "albumId",  
            "genre" : "genre",  
            "link" : "link"  
        }  
- Ну и при getAlbumById и getTrackById инфа чтоб новая была с artistsNames и featuresNames(сделал)
- Получить все альбомы(album_id) по artist_id ну либо по user_id, но тогда с проверкой что он артист.
- При добавлении трека в юхер плейлист проверять что его у него нет(сделал)
- Убрать повторное создание плейлиста юзера
- Убери с бэка изначальную дата базу с плейлистами которую мы не юзаем(сделал)
