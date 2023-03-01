package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

public class JNZInstruction extends Instruction {
    private final RegisterName condition;
    private final String nextIns;

    public static final String OP_CODE = "jnz";
    public JNZInstruction(String label, RegisterName condition, String nextIns) {
        super(label, OP_CODE);
        this.condition = condition;
        this.nextIns = nextIns;
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
        if(m.getRegisters().get(condition) != 0){
            return m.getLabels().getAddress(nextIns);
        }
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + condition + " " + nextIns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (o instanceof JNZInstruction other) {
            return Objects.equals(this.label, other.label)
                    && Objects.equals(condition, other.condition)
                    && Objects.equals(nextIns, other.nextIns);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, condition, nextIns);
    }
}
