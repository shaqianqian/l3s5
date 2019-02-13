.PHONY:clean
CC=gcc
CFLAGS= -Wall -Werror -ansi -pedantic
CFLAGS+= -D_XOPEN_SOURCE=500
CFLAGS+= -g

prog: 
	$(CC) test1.c -o test1
	$(CC) test2.c -o test2
	$(CC) test3.c -o test3
	$(CC) do.c makeargv.c $(CFLAGS) -o do
		
clean:
	$(RM) -rf *.o *~ *.dSYM
realclean:
	$(RM)  do test1 test2 test3 
