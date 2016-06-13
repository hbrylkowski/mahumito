package com.brylkowski.mahumito.mahumito.models;

public class SimpleObject {
    private String label;
    private Integer value;

    public SimpleObject(String label, Integer value) {
        this.label = label;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleObject that = (SimpleObject) o;

        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        return value != null ? value.equals(that.value) : that.value == null;

    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
