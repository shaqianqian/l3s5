schema([courseno,secno,offeringdept,credithours,courselevel,instructorssn,semester,year, dayshours,roomno,noofstudents]).
fds([ [[courseno],[offeringdept,credithours,courselevel]],
[[courseno,secno,semester,year],[dayshours,roomno,noofstudents,instructorssn]],
[[roomno,dayshours,semester,year],[instructorssn,courseno,secno]] ]).
decomp([propertyis,area,lotno],[area,countyname],[area,price],[countyname,taxrate]).
