package practicum3.backtracker;

/**
 * This class represents the classic recursive backtracking algorithm.
 * It has a solver that can take a valid configuration and return a
 * solution, if one exists.
 * 
 * @author GCCIS Faculty
 */
public class Backtracker {
    /*
     * Should debug output be enabled?
     */
    private boolean debug;
    
    /**
     * Initialize a new backtracker
     * 
     * @param debug Is debugging output enabled?
     */
    public Backtracker(boolean debug) {
        this.debug = debug;
        if (this.debug) {
            System.out.println("backtracker.Backtracker debugging enabled...");
        }
    }
    
    /**
     * A utility routine for printing out various debug messages.
     * 
     * @param msg The type of config being looked at (current, goal, 
     *  successor, e.g.)
     * @param config The config to display
     */
    private void debugPrint(String msg, Configuration config, int indentLevel) {
        if (this.debug) {
            String indent = "";
            for (int i = 0;i < indentLevel;++i)
                indent += "\t";

            System.out.println(indent + msg + ":\n" + indent + config.toString().replace("\n","\n" + indent));
        }
    }
    
    /**
     * Try find a solution, if one exists, for a given configuration.
     * 
     * @param config A valid configuration
     * @return A solution config, or null if no solution
     */
    public Configuration solve(Configuration config) {
        debugPrint("Current config", config, 0);
        if (config.isGoal()) {
            debugPrint("Goal config", config, 1);
            return config;
        } else {
            for (Configuration child : config.getSuccessors()) {
                if (child.isValid()) {
                    debugPrint("Valid successor", child, 1);
                    Configuration sol = solve(child);
                    if(sol != null) {
                        return sol;
                    }
                } else {
                    debugPrint("Invalid successor", child, 1);
                }
            }
            // implicit backtracking happens here
        } 
        return null;
    }
}
