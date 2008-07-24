public class FunctionTraceData extends TraceData implements java.io.Serializable {
    static final long serialVersionUID = 7007267075134727811L;

    private int moduleId;
    private int functionId;

    public FunctionTraceData(int type, int moduleId, int functionId) {
        super(type);

        this.moduleId   = moduleId;
        this.functionId = functionId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public int getFunctionId() {
        return functionId;
    }

    public String toString() {
        return super.toString() + ", " +
               "moduleId: "   + moduleId  + ", " +
               "functionId: " + functionId;
    }
}
