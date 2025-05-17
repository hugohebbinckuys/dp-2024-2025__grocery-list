package com.fges;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CommandTests {
    private CommandeLine commandeLine;

    @BeforeEach
    public void setUp() {
        commandeLine = new CommandeLine();
    }

    @Test
    public void testParseCommand_withMissingCommand_shouldReturnError() {
        String[] args = {"-s", "list.json", "-t", "json"};

        int result = commandeLine.parseCommand(args);

        assertEquals(1, result);
    }

    @Test
    public void testParseCommand_infoCommand_withoutSourceFile_shouldSucceed() {
        String[] args = {"info"};

        int result = commandeLine.parseCommand(args);

        assertEquals(0, result);
        assertEquals("info", commandeLine.getCommande());
        assertEquals("json", commandeLine.getFileType()); // valeur par défaut
        assertNull(commandeLine.getSourceFile());
    }

    @Test
    public void testParseCommand_addCommand_withoutSourceFile_shouldFail() {
        String[] args = {"add", "Pain", "1", "-t", "json"};

        int result = commandeLine.parseCommand(args);

        assertEquals(1, result);
    }

    @Test
    public void testParseCommand_addCommand_withAllArgs_shouldSucceed() {
        String[] args = {"add", "Pain", "1", "-s", "liste.txt", "-t", "csv"};

        int result = commandeLine.parseCommand(args);

        assertEquals(0, result);
        assertEquals("add", commandeLine.getCommande());
        assertEquals("csv", commandeLine.getFileType());
        assertEquals("liste.txt", commandeLine.getSourceFile());
    }
}
