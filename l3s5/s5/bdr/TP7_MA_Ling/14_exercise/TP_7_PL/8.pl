schema([propertyid,countyname,lotno,area,price,taxrate]).
fds([ [[propertyid],[countyname,lotno,area,price,taxrate]],[[countyname,lotno],[propertyid,area,price,taxrate]],
[[countyname],[taxrate]],
[[area],[price]] ]).
decomp([propertyis,area,lotno],[area,countyname],[area,price],[countyname,taxrate]).

