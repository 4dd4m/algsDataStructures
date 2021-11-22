#source directory
SRC_DIR := src/arch

# output directory
OUT_DIR := out

PKG:=arch

# suffixes
.SUFFIXES: .java

# targets that do not produce output files
.PHONY: all clean

# sources
SRCS := $(wildcard $(SRC_DIR)/*.java)
MAIN:=arch.App
TESTCLASSES:= $(PKG).gameTest $(PKG).DeckTest $(PKG).BoardTest
JVM:= java -classpath out

# classes
CLS := $(SRCS:$(SRC_DIR)/%.java=$(OUT_DIR)/%.class)

# compiler and compiler flags
JC := javac
JCFLAGS := -d $(OUT_DIR)/ -cp $(SRC_DIR)/

run: 
	$(JVM) $(MAIN)

runtest: 
	$(JVM) $(TESTCLASSES)

testdeck:
	$(JVM) $(PKG).Decktest

testgame:
	$(JVM) $(PKG).TestGame

testborad:
	$(JVM) $(PKG).TestBoard

# default target(s)
all: $(CLS)

test:
	$(JC) $(JCFLAGS) $(TESTCLASSES)

$(CLS): $(OUT_DIR)/%.class: $(SRC_DIR)/%.java
	$(JC) $(JCFLAGS) $<

#clean up any output files
clean:
	rm $(OUT_DIR)/arch/*.class
