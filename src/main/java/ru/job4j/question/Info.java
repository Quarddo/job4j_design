package ru.job4j.question;

import java.util.Objects;

public class Info {

    private int added;
    private int changed;
    private int delete;

    public Info(int added, int changed, int delete) {
        this.added = added;
        this.changed = changed;
        this.delete = delete;
    }

    public int getAdded() {
        return added;
    }

    public void setAdded(int added) {
        this.added = added;
    }

    public int getChanged() {
        return changed;
    }

    public void setChanged(int changed) {
        this.changed = changed;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Info info = (Info) obj;
        return info.added == added
                && info.changed == changed
                && info.delete == delete;
    }

    @Override
    public int hashCode() {
        return Objects.hash(added, changed, delete);
    }
}