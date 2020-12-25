package ru.pvolan.sampleconsole;

import java.util.LinkedList;
import java.util.List;

public class StringInput implements Input {

    private LinkedList<Character> inputData;

    public StringInput(String inputString) {
        char[] inputChars = inputString.toCharArray();
        inputData = new LinkedList<Character>();
        for (char inputChar : inputChars) {
            inputData.add(inputChar);
        }
    }


    private char peekChar() throws EndOfInputException {
        if(inputData.size() == 0) throw new EndOfInputException();
        return inputData.pop();
    }


    private List<Character> peekWord() {
        //skip spaces
        char fistChar;
        do {
            fistChar = peekChar();
        } while (Character.isWhitespace(fistChar));

        //Put char
        LinkedList<Character> word = new LinkedList<>();
        word.add(fistChar);

        //Put other chars
        while (true){
            char c = peekChar();
            if(Character.isWhitespace(c)) break;
            word.add(c);
        }

        return word;
    }



    private List<Character> peekLine() {

        LinkedList<Character> line = new LinkedList<>();
        //Put other chars
        while (true){
            char c = peekChar();
            if(c == '\n') break;
            line.add(c);
        }

        return line;
    }


    private String listToString(List<Character> list){
        StringBuilder sb = new StringBuilder();
        for (Character character : list) {
            sb.append(character);
        }
        return sb.toString();
    }


    @Override
    public int readInt() {
        String s = listToString(peekWord());
        return Integer.parseInt(s);
    }

    @Override
    public long readLong() {
        String s = listToString(peekWord());
        return Long.parseLong(s);
    }

    @Override
    public float readFloat() {
        String s = listToString(peekWord());
        return Float.parseFloat(s);
    }

    @Override
    public double readDouble() {
        String s = listToString(peekWord());
        return Double.parseDouble(s);
    }

    @Override
    public String readLine() {
        return listToString(peekLine());
    }
}
