package models;

import lombok.Data;

@Data
public class DeleteBookResponseModel {
    private String isbn, userId;
}
