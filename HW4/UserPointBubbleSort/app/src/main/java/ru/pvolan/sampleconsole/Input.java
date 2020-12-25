package ru.pvolan.sampleconsole;

public interface Input {
    int readInt();
    long readLong();
    float readFloat();
    double readDouble();
    String readLine();

    class EndOfInputException extends RuntimeException {
        public EndOfInputException() {
            super("UNEXPECTED END OF INPUT");
        }
    }
}
