import com.hyf.cloud.OauthApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OauthApplication.class)
public class OAuthOSSApplication7789Test {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void t1(){
        String password = passwordEncoder.encode("123456");
        System.out.println(password);
    }
}
