SRC=src/jadelex/
EXLEX=${SRC}jade.lex
YYLEX=${SRC}Yylex.java
TEST=${SRC}Test.java
BIN=bin/jadelex/
TESTCLASS=${BIN}Test.class

${YYLEX}:${EXLEX}
	java -jar jflex-1.6.1.jar $<
${TESTCLASS}:${TEST} ${YYLEX}
	javac -cp src -d bin $<
.PHONY: test clean
test: ${TESTCLASS} test.txt
	java -cp bin jadelex.Test test.txt
clean:
	rm ${BIN}/* && rm ${YYLEX}
