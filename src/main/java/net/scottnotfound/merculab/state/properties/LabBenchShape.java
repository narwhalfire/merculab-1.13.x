package net.scottnotfound.merculab.state.properties;

public enum LabBenchShape {

    NEUTRAL(0, "");

    private final int meta;
    private final String name;

    LabBenchShape(int meta, String name) {
        this.meta = meta;
        this.name = name;
    }

}
