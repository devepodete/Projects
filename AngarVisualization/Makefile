all:
	g++ -std=c++17 -c visualize.cpp calendar.cpp
	g++ visualize.o calendar.o -o visualize-app -lsfml-graphics -lsfml-system -lsfml-window -lsfml-audio
	rm visualize.o calendar.o
