package sml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import static sml.Registers.Register;

/**
 * This class ....
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author Swati Patel
 */
public final class Translator {

    private final String fileName; // source file of SML code
    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    public Translator(String fileName) {
        this.fileName =  fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();
    /*     switch (opcode) {
            case AddInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }

            // TODO: add code for all other types of instructions [complete]
            case MovInstruction.OP_CODE -> {
                String r = scan();
                int s = Integer.valueOf(scan());
                return new MovInstruction(label, Register.valueOf(r), s);
            }
            case SubInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new SubInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }

            case MulInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new MulInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }

            case DivInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new DivInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            case OutInstruction.OP_CODE -> {
                String r = scan();
                return new OutInstruction(label, Register.valueOf(r));
            }
            case JnzInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new JnzInstruction(label, Register.valueOf(r), s);
            }
            default -> {
                System.out.println("Unknown instruction: " + opcode);
            }
        }*/
           // TODO: Then, replace the switch by using the Reflection API [complete]
          String cap="";
           try {
            cap = opcode.substring(0, 1).toUpperCase() + opcode.substring(1);
            Class<?> instructionClass = Class.forName("sml.instruction." + cap + "Instruction");
            String r = scan();
            String s = null;
            Constructor<?> constructor =null;
            if(cap.contentEquals("Mov")){
                constructor = instructionClass.getConstructor(String.class, RegisterName.class, int.class);
                s = scan();
                return (Instruction) constructor.newInstance(label, Register.valueOf(r), Integer.valueOf(s));
            }
            else if(cap.contentEquals("Add")||cap.contentEquals("Sub")||cap.contentEquals("Mul")||cap.contentEquals("Div")){
                constructor = instructionClass.getConstructor(String.class, RegisterName.class, RegisterName.class);
                s = scan();
                return (Instruction) constructor.newInstance(label, Register.valueOf(r), Register.valueOf(s));
            }
            else if(cap.contentEquals("Jnz")){
                constructor = instructionClass.getConstructor(String.class, RegisterName.class, String.class);
                s = scan();
                return (Instruction) constructor.newInstance(label, Register.valueOf(r), s);}
            else if(cap.contentEquals("Out")){
                constructor = instructionClass.getConstructor(String.class, RegisterName.class);
                return (Instruction) constructor.newInstance(label, Register.valueOf(r));
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            System.out.println("Unknown instruction: " + opcode);
        }
            // TODO: Next, use dependency injection to allow this machine class
            //       to work with different sets of opcodes (different CPUs)

     return null;
    }


    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}
