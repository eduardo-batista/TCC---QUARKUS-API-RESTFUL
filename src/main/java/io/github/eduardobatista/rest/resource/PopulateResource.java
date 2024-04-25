package io.github.eduardobatista.rest.resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

import org.neo4j.ogm.session.Session;

import com.github.javafaker.Faker;

import io.github.eduardobatista.domain.entity.Recipe;
import io.github.eduardobatista.domain.entity.Tag;
import io.github.eduardobatista.domain.entity.User;
import io.github.eduardobatista.domain.repository.FollowRepository;
import io.github.eduardobatista.domain.repository.RecipeRepository;
import io.github.eduardobatista.domain.repository.TagRepository;
import io.github.eduardobatista.domain.repository.UserRepository;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/populate")
public class PopulateResource {
    UserRepository userRepository = new UserRepository();
    FollowRepository followRepository = new FollowRepository();
    TagRepository tagRepository = new TagRepository();
    RecipeRepository recipeRepository = new RecipeRepository();

    // @POST
    // public Response populate() {

    // Faker faker = new Faker();

    // var dundun = userRepository.save(null, new User("Dundun", "dundun@email.com",
    // "123456", LocalDate.now(), null,
    // "Pq eu escolhi java meu deus", "@Dundun", false));
    // var julinho = userRepository.save(null, new User("Julinho",
    // "julinho@email.com", "123456", LocalDate.now(),
    // null, "Jesuscripto", "@Julinho", false));
    // var yhan = userRepository.save(null, new User("Yhan", "yhan@email.com",
    // "123456", LocalDate.now(), null,
    // "ouvido meio cheio", "@Yhan", false));
    // var richard = userRepository.save(null, new User("Richard",
    // "richard@email.com", "123456", LocalDate.now(),
    // null, "MF Destroyer", "@Richard", false));

    // followRepository.save(null, new Follow(dundun, julinho));
    // followRepository.save(null, new Follow(yhan, richard));
    // followRepository.save(null, new Follow(julinho, dundun));

    // ArrayList<Tag> listTags = new ArrayList<Tag>();

    // var assados = tagRepository.save(null, new Tag("Assados"));
    // var cozidos = tagRepository.save(null, new Tag("Cozidos"));
    // var fritos = tagRepository.save(null, new Tag("Fritos"));
    // var massas = tagRepository.save(null, new Tag("Massas"));
    // var carnes = tagRepository.save(null, new Tag("Carnes"));
    // var jantar = tagRepository.save(null, new Tag("jantares"));

    // listTags.add(assados);
    // listTags.add(cozidos);
    // listTags.add(carnes);

    // recipeRepository.save(yhan.getId(), new Recipe("Carne de Panela", "Carne de
    // gado gostosa", "Carne & Panela", "Butar carne na panela", null, listTags));

    // listTags.clear();
    // listTags.add(massas);
    // listTags.

    // recipeRepository.save(dundun.getId(), new Recipe("Lazanha", "Garfield gosta",
    // "Laz & Zanha", "Faz a lazanha ai mano", null, listTags));

    // return Response.ok().build();
    // }

    @POST
    public Response populateTeste() {
        Random random = new Random();
        Faker faker = new Faker(new Locale("pt-BR"));
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Tag> tags = new ArrayList<>();
        ArrayList<Recipe> recipes = new ArrayList<>();

        // Criar e salvar 5 usuários
        for (int i = 0; i < 5; i++) {
            User user = new User(
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    "123456",
                    LocalDate.now(),
                    null,
                    faker.lorem().sentence(),
                    "@" + faker.name().username(),
                    false);
            users.add(userRepository.save(null, user));
        }

        // Criar e salvar 5 tags
        for (int i = 0; i < 5; i++) {
            Tag tag = new Tag("TAG " + i);
            tags.add(tagRepository.save(null, tag));
        }

        // Criar e salvar 5 receitas
        for (int i = 0; i < 5; i++) {
            Collections.shuffle(tags);

            Recipe recipe = new Recipe(
                    faker.food().dish(),
                    faker.lorem().sentence(),
                    faker.lorem().sentence(),
                    faker.lorem().paragraph(),
                    null,
                    tags.subList(0, 2));

            recipes.add(recipeRepository.save(users.get(0).getId(), recipe));
        }

        return Response.ok(recipes).build();
    }
}
