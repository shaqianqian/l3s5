schema([ename,ssn,bdate,address,dnumber,dname,dmgrssn]).
fds([ [[ssn],[ename, bdate, address,dnumber]],[[dnumber],[dname, dmgrssn]]]).
