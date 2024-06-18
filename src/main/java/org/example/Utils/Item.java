package org.example.Utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.example.Settings.AppSettings;

import javax.persistence.*;


@Entity
@Table(name="steamitems")   //table name
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    public Item(String id, String name, String imageUrl)
    {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Item() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
