public class RadioTraceData extends TraceData implements java.io.Serializable {
    static final long serialVersionUID = 7007267075134727811L;

    private int address;
    private int magic;

    public RadioTraceData(int type, int address, int magic) {
        super(type);

        this.address = address;
        this.magic   = magic;
    }


    public int getAddress() {
        return address;
    }

    public int getMagic() {
        return magic;
    }

    public String toString() {
        return super.toString() + ", " +
               "address: " + address  + ", " +
               "magic: "   + magic;
    }
}
