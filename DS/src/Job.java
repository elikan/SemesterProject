public class Job {
    @Override
    public String toString() {
        return "Job{" +
                "type=" + type +
                ", ID=" + ID +
                '}';
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private Type type;
    int ID;
    public Job(Type type, int ID) {
        this.type = type;
        this.ID=ID;
    }
}
