package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.EAX;

public class JNZInstructionTest {
    private Machine mach;
    private Registers reg;

    @BeforeEach
    void setUp() {
        mach = new Machine(new Registers());
        reg = mach.getRegisters();
        //...
    }

    @AfterEach
    void tearDown() {
        mach = null;
        reg = null;
    }

    @Test
    void executeValid() {
        reg.set(EAX, 10);
        mach.getLabels().addLabel("L",20);
        Instruction instruction = new JNZInstruction(null,EAX,"I1");
        Assertions.assertEquals(20, instruction.execute(mach));
    }

    @Test
    void JNZexecTestzero() {
        reg.set(EAX, 0);
        mach.getLabels().addLabel("L",0);
        Instruction instruction = new JNZInstruction(null, EAX, "I1");
        Assertions.assertEquals(-1, instruction.execute(mach));
    }
}
