package main.java.ir.loghme.controller.command;

@SuppressWarnings("UnusedReturnValue")
public interface Command {
    <I, O> O execute(I input) throws Exception;
}