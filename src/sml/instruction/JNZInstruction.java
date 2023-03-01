package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

public class JNZInstruction extends Instruction {
    private final RegisterName loop;
    private final String inst;

    public static final String OP_CODE = "jnz";
    public JNZInstruction(String label, RegisterName loop, String inst) {
        super(label, OP_CODE);
        this.loop = loop;
        this.inst = inst;
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
        if(m.getRegisters().get(loop) != 0){
            return m.getLabels().getAddress(inst);
        }
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + loop + " " + inst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (o instanceof JNZInstruction other) {
            return Objects.equals(this.label, other.label)
                    && Objects.equals(loop, other.loop)
                    && Objects.equals(inst, other.inst);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, loop, inst);
    }
}
