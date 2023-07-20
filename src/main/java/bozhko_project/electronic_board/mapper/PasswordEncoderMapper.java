package bozhko_project.electronic_board.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncoderMapper {
    PasswordEncoder passwordEncoder;

    @EncodedMapping(encode = "value")
    public String encode(String value) {
        return passwordEncoder.encode(value);//bCryptPasswordEncoder.encode(value);
    }
}
