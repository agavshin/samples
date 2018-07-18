package gal.springangular.domain;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Data
public class Sub {

    private String id = UUID.randomUUID().toString();

    private String name;

    private List<Sub> children = new ArrayList<>();

    public Sub() {
    }

    public Sub(String name) {
        this.name = name;
    }

    public Stream<Sub> flattened() {
        return Stream.concat(
                Stream.of(this), children.stream().flatMap(Sub::flattened));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sub sub = (Sub) o;
        return Objects.equals(id, sub.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
