package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class [complete]
/**
 * @author Swati Patel
 * MulInstruction is a class that represents a multiply instruction in a machine language program. It extends the Instruction class and overrides its abstract methods.
 * This class stores the two register operands: result and source.
 * When executed, the instruction multiplies the values stored in the result and source registers and stores the result in the result register.
 */

public class MulInstruction extends Instruction {
    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "mul";

    public MulInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        int val1 = m.getRegisters().get(result);
        int val2 = m.getRegisters().get(source);
        m.getRegisters().set(result, val1 * val2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (o instanceof MulInstruction other) {
            return Objects.equals(this.label, other.label)
                    && Objects.equals(this.result, other.result)
                    && Objects.equals(this.source, other.source);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, result, source);
    }
}
