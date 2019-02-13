CC 	= gcc
CFLAGS 	= -Wall -Werror -ansi -pedantic
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

prog: tail.o 
	gcc -o tail tail.o 

.PHONY: clean 
clean:
	$(RM) -rf *.o 


realclean:
	$(RM) tail 

