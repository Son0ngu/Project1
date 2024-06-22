package com.auction.Project1.Main.Constructor;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class RoomInfo {
    private String id;
    private String ownerId;
    private String name;
}