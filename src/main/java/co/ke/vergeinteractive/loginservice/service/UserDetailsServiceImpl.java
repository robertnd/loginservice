package co.ke.vergeinteractive.loginservice.service;


import co.ke.vergeinteractive.loginservice.model.dto.UserDetailsImpl;
import co.ke.vergeinteractive.loginservice.model.entity.ERole;
import co.ke.vergeinteractive.loginservice.model.entity.User;
import co.ke.vergeinteractive.loginservice.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    //private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    //@Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
         */
        //TODO: simulated users
        User user = null;
        Set<String> views = new HashSet<>();
        switch(username) {
            case "intermed@nosuchmail.org":
            case "intermed":
                user = new User( "intermed@nosuchmail.org", "intermed@nosuchmail.org", passwordEncoder.encode("intermed"));
                user.setId(new Random().nextLong());
                user.setRoles(Utils.createSet(ERole.ROLE_INTERMED.name()));
                user.setViews(Utils.createSet("customers-im", "leads-im", "leads-new", "lead-detail"));
                break;
            case "supervisor@nosuchmail.org":
            case "supervisor":
                user = new User( "supervisor@nosuchmail.org", "supervisor@nosuchmail.org", passwordEncoder.encode("supervisor"));
                user.setId(new Random().nextLong());
                user.setRoles(Utils.createSet(ERole.ROLE_SUPERVISOR.name()));
                user.setViews(Utils.createSet("advisors", "advisors-new", "customers", "customers-detail", "leads", "leads-new", "lead-detail", "products", "products-new", "product-detail","lead-detail"));
                break;
            default:
                user = new User( "guest", "guest@nosuchmail.org", passwordEncoder.encode("guest"));
                //user.setId(new Random().nextLong());
                user.setRoles(Utils.createSet(ERole.ROLE_GUEST.name()));
                break;
        }
        return UserDetailsImpl.build(user);
    }
}

