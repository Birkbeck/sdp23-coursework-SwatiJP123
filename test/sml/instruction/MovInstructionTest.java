package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.EAX;

public class MovInstructionTest {
    private Machine mach;

    @BeforeEach
    void setUp() {
        mach = new Machine(new Registers());
    }

    @AfterEach
    void tearDown() {
        mach = null;
    }

    @Test
    void executeValid() {
        int testValue = 12;
        Instruction instruction = new MovInstruction(null, EAX, testValue);
        instruction.execute(mach);
        Assertions.assertEquals(testValue, mach.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        int testValue = -35;
        Instruction instruction = new MovInstruction(null, EAX, testValue);
        instruction.execute(mach);
        Assertions.assertEquals(testValue, mach.getRegisters().get(EAX));
    }
}
