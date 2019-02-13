CC 	= gcc
CFLAGS 	= -Wall -Werror -ansi -pedantic
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

prog: prlimit.o  maccess.o
	gcc -o prlimit prlimit.o 
	gcc -o maccess maccess.o 

.PHONY: clean 
clean:
	$(RM) -rf *.o 




