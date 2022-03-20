public abstract class Driver {
    
    //instance fields
    private String name;
    private String location;
    private String teamName;

    public Driver() {
    }

    public Driver(String name, String location, String teamName) {
        this.name = name;
        this.location = location;
        this.teamName = teamName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTeamName() {
        
        return this.teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    // @Override
    // public boolean equals(Object o) {
    //     return this.teamName.equals(((Driver)o).teamName);
    // }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", location='" + getLocation() + "'" +
            ", teamName='" + getTeamName() + "'" +
            "}";
    }
}