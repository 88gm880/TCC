package org.openjfx.control.repositories;

import org.openjfx.model.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends JpaRepository<Login, Integer> {


    @Query("select (count(*) = 1 ) " +
            "from Login " +
            "where login_user = :username " +
            "and login_password = :password")
    public boolean isValidLogin(
            @Param("username")String username,
            @Param("password")String password);

}
