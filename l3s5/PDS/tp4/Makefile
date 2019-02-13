CC 	= gcc
CFLAGS 	= -Wall -Werror -ansi -pedantic
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

prog: forkfork.o 
	gcc -o forkfork forkfork.o 

.PHONY: clean 
clean:
	$(RM) -rf *.o 


realclean:
	$(RM) forkfork 

