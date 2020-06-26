package Main.ModulesList;

import java.util.*;

/**
 * @Description:
 * @Author: Jacob Ninja
 * @Date:
 */
public class PGTModulesList implements ModulesList {

    // To register Modules have already registered.
    private List<Module> registerSet = new ArrayList<>();

    /**
     * In this scenario, there is an simplify assumption,
     * postgraduate students default register modules is all the modules in the file ModuleList;
     * undergraduate students default register modules is restricted and their module lists are from another file;
     *
     * @param c a module list the the student is registered foe.
     * @return update registered module list.
     */
    @Override
    public void registerModules(List<Module> c) {
        /**
         * In this scenario, there is an simplify assumption,
         * postgraduate students register modules is all the modules in the file ModuleList;
         * undergraduate students register modules, but have different credit standard.
         * Adding Modules via stream.
         */
        c.stream().filter(i -> !registerSet.contains(i))
                .forEach(i -> registerSet.add(new Module(i)));
//        return registerSet;
    }

    /**
     * Accessor method to get the registered module list information.
     *
     * @return a copy of original List instance.
     */
    public List<Module> getRegisterSet() {
        return new ArrayList<>(registerSet);
    }

    @Override
    public boolean getEnoughCredits() {
        //common implementation.
        int credit = 0;
        for (int i = 0; i < registerSet.size(); i++) {
            credit += Integer.valueOf(registerSet.get(i).getModuleCredit());
        }
        if (credit < 180) {
        }
        return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PGTModulesList)) return false;
        PGTModulesList that = (PGTModulesList) o;
        return registerSet != null ? registerSet.equals(that.registerSet) : that.registerSet == null;
    }

    @Override
    public int hashCode() {
        return registerSet != null ? registerSet.hashCode() : 0;
    }
}
