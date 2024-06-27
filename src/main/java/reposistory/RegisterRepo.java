package reposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auctionwebsite.model.UserInfo;

@Repository
public interface RegisterRepo extends JpaRepository<UserInfo, Long> {

}