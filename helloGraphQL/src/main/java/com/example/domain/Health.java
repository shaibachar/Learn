package com.example.domain;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Health {
    @Id @GeneratedValue
    @GraphQLQuery(name = "id", description = "A client's id")
    private Long id;
    @GraphQLQuery(name = "name", description = "A client's name")
    private @NonNull String name;
    @GraphQLQuery(name = "score", description = "A client's health score")
    private @NonNull Float score;

}