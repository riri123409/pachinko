JAVAC = javac
JAVA = java
SRC_DIR = src
BIN_DIR = bin
SRC = $(wildcard $(SRC_DIR)/*.java)
TARGET = main

all:
	@if not exist $(BIN_DIR) mkdir $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) $(SRC)

run: all
	$(JAVA) -cp $(BIN_DIR) $(TARGET)

clean:
	@if exist $(BIN_DIR) del /Q $(BIN_DIR)\*.class



# make コンパイル
# make run 実行
# make clean クリーン
