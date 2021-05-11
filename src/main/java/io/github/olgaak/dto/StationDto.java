package io.github.olgaak.dto;


public class StationDto {

    private long id;

    private String name;

    public StationDto() {
    }

    public StationDto(String id){
        this.id = Long.parseLong(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
