package main.java.ir.loghme.model.command;

@SuppressWarnings("UnusedReturnValue")
public interface Command {
    <I, O> O execute(I input) throws Exception;
}