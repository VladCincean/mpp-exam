package ro.droptable.exam.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vlad on 20/06/2017.
 */
@Entity
@Table(name = "pizza")
@NamedEntityGraphs(
        @NamedEntityGraph(
                name = "pizzaWithIngredients",
                attributeNodes = {
                        @NamedAttributeNode(
                                value = "ingredients"
                        )
                }
        )
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Pizza extends BaseEntity<Long> {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private float price;

    @OneToMany(
            mappedBy = "pizza",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, // default
            orphanRemoval = true
    )
    private Set<Ingredient> ingredients = new HashSet<>();
}
