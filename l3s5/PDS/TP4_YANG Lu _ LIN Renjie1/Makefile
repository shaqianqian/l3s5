CC 	= gcc
CFLAGS 	= -Wall -Werror -ansi -pedantic
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

prog: race.o  forkfork.o
	gcc -o race race.o 

	gcc -o forkfork forkfork.o 

.PHONY: clean 
clean:
	$(RM) -rf *.o 

realclean:
	$(RM) race forkfork 



