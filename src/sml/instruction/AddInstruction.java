package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

// TODO: write a JavaDoc for the class [complete]
/**
 * @author Swati Patel
    The AddInstruction class represents an add instruction in a machine language program. It extends the Instruction class and provides a concrete implementation for the execute() method, which adds the values of two registers and stores the result in a specified register.
    The class includes two instance variables, result and source, which represent the names of the registers to be used as operands for the add operation.
    The class provides a constructor that takes a label, a result register name, and a source register name as arguments. The constructor calls the superclass constructor with the opcode "add".
    The class overrides the toString() method to provide a string representation of the instruction in the format "label: opcode result source", where label is optional and source and result are the register names.
    The class also overrides the equals() and hashCode() methods inherited from the Instruction class, but these methods currently do not provide any meaningful implementation.
    This class is intended to be used as a concrete subclass of Instruction for representing add instructions in a machine language program.
    */


public class AddInstruction extends Instruction {
	private final RegisterName result;
	private final RegisterName source;

	public static final String OP_CODE = "add";

	public AddInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		int value2 = m.getRegisters().get(source);
		m.getRegisters().set(result, value1 + value2);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + source;
	}
	@Override
	public int hashCode() {
		return 0;
	}
	@Override
	public boolean equals(Object o) {
		return true;
	}
}
