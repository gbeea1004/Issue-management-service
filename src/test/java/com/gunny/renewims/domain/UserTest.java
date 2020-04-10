package com.gunny.renewims.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    public static final User GUNNY = User.builder()
                                         .id(1L)
                                         .userId("lime")
                                         .name("성건희")
                                         .password("123123")
                                         .build();

    public static final User MUZZI = User.builder()
                                         .id(2L)
                                         .userId("muzzi")
                                         .name("무시무시한무지")
                                         .password("111111")
                                         .build();
    @Test
    public void update_owner() throws Exception {
        //given
        User origin = GUNNY;

        User loginedUser = origin;

        User target = User.builder()
                          .id(origin.getId())
                          .userId(origin.getUserId())
                          .name("재키와이")
                          .password(origin.getPassword())
                          .build();
        //when
        origin.update(loginedUser, target);

        //then
        assertThat(origin.getName()).isEqualTo(target.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void update_not_Owner() throws Exception {
        //given
        User origin = GUNNY;
        User loginedUser = MUZZI;
        User target = User.builder()
                          .id(origin.getId())
                          .userId(origin.getUserId())
                          .name("재키와이")
                          .password(origin.getPassword())
                          .build();
        
        //when
        origin.update(loginedUser, target);
    }
    
    @Test
    public void update_match_password() throws Exception {
        //given
        User origin = GUNNY;
        User loginedUser = origin;
        User target = User.builder()
                          .id(origin.getId())
                          .userId(origin.getUserId())
                          .name("재키와이")
                          .password(origin.getPassword())
                          .build();

        //when
        origin.update(loginedUser, target);

        //when
        assertThat(origin.getName()).isEqualTo(target.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void update_mismatch_password() throws Exception {
        //given
        User origin = GUNNY;
        User loginedUser = origin;
        User target = User.builder()
                          .id(origin.getId())
                          .userId(origin.getUserId())
                          .name("재키와이")
                          .password("aaaaaaa")
                          .build();

        //when
        origin.update(loginedUser, target);
    }
}