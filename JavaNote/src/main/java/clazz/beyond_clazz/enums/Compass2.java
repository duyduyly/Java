package clazz.beyond_clazz.enums;

import lombok.Getter;

@Getter
public enum Compass2 {
    NORTH{
        public String getDirection(){return "N";}
    },
    SOUTH{
        public String getDirection(){return "S";}
    },
    EAST, WEST;
    public String getDirection(){
        return "SW";
    }

}
