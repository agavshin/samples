package gal.springangular.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Data
@Builder
@Document(collection = "demo")
public class Demo {

    @Id
    private String id;

    @Getter
    private String demoGuid = UUID.randomUUID().toString();

    @Field("name")
    private String name;

    @Field("created_at")
    private Instant createdAt = Instant.now();

    private List<Sub> subs = new ArrayList<>();

    public Stream<Sub> flattenedSub() {
        return subs.stream().flatMap(Sub::flattened);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demo demo = (Demo) o;
        return Objects.equals(id, demo.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
