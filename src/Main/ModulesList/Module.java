package Main.ModulesList;

/**
 * This Module Class is for these store information about modules
 * whose data is from a txt file.
 * Open to modify except module code, so just use final key word to constraint Class
 * cannot be inherited, no one could  and not prov
 */
public final class Module {
    /**
     * String is an immutable Class, each time give a String new value,
     * a new String is created, so declarations like "final String" are unnecessary.
     */
    private String moduleCode;
    private String moduleName;
    private String moduleCredit;

    /**
     * public constructor
     *
     * @param moduleCode   module code, e.g. CSC8002
     * @param moduleName   mudule name, e.g. Advanced Programming
     * @param moduleCredit module credit, e.g. 20
     */
    public Module(String moduleCode, String moduleName, String moduleCredit) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleCredit = moduleCredit;
    }

    // replace clone method, copy constructor.
    public Module(Module module) {
        this(module.moduleCode, module.moduleName, module.moduleCredit);
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleCredit() {
        return moduleCredit;
    }

    /**
     * Only in a situation that two modules have the same name and module code
     * means these two modules are equals.
     * @param o an input object
     * @return the compare result.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Module)) return false;
        Module module = (Module) o;
        if (!getModuleCredit().equals(module.getModuleCredit())) return false;
        if (getModuleCode() != null ? !getModuleCode().equals(module.getModuleCode()) : module.getModuleCode() != null)
            return false;
        return getModuleName() != null ? getModuleName().equals(module.getModuleName()) : module.getModuleName() == null;
    }

    @Override
    public int hashCode() {
        int result = getModuleCode() != null ? getModuleCode().hashCode() : 0;
        result = 31 * result + (getModuleName() != null ? getModuleName().hashCode() : 0);
        result = 31 * result + Integer.valueOf(getModuleCredit());
        return result;
    }

    @Override
    public String toString() {
        return
//                "Module{" +
                "moduleCode='" + moduleCode + '\'' +
                        ", moduleName='" + moduleName + '\'' +
                        ", moduleCredit='" + moduleCredit + '\''
//                        + '}'
                ;
    }
}
