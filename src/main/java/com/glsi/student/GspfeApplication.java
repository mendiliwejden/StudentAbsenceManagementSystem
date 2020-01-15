package com.glsi.student;

import com.glsi.student.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GspfeApplication implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public GspfeApplication(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(GspfeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Role admin = new Role("ROLE_ADMIN");
//        Role user = new Role("ROLE_USER");
//        roleRepository.save(admin);
//        roleRepository.save(user);
}
}
