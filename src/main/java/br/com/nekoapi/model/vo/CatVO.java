/*
 * @author Marina Yumi Kanadani - RM 558404 - 1TDSPX
 */

package br.com.nekoapi.model.vo;

public class CatVO {
    private String id;
    private String name;
    private String description;
    private String temperament;
    private String origin;
    private String lifeSpan;
    private String imageUrl;

    public CatVO(String id, String name, String description, String temperament, String origin, String lifeSpan, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.temperament = temperament;
        this.origin = origin;
        this.lifeSpan = lifeSpan;
        this.imageUrl = imageUrl;
    }

    public CatVO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
