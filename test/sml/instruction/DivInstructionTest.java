package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

class DivInstructionTest {
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
    reg.set(EAX, 2);
    reg.set(EBX, 5);
    Instruction instruction = new DivInstruction(null, EAX, EBX);
    instruction.execute(mach);
    Assertions.assertEquals(2, mach.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    reg.set(EAX, -5);
    reg.set(EBX, 4);
    Instruction instruction = new DivInstruction(null, EAX, EBX);
    instruction.execute(mach);
    Assertions.assertEquals(-5, mach.getRegisters().get(EAX));
  }
}