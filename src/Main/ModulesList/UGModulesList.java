package Main.ModulesList;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description:
 * @Author: Jacob Ninja
 * @Date:
 */
public class UGModulesList implements ModulesList {

    // To register Modules have already registered.
    private List<Module> registerSet = new ArrayList<>();

    /**
     * In this scenario, there is an simplify assumption,
     * postgraduate students default register modules is all the modules in the file ModuleList;
     * undergraduate students default register modules is restricted and their module lists are from another file;
     *
     * @param c a module list need to register
     * @return a updated resisted module record for this student.
     */
    public void registerModules(List<Module> c) {
        c.stream().filter(i -> !registerSet.contains(i))
                .forEach(i -> registerSet.add(new Module(i)));
    }

    /**
     * Accessor method to get the registered module list information.
     *
     * @return a copy of original List instance.
     */
    public List<Module> getRegisterSet() {
        return new ArrayList<>(registerSet);
    }

    /**
     * To check whether a undergraduate student is currently registered
     * for enough credits.(120 for undergraduate)
     *
     * @return true if this undergraduate student is currently registered
     * for enough credits
     */
    @Override
    public boolean getEnoughCredits() {
        int credit = 0;
        for (int i = 0; i < registerSet.size(); i++) {
            credit += Integer.valueOf(registerSet.get(i).getModuleCredit());
        }
        if (credit < 120) {
        }
        return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UGModulesList)) return false;
        UGModulesList that = (UGModulesList) o;
        return getRegisterSet() != null ? getRegisterSet().equals(that.getRegisterSet()) : that.getRegisterSet() == null;
    }

    @Override
    public int hashCode() {
        return getRegisterSet() != null ? getRegisterSet().hashCode() : 0;
    }
}
