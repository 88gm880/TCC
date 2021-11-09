package gmacias.control.repositories;

import gmacias.model.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends JpaRepository<Login, Integer> {


    @Query("select (count(*) = 1 ) " +
            "from Login " +
            "where login_user = :username " +
            "and login_pwd = :password")
    public boolean isValidLogin(
            @Param("username")String username,
            @Param("password")String password);

}
