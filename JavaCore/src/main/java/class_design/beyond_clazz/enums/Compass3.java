package class_design.beyond_clazz.enums;

import lombok.Getter;

//Enums can Element for abstract method
@Getter
public enum Compass3 {
    NORTH {
        public String getDirection() {
            return "N";
        }
    },
    SOUTH {
        public String getDirection() {
            return "S";
        }
    },
    EAST {
        public String getDirection() {
            return "E";
        }
    }, WEST {
        public String getDirection() {
            return "W";
        }
    };

    public abstract String getDirection();

}
