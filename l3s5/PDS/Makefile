CC 	= gcc
CFLAGS 	= -Wall -Werror  -pedantic
CFLAGS += -g

do: makeargv.o do.o
	$(CC) -o $@ $^ $(CFLAGS)

do.o: do.c makeargv.h
	$(CC) -o $@ -c $< $(CFLAGS)

makeargv.o: makeargv.c makeargv.h
	$(CC) -o $@ -c $< $(CFLAGS)

.PHONY: clean realclean
clean:
	$(RM) -rf *.o
realclean:
	$(RM) do makeargv 

