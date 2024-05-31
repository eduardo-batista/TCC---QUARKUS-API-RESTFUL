package io.github.eduardobatista.rest.resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import com.github.javafaker.Faker;

import io.github.eduardobatista.domain.entity.Recipe;
import io.github.eduardobatista.domain.entity.Tag;
import io.github.eduardobatista.domain.entity.User;
import io.github.eduardobatista.repository.FollowRepository;
import io.github.eduardobatista.repository.RecipeRepository;
import io.github.eduardobatista.repository.TagRepository;
import io.github.eduardobatista.repository.UserRepository;
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

        tags.add(tagRepository.save(null, new Tag("Massas")));
        tags.add(tagRepository.save(null, new Tag("Cozidos")));
        tags.add(tagRepository.save(null, new Tag("Assados")));
        tags.add(tagRepository.save(null, new Tag("Fritos")));
        tags.add(tagRepository.save(null, new Tag("Molhos")));
        tags.add(tagRepository.save(null, new Tag("Café da Manhã")));
        tags.add(tagRepository.save(null, new Tag("Jantar")));
        tags.add(tagRepository.save(null, new Tag("Fitness")));
        tags.add(tagRepository.save(null, new Tag("Petiscos")));
        tags.add(tagRepository.save(null, new Tag("Frutos do Mar")));

        // Criar e salvar 5 receitas
        for (int i = 0; i < 10; i++) {
            Collections.shuffle(tags);

            Recipe recipe = new Recipe(
                    faker.food().dish(),
                    faker.lorem().sentence(),
                    faker.food().measurement() + " " + faker.food().ingredient(),
                    faker.lorem().paragraph(),
                    "https://www.anamariareceitas.com.br/wp-content/uploads/2022/10/Lasanha-a-bolonhesa.jpg",
                    tags.subList(0, 2));

            recipes.add(recipeRepository.save(users.get(0).getId(), recipe));
        }

        return Response.ok(recipes).build();
    }
}
