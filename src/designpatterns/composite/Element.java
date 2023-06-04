package designpatterns.composite;

import java.util.Objects;

public abstract class Element {
    private int id;
    public Element(int id){
        this.id=id;
    }

    protected Element() {
    }

    public int getId() {
        return id;
    }

    public abstract int empTot();

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Element other = (Element) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
