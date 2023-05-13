package com.leviethoang.init;

import com.leviethoang.model.Role;
import com.leviethoang.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class ApplicationStart implements CommandLineRunner {
    private final RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {
        Role user = new Role(1L,"USER");
        Role admin = new Role(2L , "ADMIN");
        roleRepository.saveAll(Arrays.asList(user,admin));
    }
}
