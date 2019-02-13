CC 	= gcc
CFLAGS 	= -Wall -Werror -ansi -pedantic
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

prog: mdu.o 
	gcc -o mdu mdu.o 

.PHONY: clean 
clean:
	$(RM) -rf *.o 


realclean:
	$(RM) mdu 

