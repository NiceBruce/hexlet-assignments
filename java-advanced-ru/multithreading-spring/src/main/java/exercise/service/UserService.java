package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    // BEGIN
    public Mono<User> findById(long id) {
        return userRepository.findById(id);
    }

    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

    public Mono<User> update(User user, Long id) {
        Mono<User> userUpdate = findById(id);

        return userUpdate.flatMap(exUser -> {
            exUser.setFirstName(user.getFirstName());
            exUser.setLastName(user.getLastName());
            exUser.setEmail(user.getEmail());
            return userRepository.save(exUser);
        });
    }

    public Mono<Void> deleteById(Long id) {
        return userRepository.deleteById(id);
    }

    // END
}
