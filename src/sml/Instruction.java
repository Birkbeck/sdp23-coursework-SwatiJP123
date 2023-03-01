package sml;

// TODO: write a JavaDoc for the class [complete]
/**
 * @author Swati Patel
 * The Instruction class represents a single instruction in a machine language program. It is an abstract class that provides the basic structure and functionality for any type of instruction. The class includes a label and an opcode, which must be provided when an instruction is created.
 * Instructions can be executed on a Machine object using the execute() method, which is an abstract method that must be implemented by any concrete subclass of Instruction. The execute() method takes a Machine object as its argument and returns an integer value that represents the new program counter.
 * The class also includes methods for getting the label and opcode of an instruction, as well as a method for getting a label string that includes the label and a colon, if one is present.
 * The class is declared as abstract, and includes abstract methods for toString(), equals(), and hashCode(). Any concrete subclass of Instruction must provide an implementation for these methods.
 * This class is intended to be extended by concrete classes that represent specific types of instructions, such as AddInstruction, JumpInstruction, etc.
 */

public abstract class Instruction {
	protected final String label;
	protected final String opcode;

	/**
	 * Constructor: an instruction with a label and an opcode
	 * (opcode must be an operation of the language)
	 *
	 * @param label optional label (can be null)
	 * @param opcode operation name
	 */
	public Instruction(String label, String opcode) {
		this.label = label;
		this.opcode = opcode;
	}

	public String getLabel() {
		return label;
	}

	public String getOpcode() {
		return opcode;
	}

	public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;

	/**
	 * Executes the instruction in the given machine.
	 *
	 * @param machine the machine the instruction runs on
	 * @return the new program counter (for jump instructions)
	 *          or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 *          the instruction with the next address is to be executed
	 */

	public abstract int execute(Machine machine);

	protected String getLabelString() {
		return (getLabel() == null) ? "" : getLabel() + ": ";
	}

	// TODO: What does abstract in the declaration below mean? [Complete]
	/* In the declaration provided, the keyword "abstract" is used to indicate that the method toString() is an abstract method.
	An abstract method is a method that is declared but does not provide an implementation in the current class. 
	Instead, the implementation is provided by a subclass. Any class that contains one or more abstract methods must also be declared as an abstract class.
	In this specific case, the toString() method is being declared as abstract in a class that is implementing an interface or extending an abstract class that requires the implementation of this method. 
	Therefore, any concrete class that implements this interface or extends this abstract class will need to provide its own implementation for the toString() method.
*/
// declare equals and hashCode as abstract
	@Override
	public abstract String toString();
	// TODO: Make sure that subclasses also implement equals and hashCode (needed in class Machine). [Complete]
	@Override
	public abstract boolean equals(Object o);
    @Override
	public abstract int hashCode();
}
