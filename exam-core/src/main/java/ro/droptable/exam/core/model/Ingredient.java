package ro.droptable.exam.core.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by vlad on 20/06/2017.
 */
@Entity
@Table(name = "ingredient")
@NamedEntityGraphs(
        @NamedEntityGraph(
                name = "ingredientWithPizza",
                attributeNodes = {
                        @NamedAttributeNode(
                                value = "pizza"
                        )
                }
        )
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Ingredient extends BaseEntity<Long> {
    @Column(name = "name")
    private String name;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;
}
