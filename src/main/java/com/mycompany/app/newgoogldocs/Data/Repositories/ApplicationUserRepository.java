package com.mycompany.app.newgoogldocs.Data.Repositories;

import com.mycompany.app.newgoogldocs.Models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,String> {
    ApplicationUser findByEmail(String email);
    boolean existsByEmail(String email);
}
