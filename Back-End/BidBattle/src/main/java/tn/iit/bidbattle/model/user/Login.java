package tn.iit.bidbattle.model.user;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class Login {
    private String email;
    private String password;
}
