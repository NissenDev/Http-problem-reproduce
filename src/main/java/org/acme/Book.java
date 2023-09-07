package org.acme;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class Book {

    @NotBlank(message="Title may not be blank")
    public String title;
    @NotBlank(message="Email may not be blank")
    @Pattern(regexp = ".*\\.[a-zA-Z]{2,31}$", message = "must be a well-formed email address")
    public String email;
}
