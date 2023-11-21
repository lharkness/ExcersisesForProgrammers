package com.leeharkness;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ObjectOrientedGreeter {

    private final Inputter inputter;
    private final Outputter outputter;

    public ObjectOrientedGreeter(Inputter inputter, Outputter outputter) {
        this.inputter = inputter;
        this.outputter = outputter;
    }

    private void run() throws IOException {
        outputter.output("What is your name? ");
        String name = inputter.input();
        outputter.output("\nHi " + name + ", nice to meet you\n");
    }
    public static void main(String[] args) throws IOException {
        InputOutputFactory inputOutputFactory = InputOutputFactory.create(InputOutputFactory.STANDARD);
        Inputter inputter = inputOutputFactory.getInputter();
        Outputter outputter = inputOutputFactory.getOutputter();

        ObjectOrientedGreeter objectOrientedGreeter = new ObjectOrientedGreeter(inputter, outputter);
        objectOrientedGreeter.run();
    }

    private interface Inputter {
        String input() throws IOException;
    }

    private interface Outputter {
        void output(String s);
    }

    static class StandardInputter implements Inputter {

        @Override
        public String input() throws IOException {
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        }
    }

    static class StandardOutputter implements Outputter {

        @Override
        public void output(String s) {
            System.out.print(s);
        }
    }

    private static interface InputOutputFactory {
        public static final String STANDARD = "STANDARD";

        public static InputOutputFactory create(String hint) {
            if (hint.equals(InputOutputFactory.STANDARD)) {
                return new StandardInputOutputFactory();
            }
            else {
                throw new IllegalArgumentException("Unknown InputOutputFactory Type: " + hint);
            }
        }

        Inputter getInputter();

        Outputter getOutputter();

        class StandardInputOutputFactory implements InputOutputFactory {
            @Override
            public Inputter getInputter() {
                return new StandardInputter();
            }

            @Override
            public Outputter getOutputter() {
                return new StandardOutputter();
            }
        }
    }
}
