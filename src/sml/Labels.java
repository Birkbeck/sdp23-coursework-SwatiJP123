package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
// TODO: write a JavaDoc for the class [complete]

/**
 *
 * @author Swati Patel
 * The Labels class represents a collection of labels and their associated addresses in a program.
 * It provides methods to add labels, get the address associated with a label, and reset the labels.
 * It also implements the equals and hashCode methods for use in the Machine class.
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) {
		Objects.requireNonNull(label);
		// TODO: Add a check that there are no label duplicates. [Complete]
		if(labels.containsKey(label)){
			throw new RuntimeException("Duplicate label found : " +label);
		}
		labels.put(label, address);
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {
		// TODO: Where can NullPointerException be thrown here? [Complete]
		//       (Write an explanation.)
		/*
		 * The NullPointerException can be thrown in the getAddress method if the label argument is null. 
		 * This is because the labels map is a HashMap and calling the get method on a HashMap with a null key will result in a NullPointerException.
		 * To handle this scenario, you can add a null check for the label argument at the beginning of the getAddress method and throw an appropriate exception or return a default value if the label is null.
		*/
		//Add code to deal with non-existent labels.
		if (label == null) {
			throw new IllegalArgumentException("Label cannot be null");
			// or return a default value if appropriate
		}
		Integer address = labels.get(label);
		if (address == null) {
			throw new IllegalArgumentException("Label not found: " + label);
			// or return a default value if appropriate
		}
		return address;
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		// TODO: Implement the method using the Stream API (see also class Registers). [Complete]
		return labels.entrySet().stream()
				.map(e -> e.getKey() + " -> " + e.getValue())
				.collect(Collectors.joining(", ", "[", "]")) ;
	}

	// TODO: Implement equals and hashCode (needed in class Machine). [Complete]
	@Override
	public boolean equals(Object o) {
		if (o instanceof Labels label) {
			return Objects.equals(this.labels, label.labels);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(labels);
	}

	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}
}
