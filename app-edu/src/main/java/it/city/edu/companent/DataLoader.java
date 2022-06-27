//package it.city.edu.companent;
//
//import it.city.edu.entity.User;
//import it.city.edu.repository.AuthRepository;
//import it.city.edu.repository.RoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashSet;
//@Component
//public class DataLoader implements CommandLineRunner {
//    @Autowired
//    AuthRepository userRepository;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Value("${spring.datasource.initialization-mode}")
//    private String initMode;
//
//    @Override
//    public void run(String... args) throws Exception {
//        if (initMode.equals("always")) {
//            userRepository.save(
//                    new User(
//                            "Javohir",
//                            "Qoziboyev",
//                            "Mamur o'gli",
//                            "AB",
//                            "1234567",
//                            new Date(),
//                            "+998999615120",
//                            passwordEncoder.encode("11201120"),
//                            null,
//                            new HashSet<>(roleRepository.findAll())
//                    )
//            );
//        }
//
//    }
//}
