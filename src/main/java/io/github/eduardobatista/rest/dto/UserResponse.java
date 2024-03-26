package io.github.eduardobatista.rest.dto;

import io.github.eduardobatista.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResponse extends BaseResponse<UserResponse, User> {
    private String name;

    public UserResponse(User object) {
        super(object.getId());
        this.name = object.getName();
    }
}
