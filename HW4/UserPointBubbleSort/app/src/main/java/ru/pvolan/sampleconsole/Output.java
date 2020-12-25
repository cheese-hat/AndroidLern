package ru.pvolan.sampleconsole;

public interface Output {
    void print(String msg);
    void print(int i);
    void print(long i);
    void print(float i);
    void print(double i);
    void print(boolean i);
    void print(char c);

    void println(String msg);
    void println(int i);
    void println(long i);
    void println(float i);
    void println(double i);
    void println(boolean i);
    void println(char c);

    void printf(String format, Object... params);
}
