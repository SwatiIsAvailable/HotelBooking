package com.app.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class LoginForm {
  private String email;
  private String password;
}
