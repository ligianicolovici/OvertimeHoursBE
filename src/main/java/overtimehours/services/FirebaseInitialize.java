package overtimehours.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
public class FirebaseInitialize {
    @PostConstruct
    public void initialize() {
        try {
            //ClassPathResource classPathResource = new ClassPathResource("overtime-hours-firebase-adminsdk-hinvf-f40327af5a.json");
String credentials="{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"overtime-hours\",\n" +
        "  \"private_key_id\": \"f40327af5adf2b80fbef7ebe1fc3e21a97879a9d\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC9r6tr7WPn7sFu\\neD4mC5eoD3mHt9otLKFFc29FLrqDG0W+qb4ITqQVaV4YABMPNt/OHDax1tumHGzV\\n8nLKygc9UtRfMdRwZUXy9nly4fqTl6UptEzgX3p/mQZjSB9EGqDe8M3SW+sH35fB\\nGetLBR5s1ZuTsSTrE6F5xioPOJ2lOOTTYk7p+IPux83CWbIj4PUyJpulAn1OX9/2\\nWHK97lobrZSxw/hguzEUk+5YdxbQSmyCexvCAneAYr0JUo0bPLvwzSyRInt0hTHE\\n176RZt1kYFDr+8CM2RqXzOov7PK8NSc0VE2GbhrlfdQboO0G9L4qJO0QXrIRgdwE\\nFXz/PznDAgMBAAECggEAEQqkZBZlGID24a9g2gP5mvcE8qBqzaf9HZaHQ4s1GEyB\\nDn+egicnW7SzNOtj/is7N5lWTdG67k7bqLijzCJ5b9UUtzBS+mQTp2yx/ZQq2H64\\nlalMRm43I0z16dtT0vqkQ4Z731XNvrY9iPMEriFAGgQMFA+t4u0veTEuzysSReke\\nh6QSeU3GjoFlTMxGGGzcfWtH03ykO4evvAwf7Ab1gst9OZJ3k2PLtW44a4aBNarG\\nxDsJZJhelcO/aFJCfiCCPTMWdMm5D0v4l44uTb+QCA7wuTyyqOIVXGyox30rk2f4\\nWNXraMXum/X1ttkQK54alB95ZARXf0dZ3HLAdrqM+QKBgQDc8ZbaqHNzecUUtkS9\\nHWmHuxUJPOivyN5UU8d7bz0gjzJtleq32/11YujDe4sEl4wH2mhWjeT0IK+dwyAU\\n2/yT2PKujCHUBf2NIoylXWnQBytb0he0Yl996URmLxIBLde23QJFj1Vsyp2g94uD\\noka9H/KoGGbdN08WvFx9fNIB6wKBgQDbyHJHLqtjbcp02KfyZi/6V58ewzrWWnPK\\nTMIFN+oaNO1qKwphlyxSc7N6ebKZX30MEVlQYOMigWc3vb58Cf0gY2oa9l9ujB2w\\niq0+0MQ6+hxae2rpK6WooAcc/97XQJnOnua/w6nOGN5PPJtVvL0kw+6YADgP9yqR\\nmd8OArTZiQKBgCFh5zcWaIs0BNECURR50y4M2DrTFx0oLl9zYRqHTicTO71O/23U\\nMVGBJkYS7og9FY1CNK2PNt6j6aTejIRaUKlOCCzTQ3EzjoG6jKGXoZsq2iQ/dBFv\\nI5p0mj8OMZw3/vXEIsm7xF5xpqthe5Th+eotX46CUVhvOOXog9j5+QkNAoGAOCtB\\nMXn26UFM5ZHdUjDVgChWjTkd11xkjUYSq9y/YpU2O9TmIUzOUgAp1Vo2z8W8opHo\\nfodUZSQnKfK/NNokq9tcaamfeBQtHkpCvnQCQoRbLLCwAQlbm/3R85Jc9CwZYtnv\\nZQDva7GlVyDJHh9PQ48jcgtUSJhFc9KcU+tTJukCgYEApxQyPzI2Yjf5fHdBkn9y\\nUQgKO1TfeS+1ZXY5Qt3yXa7QmoZuJIF/G1+wwy+e/4J5hHWTJqVWs30RNvVopXQS\\nK/4QLDN6tkO9hrc6Ki3ijdxa1PM5xtrxV8Y73LCugCxGeKk36BGy2MLb6/01sSf5\\nCx1JTuhcZi/Nt3V4xDzCvns=\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"firebase-adminsdk-hinvf@overtime-hours.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"100530404802230588241\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-hinvf%40overtime-hours.iam.gserviceaccount.com\"\n" +
        "}";
            InputStream inputStream = new ByteArrayInputStream(credentials.getBytes());
//            File somethingFile = File.createTempFile("overtime-hours-firebase-adminsdk-hinvf-f40327af5a", ".json");
//            try {
//                FileUtils.copyInputStreamToFile(inputStream, somethingFile);
//            } finally {
//                IOUtils.closeQuietly(inputStream);
//            }
//            FileInputStream serviceAccount =
//                    new FileInputStream("overtime-hours-firebase-adminsdk-hinvf-f40327af5a.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .setDatabaseUrl("https://overtime-hours-default-rtdb.europe-west1.firebasedatabase.app/")
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}