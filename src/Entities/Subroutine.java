package Entities;

import java.util.ArrayList;

public class Subroutine {

    private String subroutineId;
    private ArrayList<String> loops;

    public Subroutine(String subroutineId, ArrayList<String> loops) {
        this.subroutineId = subroutineId;
        this.loops = loops;
    }

    public String getSubroutineId() {
        return subroutineId;
    }

    public void setSubroutineId(String subroutineId) {
        this.subroutineId = subroutineId;
    }

    public ArrayList<String> getLoops() {
        return loops;
    }

    public void setLoops(ArrayList<String> loops) {
        this.loops = loops;
    }

    
    
    
}
