package exercise.controllers;

import io.javalin.http.Handler;
import java.util.List;
import java.util.Map;
import io.javalin.core.validation.Validator;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.JavalinValidation;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

import exercise.domain.User;
import exercise.domain.query.QUser;

public final class UserController {

    public static Handler listUsers = ctx -> {

        List<User> users = new QUser()
            .orderBy()
                .id.asc()
            .findList();

        ctx.attribute("users", users);
        ctx.render("users/index.html");
    };

    public static Handler showUser = ctx -> {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        User user = new QUser()
            .id.equalTo(id)
            .findOne();

        ctx.attribute("user", user);
        ctx.render("users/show.html");
    };

    public static Handler newUser = ctx -> {

        ctx.attribute("errors", Map.of());
        ctx.attribute("user", Map.of());
        ctx.render("users/new.html");
    };

    public static Handler createUser = ctx -> {
        // BEGIN
        String firstName = ctx.formParam("firstName");
        String lastName = ctx.formParam("lastName");
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");

        // Добавляем валидатор для каждого поля
        Validator<String> firstNameValidator = ctx.formParamAsClass("firstName", String.class)
                .check(it -> !it.isEmpty(), "Имя не должно быть пустым");

        Validator<String> lastNameValidator = ctx.formParamAsClass("lastName", String.class)
                .check(it -> !it.isEmpty(), "Фамилия не должны быть пустой");

        EmailValidator eV = EmailValidator.getInstance();
        Validator<String> emailValidator =  ctx.formParamAsClass("email", String.class)
                .check(e -> eV.isValid(e), "email isn't valid");

        Validator<String> passwordValidator = ctx.formParamAsClass("password", String.class)
                .check(p -> p.length() > 3, "password shoild be more 4 symbols")
                .check(p -> StringUtils.isNumeric(p), "password should have only digits");

        // Валидируем данные из форм и собираем все ошибки валидации в словарь
        // Если ошибок нет, словарь будет пустой
        Map<String, List<ValidationError<? extends Object>>> errors = JavalinValidation.collectErrors(
                firstNameValidator,
                lastNameValidator,
                emailValidator,
                passwordValidator
                );

        if (!errors.isEmpty()) {
            // Устанавливаем код ответа
            ctx.status(422);
            // Передаем ошибки и данные компании
            ctx.attribute("errors", errors);
            User invalidUser = new User(firstName, lastName, email, password);
            ctx.attribute("user", invalidUser);
            ctx.render("users/new.html");
            return;
        }

        User user = new User(firstName, lastName, email, password);
        user.save();

        ctx.sessionAttribute("flash", "User успешно создан");
        ctx.redirect("/users");
        // END
    };
}
