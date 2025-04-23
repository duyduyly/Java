package clazz.beyond_clazz.enums;

import lombok.Getter;

@Getter
public enum Compass {
    NORTH("Move up"), SOUTH("Move Down"), EAST("Move Right"), WEST("Move Left");
    private final String instruction;
    private Compass(String instruction) {
        this.instruction = instruction;
    }

    public void printInstruction() {
        System.out.println(instruction);
    }

}
