public abstract class TraceData implements java.io.Serializable {
    static final long serialVersionUID = 7007267075134727811L;

    private int type;

    public TraceData(int type) {
        this.type       = type;
    }

    public int getType() {
        return type;
    }

    public String toString() {
        return "type: " + type;
    }
}
