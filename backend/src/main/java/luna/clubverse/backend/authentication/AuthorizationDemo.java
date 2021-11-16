package luna.clubverse.backend.authentication;

import luna.clubverse.backend.user.entity.Authority;
import luna.clubverse.backend.user.entity.User;
import luna.clubverse.backend.user.repository.UserRepository;
import luna.clubverse.backend.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class AuthorizationDemo {

    private final UserRepository userRepository;

    public AuthorizationDemo(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authorize(Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(()-> new UsernameNotFoundException("The user with the given username could not found."));

        return user.getAuthorities().contains(new Authority("USER",1L));
    }
}
