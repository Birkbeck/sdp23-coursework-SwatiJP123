package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;
// TODO: write a JavaDoc for the class [complete]
/**
 * @author Swati Patel
 * This class represents a machine instruction that moves an integer value into a register.
 * The instruction stores the given value in the specified register of the machine.
 */
public class MovInstruction extends Instruction {

    private final RegisterName result;

    private final int value;
    public static final String OP_CODE = "mov";


    public MovInstruction(String label, RegisterName result, int value) {
        super(label, OP_CODE);
        this.result = result;
        this.value = value;
    }


    /**
     * Executes the instruction in the given machine.
     *
     * @param m the machine the instruction runs on
     * @return the new program counter (for jump instructions)
     * or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
     * the instruction with the next address is to be executed
     */
    @Override
    public int execute(Machine m) {
        int val1 = m.getRegisters().get(result);
        m.getRegisters().set(result, val1);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + value;
    }

    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (o instanceof MovInstruction other) {
            return Objects.equals(this.label, other.label)
                    && Objects.equals(result, other.result)
                    && Objects.equals(value, other.value);
        }
        return false;
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(label, result, value);
    }
}
