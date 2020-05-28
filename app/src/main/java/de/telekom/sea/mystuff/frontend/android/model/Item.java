package de.telekom.sea.mystuff.frontend.android.model;


import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item implements Serializable {

    private Long id;

    private String name;
    private int amount;
    private String location;
    private String description;
    private Date lastUsed;

}
