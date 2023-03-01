package sml;

import java.util.*;
import java.util.stream.Collectors;

// TODO: write a JavaDoc for the class [complete]

/**
 *
 * @author Swati Patel
 * The Registers class represents the registers used in an x86-64 computer architecture. It contains eight registers: EAX, EBX, ECX, EDX, ESP, EBP, ESI, and EDI.
 * The class provides methods to set and get the value stored in a register, as well as to clear all the registers to 0.
 * The class also provides an implementation for the equals, hashCode, and toString methods.
 */
public final class Registers {
    private final Map<Register, Integer> registers = new HashMap<>();

    public enum Register implements RegisterName {
        EAX, EBX, ECX, EDX, ESP, EBP, ESI, EDI;
    }

    public Registers() {
        clear(); // the class is final
    }

    public void clear() {
        for (Register register : Register.values())
            registers.put(register, 0);
    }

    /**
     * Sets the given register to the value.
     *
     * @param register register name
     * @param value new value
     */
    public void set(RegisterName register, int value) {
        registers.put((Register)register, value);
    }

    /**
     * Returns the value stored in the register.
     *
     * @param register register name
     * @return value
     */
    public int get(RegisterName register) {
        return registers.get((Register)register);
    }

    // TODO: use pattern matching for instanceof [complete]
    // https://docs.oracle.com/en/java/javase/14/language/pattern-matching-instanceof-operator.html
    @Override
    public boolean equals(Object o) {
        if (o instanceof Registers other) {
            return registers.equals(other.registers);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return registers.hashCode();
    }

    @Override
    public String toString() {
        return registers.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining(", ", "[", "]")) ;
    }
}
