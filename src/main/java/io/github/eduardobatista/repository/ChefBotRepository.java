package io.github.eduardobatista.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import io.github.eduardobatista.database.graph.SessionFactoryProducer;
import io.github.eduardobatista.domain.entity.ChefBot;
import io.github.eduardobatista.domain.entity.Tag;
import io.github.eduardobatista.domain.entity.relationships.Tagged;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ChefBotRepository {

    private SessionFactory sessionFactory;
    private Session session;

    @Inject
    public ChefBotRepository() {
        this.sessionFactory = new SessionFactoryProducer().sessionFactory;
    }

    protected Session getSession() {
        if (this.session == null)
            session = sessionFactory.openSession();
        return this.session;
    }

    public ChefBot getChefBot() {
        ChefBot chefBot;
        var aux = getSession().loadAll(ChefBot.class);
        if (aux == null || aux.isEmpty()) {
            chefBot = new ChefBot();
        } else {
            chefBot = aux.iterator().next();
        }

        System.out.println("\n\n\n\n\n\n\n\n" + chefBot + "\n\n\n\n");

        return chefBot;
    }

    public Collection<Tagged> getTaggeds(ArrayList<Long> tagsId, ChefBot chefBot) {

        Collection<Tagged> taggeds = new ArrayList<Tagged>();
        tagsId.forEach(id -> {
            Tag tag = getSession().load(Tag.class, id);

            // System.out.println("\n\n\n\n\n\n\n\n" + tag + "\n\n\n\n");

            taggeds.add(
                    new Tagged(chefBot.getAbstractRecipeForRecomendation(), tag));
        });

        return taggeds;
    }

    public Iterable<Map<String, Object>> getRecomendations(ArrayList<Long> tagsId) {
        var chefBot = getChefBot();

        getSession().save(chefBot);

        var taggeds = getTaggeds(tagsId, chefBot);

        getSession().save(taggeds);

        chefBot.getAbstractRecipeForRecomendation().setListTags(taggeds);

        getSession().save(chefBot.getAbstractRecipeForRecomendation());
        getSession().save(chefBot);

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("abstractRecipeId", chefBot.getAbstractRecipeForRecomendation().getId());

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
                "CALL gds.nodeSimilarity.filtered.stream('tagged_recipes', {sourceNodeFilter:"
                        + parameters.get("abstractRecipeId")
                        + " , targetNodeFilter:'abstract_recipe' , relationshipWeightProperty: 'amount', similarityMetric: 'COSINE'})\r\n"
                        + //
                        "YIELD node1, node2, similarity\r\n" + //
                        "RETURN gds.util.asNode(node1).name AS Recipe1, gds.util.asNode(node2) AS Recipe2, similarity\r\n"
                        + //
                        "ORDER BY similarity DESCENDING, Recipe1, Recipe2",
                parameters);

        getSession().delete(chefBot.getAbstractRecipeForRecomendation());

        return aux.queryResults();
    }
}
