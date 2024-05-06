package io.github.eduardobatista.domain.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.neo4j.ogm.model.Result;

import io.github.eduardobatista.domain.entity.Recipe;
import io.github.eduardobatista.domain.entity.Tag;
import io.github.eduardobatista.domain.entity.User;
import io.github.eduardobatista.domain.entity.relationships.Tagged;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository extends BaseRepository<User> {

    public Iterable<Map<String,Object>> loadFeed(Long id) {

        var user = getSession().load(User.class, id);

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("abstractRecipeId", user.getAbstractRecipeForLikenessRepresentation().getId());

        getSession().query("call gds.graph.drop('tagged_recipes')", parameters);

        getSession().query("CALL gds.graph.project(\r\n" + //
                "    'tagged_recipes',\r\n" + //
                "    ['abstract_recipe', 'tag'],\r\n" + //
                "    {\r\n" + //
                "        TAGGED_BY: {\r\n" + //
                "            properties: {\r\n" + //
                "                amount: {\r\n" + //
                "                    property: 'amount',\r\n" + //
                "                    defaultValue: 0.1\r\n" + //
                "                }\r\n" + //
                "            }\r\n" + //
                "        }\r\n" + //
                "    }\r\n" + //
                ");", parameters);

        var aux = getSession().query(
                "CALL gds.nodeSimilarity.filtered.stream('tagged_recipes', {sourceNodeFilter:"+parameters.get("abstractRecipeId")+" , targetNodeFilter:'abstract_recipe' , relationshipWeightProperty: 'amount', similarityMetric: 'COSINE'})\r\n"
                        + //
                        "YIELD node1, node2, similarity\r\n" + //
                        "RETURN gds.util.asNode(node1).name AS Recipe1, gds.util.asNode(node2) AS Recipe2, similarity\r\n"
                        + //
                        "ORDER BY similarity DESCENDING, Recipe1, Recipe2",
                parameters);

        return aux.queryResults();
    }

    @Override
    public User load(Long id) {
        return getSession().load(User.class, id);
    }

    @Override
    public Collection<User> loadAll() {
        return getSession().loadAll(User.class);
    }

    @Override
    public Collection<User> loadAllBy(Long id) {
        throw new UnsupportedOperationException("Use other the method 'loadAll'");
    }

    @Override
    public User save(Long userId, User object) {
        getSession().save(object);
        return object;
    }

    public User likes(Long id, Long recipeId) {
        var recipe = getSession().load(Recipe.class, recipeId);
        var user = getSession().load(User.class, id);
        boolean chave = true;

        user.getListLikes().add(recipe);

        if (!user.getAbstractRecipeForLikenessRepresentation().getListTags().isEmpty()) {
            for (Tag tag : recipe.getListTags()) {
                for (Tagged tagged : user.getAbstractRecipeForLikenessRepresentation().getListTags()) {
                    if (tag.getId() == tagged.getTag().getId()) {
                        tagged.incrementAmount();
                        chave = !chave;
                    }
                }
                if (chave) {
                    user.getAbstractRecipeForLikenessRepresentation().getListTags()
                            .add(new Tagged(user.getAbstractRecipeForLikenessRepresentation(), tag));
                }
                chave = true;
            }
        } else {
            for (Tag tag : recipe.getListTags()) {
                user.getAbstractRecipeForLikenessRepresentation().getListTags()
                        .add(new Tagged(user.getAbstractRecipeForLikenessRepresentation(), tag));
            }
        }

        getSession().save(user);

        return user;
    }

    @Override
    public User update(Long id, User object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
