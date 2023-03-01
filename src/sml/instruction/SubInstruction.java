package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;
// TODO: write a JavaDoc for the class [complete]
/**
 * @author Swati Patel
 * The SubInstruction class represents a subtraction instruction in a machine's assembly language.
 * This instruction subtracts the value stored in the source register from the value stored in the result register and stores the result in the result register.
 * 'result' and 'source' are the names of the registers involved in the subtraction.
 * This class extends the Instruction class and implements the execute() method to carry out the subtraction operation.
 */
public class SubInstruction extends Instruction {

    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "sub";
    public SubInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    @Override
    public int execute(Machine machine) {
        int val1 = machine.getRegisters().get(result);
        int val2 = machine.getRegisters().get(source);
        machine.getRegisters().set(result, val1 - val2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (o instanceof SubInstruction other) {
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

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }
}
