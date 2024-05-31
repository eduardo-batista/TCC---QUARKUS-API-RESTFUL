package io.github.eduardobatista.dto;

import io.github.eduardobatista.domain.entity.relationships.Follow;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowResponse extends BaseResponse<FollowResponse, Follow> {
    private UserResponse follower;
    private UserResponse followed;

    public FollowResponse(Follow object) {
        super(object.getId());
        this.follower = new UserResponse(object.getFollower());
        this.followed = new UserResponse(object.getFollowed());
    }

}
