package com.jotrorox.jcreate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testNoArguments() {
        App.main(new String[]{});
        assertEquals(
            "\033[1;34m" +
            "     --- JCreate ---     \n" +
            "                         \n" +
            "     < version 1.0 >     \n" +
            "                         \n" +
            "                         \n" +
            " A simple tool made for  \n" +
            " creating Maven projects \n" +
            "                         \n" +
            " If you need help, please\n" +
            " use <program> help      \n" +
            "                         \n" +
            "                         \n" +
            "      by Jotrorox        \n" +
            "\033[0m\n",
            outContent.toString()
        );
    }

    @Test
    public void testHelpArgument() {
        App.main(new String[]{"help"});
        assertEquals(
            "\033[1;32m" +
            " Help message for JCreate v1.0\n" +
            "                              \n" +
            " Usage:                       \n" +
            " jcreate <groupId> <artifactId> <packaging>\n" +
            "                            \n" +
            " Example:                   \n" +
            " jcreate com.jotrorox my-app jar\n" +
            "\033[0m\n",
            outContent.toString()
        );
    }

    @Test
    public void testVersionArgument() {
        App.main(new String[]{"version"});
        assertEquals("JCreate v1.0\n", outContent.toString());
    }

    @Test
    public void testInvalidArgument() {
        App.main(new String[]{"invalid"});
        assertEquals(
            "\033[1;31m" +
            "Error: Unknown command. Use 'help' for usage instructions." +
            "\033[0m\n",
            outContent.toString()
        );
    }

    @Test
    public void testInvalidNumberOfArguments() {
        App.main(new String[]{"arg1", "arg2"});
        assertEquals(
            "\033[1;31m" +
            "Error: Invalid arguments. Use 'help' for usage instructions." +
            "\033[0m\n",
            outContent.toString()
        );
    }

    @Test
    public void testBasicAppCreation() {
        App.main(new String[]{"com.example", "my-app", "jar"});
        assertEquals(
            "\033[1;32m" +
            "Project created successfully!" +
            "\033[0m\n",
            outContent.toString()
        );
    }
}