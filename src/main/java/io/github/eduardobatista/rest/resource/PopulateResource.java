package io.github.eduardobatista.rest.resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

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

    @POST
    public Response populateTeste() {
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
