package ru.pvolan.sampleconsole;

public class StringOutput implements Output {

    private StringBuilder sb = new StringBuilder();

    @Override
    public void print(String msg) {
        sb.append(msg);
    }

    @Override
    public void print(int i) {
        sb.append(i);
    }

    @Override
    public void print(long i) {
        sb.append(i);
    }

    @Override
    public void print(float i) {
        sb.append(i);
    }

    @Override
    public void print(double i) {
        sb.append(i);
    }

    @Override
    public void print(boolean i) {
        sb.append(i);
    }

    @Override
    public void print(char c) {
        sb.append(c);
    }

    @Override
    public void println(String msg) {
        sb.append(msg).append('\n');
    }

    @Override
    public void println(int i) {
        sb.append(i).append('\n');
    }

    @Override
    public void println(long i) {
        sb.append(i).append('\n');
    }

    @Override
    public void println(float i) {
        sb.append(i).append('\n');
    }

    @Override
    public void println(double i) {
        sb.append(i).append('\n');
    }

    @Override
    public void println(boolean i) {
        sb.append(i).append('\n');
    }

    @Override
    public void println(char c) {
        sb.append(c).append('\n');
    }

    @Override
    public void printf(String format, Object... params) {
        sb.append(String.format(format, params));
    }

    public String getOutput(){
        return sb.toString() + "END";
    }
}
